package kh.edu.rupp.ite.projectmad.ui.element.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.databinding.FragmentCartBinding
import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartViewModel

class CartFragment : BaseFragment() {

    private val cartViewModel by viewModels<CartViewModel> ()
    private lateinit var binding: FragmentCartBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        showCart()
    }

    private fun showCart() {
        val cartItems = cartViewModel.getCartItem()

//        val cartItemsTextView: TextView = binding.titleOnCart


//        cartItemsTextView.text = cartItems.joinToString("\n") { "${it.name} - $${it.price}" }

    }

}