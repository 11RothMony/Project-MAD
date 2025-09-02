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
                        Toast.makeText(requireContext(), R.string.credit_card_deleted_successfully, Toast.LENGTH_SHORT).show()
                        if (response.affectedSubscriptions.isNotEmpty()) {
                            showAffectedSubscriptionsDialog(response.affectedSubscriptions)
                        }
                    } else {
                        showAlert(getString(R.string.error_deleting_credit_card), response.message)
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
            getString(R.string.delete_card_with_subscriptions, relatedSubscriptions.size)
        } else {
            getString(R.string.delete_card_confirmation)
        }

        AlertDialog.Builder(requireContext())
            .setTitle(R.string.delete_credit_card)
            .setMessage(message)
            .setPositiveButton(R.string.delete_credit_card) { _, _ ->
                val userId = auth.currentUser?.uid ?: ""
                paymentViewModel.deleteCreditCard(creditCard.id, userId)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun showSubscriptionManagementDialog(subscription: Subscription) {
        val options = arrayOf(
            getString(R.string.change_payment_method), 
            getString(R.string.cancel_subscription)
        )
        
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.manage_subscription) + " ${subscription.planName}")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        // Change payment method - this would open a payment method selection dialog
                        Toast.makeText(requireContext(), R.string.feature_coming_soon, Toast.LENGTH_SHORT).show()
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
            .setTitle(R.string.cancel_subscription)
            .setMessage(getString(R.string.cancel_subscription_confirmation, subscription.planName))
            .setPositiveButton(R.string.cancel_subscription) { _, _ ->
                Toast.makeText(requireContext(), R.string.feature_coming_soon, Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(R.string.keep_subscription, null)
            .show()
    }

    private fun showAffectedSubscriptionsDialog(affectedSubscriptions: List<String>) {
        val message = getString(
            R.string.subscriptions_affected_message, 
            affectedSubscriptions.joinToString("\n")
        )
        
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.subscriptions_affected)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok, null)
            .show()
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }
}