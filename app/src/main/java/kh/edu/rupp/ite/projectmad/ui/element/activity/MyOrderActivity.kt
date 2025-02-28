package kh.edu.rupp.ite.projectmad.ui.element.activity

import ListOrderHistoryAdaptor
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.ActivityMyOrderBinding
import kh.edu.rupp.ite.projectmad.ui.element.fragment.AccountFragment
import kh.edu.rupp.ite.projectmad.ui.element.fragment.FragmentMenu
import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartViewModel
import kh.edu.rupp.ite.visitme.ui.element.activity.BaseActivity

class MyOrderActivity : BaseActivity() {
    private lateinit var binding: ActivityMyOrderBinding
    private val viewModelInCart by viewModels<CartViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelInCart.cartItem.value?.let { displayMenu(it) }
        binding.arrowBackOnHistory.setOnClickListener{
            backToAccountFragment()
        }

    }

    private fun displayMenu(menu: List<MenuListData>) {
        Log.d("displayMenu", "$menu")
        binding.recycleViewOnOrderHistory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recycleViewOnOrderHistory.adapter = ListOrderHistoryAdaptor(menu)
    }
    private fun backToAccountFragment(){
       finish()
    }
}
