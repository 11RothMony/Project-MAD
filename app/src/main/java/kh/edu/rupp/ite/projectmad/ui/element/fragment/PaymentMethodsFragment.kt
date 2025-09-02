package kh.edu.rupp.ite.projectmad.ui.element.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.CreditCard
import kh.edu.rupp.ite.projectmad.data.model.State
import kh.edu.rupp.ite.projectmad.data.model.Subscription
import kh.edu.rupp.ite.projectmad.databinding.FragmentPaymentMethodsBinding
import kh.edu.rupp.ite.projectmad.ui.element.adapter.CreditCardAdapter
import kh.edu.rupp.ite.projectmad.ui.element.adapter.SubscriptionAdapter
import kh.edu.rupp.ite.projectmad.ui.viewmodel.PaymentViewModel

class PaymentMethodsFragment : BaseFragment() {

    private val paymentViewModel by viewModels<PaymentViewModel>()
    private lateinit var binding: FragmentPaymentMethodsBinding
    
    private lateinit var creditCardAdapter: CreditCardAdapter
    private lateinit var subscriptionAdapter: SubscriptionAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaymentMethodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        auth = FirebaseAuth.getInstance()
        progressBar = view.findViewById(R.id.progress_bar)
        
        setupRecyclerViews()
        setupObservers()
        setupClickListeners()
        
        // Load payment methods
        paymentViewModel.loadPaymentMethods()
    }

    private fun setupRecyclerViews() {
        // Setup Credit Cards RecyclerView
        creditCardAdapter = CreditCardAdapter(emptyList()) { creditCard ->
            showDeleteConfirmationDialog(creditCard)
        }
        binding.recyclerCreditCards.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = creditCardAdapter
        }

        // Setup Subscriptions RecyclerView
        subscriptionAdapter = SubscriptionAdapter(emptyList(), emptyList()) { subscription ->
            showSubscriptionManagementDialog(subscription)
        }
        binding.recyclerSubscriptions.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = subscriptionAdapter
        }
    }

    private fun setupObservers() {
        paymentViewModel.paymentMethods.observe(viewLifecycleOwner) { state ->
            handlePaymentMethodsState(state)
        }

        paymentViewModel.deleteCreditCardResult.observe(viewLifecycleOwner) { state ->
            handleDeleteCreditCardState(state)
        }

        paymentViewModel.creditCards.observe(viewLifecycleOwner) { creditCards ->
            creditCardAdapter.updateCreditCards(creditCards)
        }

        paymentViewModel.subscriptions.observe(viewLifecycleOwner) { subscriptions ->
            subscriptionAdapter.updateSubscriptions(
                subscriptions, 
                paymentViewModel.creditCards.value ?: emptyList()
            )
        }
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun handlePaymentMethodsState(state: ApiState<kh.edu.rupp.ite.projectmad.data.model.PaymentMethodsResponse>) {
        when (state.state) {
            State.Loading -> showLoading()
            State.Success -> {
                hideLoading()
                state.data?.let { response ->
                    paymentViewModel.updatePaymentMethodsData(response)
                }
            }
            State.Error -> {
                hideLoading()
                showAlert("Error", state.message ?: "Failed to load payment methods")
                Log.e("PaymentMethodsFragment", "Error loading payment methods: ${state.message}")
            }
            else -> {}
        }
    }

    private fun handleDeleteCreditCardState(state: ApiState<kh.edu.rupp.ite.projectmad.data.model.DeleteCreditCardResponse>) {
        when (state.state) {
            State.Loading -> showLoading()
            State.Success -> {
                hideLoading()
                state.data?.let { response ->
                    if (response.success) {
                        Toast.makeText(requireContext(), "Credit card deleted successfully", Toast.LENGTH_SHORT).show()
                        if (response.affectedSubscriptions.isNotEmpty()) {
                            showAffectedSubscriptionsDialog(response.affectedSubscriptions)
                        }
                    } else {
                        showAlert("Error", response.message)
                    }
                }
            }
            State.Error -> {
                hideLoading()
                showAlert("Error", state.message ?: "Failed to delete credit card")
                Log.e("PaymentMethodsFragment", "Error deleting credit card: ${state.message}")
            }
            else -> {}
        }
    }

    private fun showDeleteConfirmationDialog(creditCard: CreditCard) {
        val relatedSubscriptions = paymentViewModel.getSubscriptionsByCardId(creditCard.id)
        
        val message = if (relatedSubscriptions.isNotEmpty()) {
            "This credit card is being used by ${relatedSubscriptions.size} subscription(s). " +
            "Deleting it will affect these subscriptions. Do you want to continue?"
        } else {
            "Are you sure you want to delete this credit card?"
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Delete Credit Card")
            .setMessage(message)
            .setPositiveButton("Delete") { _, _ ->
                val userId = auth.currentUser?.uid ?: ""
                paymentViewModel.deleteCreditCard(creditCard.id, userId)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showSubscriptionManagementDialog(subscription: Subscription) {
        val options = arrayOf("Change Payment Method", "Cancel Subscription")
        
        AlertDialog.Builder(requireContext())
            .setTitle("Manage ${subscription.planName}")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        // Change payment method - this would open a payment method selection dialog
                        Toast.makeText(requireContext(), "Change payment method feature coming soon", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        // Cancel subscription
                        showCancelSubscriptionDialog(subscription)
                    }
                }
            }
            .show()
    }

    private fun showCancelSubscriptionDialog(subscription: Subscription) {
        AlertDialog.Builder(requireContext())
            .setTitle("Cancel Subscription")
            .setMessage("Are you sure you want to cancel your ${subscription.planName} subscription?")
            .setPositiveButton("Cancel Subscription") { _, _ ->
                Toast.makeText(requireContext(), "Subscription cancellation feature coming soon", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Keep Subscription", null)
            .show()
    }

    private fun showAffectedSubscriptionsDialog(affectedSubscriptions: List<String>) {
        val message = "The following subscriptions were affected by the credit card deletion:\n\n" +
                affectedSubscriptions.joinToString("\n") + 
                "\n\nPlease update their payment methods."
        
        AlertDialog.Builder(requireContext())
            .setTitle("Subscriptions Affected")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}