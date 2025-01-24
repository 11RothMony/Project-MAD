package kh.edu.rupp.ite.projectmad.ui.element.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.CartManager
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.FragmentCartBinding
import kh.edu.rupp.ite.projectmad.ui.element.adapter.ListCartAdapter
import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartViewModel

class CartFragment : BaseFragment() {

    private val cartViewModel by viewModels<CartViewModel>()

    private lateinit var binding: FragmentCartBinding

    private lateinit var emptyCart: LinearLayout
    private lateinit var recyclerViewOnCart: RecyclerView
    private lateinit var viewLine: View
    private lateinit var resultPrice: TextView
    private lateinit var button: TextView
    private lateinit var adapter: ListCartAdapter
    private lateinit var haveDataInCart: LinearLayout
    private lateinit var orderBtn: Button
    private lateinit var bottomNavigationView: BottomNavigationView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emptyCart = view.findViewById(R.id.emptyCart)
        recyclerViewOnCart = view.findViewById(R.id.recycleView_on_Cart)
        viewLine = view.findViewById(R.id.viewLine)
        resultPrice = view.findViewById(R.id.totalPriceInCart)
        button = view.findViewById(R.id.button_clearCart)
        haveDataInCart = view.findViewById(R.id.whenHaveData)
        orderBtn = view.findViewById(R.id.orderBtn)
        bottomNavigationView = requireActivity().findViewById(R.id.bottomNavigation)


        setupObserver()
        cartViewModel.loadItemCart()

        button.setOnClickListener {
            cartViewModel.clearCart()
        }

        orderBtn.setOnClickListener {
//            cartViewModel.onButtonClicked()
            switchToCart()
        }
//        onPause()

    }
//    override fun onPause() {
//        super.onPause()
//        // Revert system UI visibility back to normal when the fragment is paused
//        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
//    }


    private fun setupObserver() {
        cartViewModel.cartItem.observe(viewLifecycleOwner) { data ->
            if (data.isNullOrEmpty()) {
                // Show empty cart UI
                emptyCart.visibility = View.VISIBLE
                recyclerViewOnCart.visibility = View.GONE
                viewLine.visibility = View.GONE
                haveDataInCart.visibility = View.GONE
            } else {
                // Show cart items
                showCart(data)
                emptyCart.visibility = View.GONE
                recyclerViewOnCart.visibility = View.VISIBLE
                viewLine.visibility = View.VISIBLE
            }
        }

        cartViewModel.itemLiveData.observe(viewLifecycleOwner) { update ->
            displayItem(update)
        }

        cartViewModel.totalPrice.observe(viewLifecycleOwner) { price ->
            // Set up RecyclerView adapter
            recyclerViewOnCart.adapter =
                ListCartAdapter(cartViewModel.cartItem.value.orEmpty(), cartViewModel)

            binding.totalPriceInCart.text = formatPrice(price)
            Log.d("totalPrice", "${cartViewModel.totalPrice.value}")
        }

    }

    private fun formatPrice(price: Double?): String {
        return "%.2f".format(price ?: 0.00)
    }

    private fun showCart(cartItem: List<MenuListData>) {
        binding.recycleViewOnCart.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleViewOnCart.adapter = ListCartAdapter(
            cartItem,
            viewModel = CartViewModel()
        )
    }

    private fun displayItem(cartItems: List<MenuListData>) {
        adapter = ListCartAdapter(cartItems, cartViewModel)
        binding.recycleViewOnCart.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapter
        }
    }

    private fun switchToCart() {

//        val fragmentOrder = OrderFragment() // Create an instance of the fragment
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.lyFragment, fragmentOrder) // Replace the current fragment
//            .addToBackStack("OrderFragment") // Optional: Add the transaction to the back stack
//            .commit()
        bottomNavigationView.selectedItemId = R.id.menuOrders
    }
//    private fun switchToCart(fragment: Fragment) {
//        val fragmentTransaction = parentFragmentManager.beginTransaction()
//
//        // If the fragment is already added, just show it
//        if (fragment.isAdded) {
//            fragmentTransaction.hide(currentFragment)
//            fragmentTransaction.show(fragment)
//        } else {
//            // Remove the current fragment if any
//            removeCurrentFragment()
//
//            // Add the new fragment
//            fragmentTransaction.add(R.id.lyFragment, fragment)
//        }
//
//        // Update the current fragment reference
//        currentFragment = fragment
//
//        // Commit the transaction
//        fragmentTransaction.commit()
//        // Change Bottom Navigation to Order
//        bottomNavigationView.selectedItemId = R.id.menuOrders
//    }

//    private fun removeCurrentFragment() {
//        val currentFragment = parentFragmentManager.findFragmentById(R.id.lyFragment)
//        if (currentFragment != null) {
//            parentFragmentManager.beginTransaction()
//                .remove(currentFragment)
//                .commit()
//        }
//    }


}



