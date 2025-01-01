package kh.edu.rupp.ite.projectmad.ui.element.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.FragmentCartBinding
import kh.edu.rupp.ite.projectmad.ui.element.adapter.kh.edu.rupp.ite.projectmad.ui.element.adapter.ListCartAdapter
import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartViewModel

class CartFragment : BaseFragment() {

    private val cartViewModel by viewModels<CartViewModel>()

    private lateinit var binding: FragmentCartBinding

    private lateinit var emptyCart: LinearLayout
    private lateinit var recyclerViewOnCart: RecyclerView
    private lateinit var viewLine : View
    private lateinit var resultPrice : TextView


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
        setupObserver()
        cartViewModel.loadItemCart()
    }

    private fun setupObserver() {
        cartViewModel.cartItem.observe(viewLifecycleOwner) { data ->
            if (data.isNullOrEmpty()) {
                // Show empty cart UI
                emptyCart.visibility = View.VISIBLE
                recyclerViewOnCart.visibility = View.GONE
                viewLine.visibility = View.GONE
            } else {
                // Show cart items
                showCart(data)
                emptyCart.visibility = View.GONE
                recyclerViewOnCart.visibility = View.VISIBLE
                viewLine.visibility = View.VISIBLE
            }
        }
            cartViewModel.totalPrice.observe(viewLifecycleOwner) { price ->
                val formattedPrice = price?.let { "%.2f".format(it) } ?: "0.00"
               Log.d("totalPrice", formattedPrice)
                resultPrice.text = formattedPrice

                // Update total price UI
    //            totalPriceTextView.text = "Total: \$${"%.2f".format(price)}"
            }
    }

    private fun showCart(cartItem: List<MenuListData>) {
        binding.recycleViewOnCart.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleViewOnCart.adapter = ListCartAdapter(cartItem)
    }

}