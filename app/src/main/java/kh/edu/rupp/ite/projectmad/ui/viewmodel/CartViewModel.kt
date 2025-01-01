package kh.edu.rupp.ite.projectmad.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.projectmad.data.model.CartManager
import kh.edu.rupp.ite.projectmad.data.model.MenuListData

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<List<MenuListData>>(emptyList())
    val cartItem: LiveData<List<MenuListData>> get() = _cartItems

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> get() = _totalPrice

    fun loadItemCart(){

        _cartItems.value = CartManager.getCartItems()
        _totalPrice.value = CartManager.getTotalPrice()

    }

}
