package kh.edu.rupp.ite.projectmad.ui.element.fragment

import ListOrderHistoryAdaptor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.FragmentMyOrderBinding
import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartInNavigationBarViewModel
import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartViewModel

class MyOrderFragment : BaseFragment() {
    private lateinit var binding : FragmentMyOrderBinding
    private val viewModelInCart by viewModels<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelInCart.cartItem.value?.let { displayMenu(it) }

    }

    private fun displayMenu(menu: List<MenuListData>) {
        Log.d("displayMenu", "$menu")
        binding.recycleViewOnOrderHistory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleViewOnOrderHistory.adapter = ListOrderHistoryAdaptor(menu)
    }
}