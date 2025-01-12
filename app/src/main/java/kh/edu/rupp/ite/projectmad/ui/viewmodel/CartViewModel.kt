package kh.edu.rupp.ite.projectmad.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.projectmad.data.model.CartManager
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kotlinx.coroutines.launch


class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<List<MenuListData>>(mutableListOf())
    val cartItem: LiveData<List<MenuListData>> get() = _cartItems

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> get() = _totalPrice

    private val _itemsLiveData = MutableLiveData<MutableList<MenuListData>>()
    val itemLiveData get() = _itemsLiveData


    fun loadItemCart(){
        _cartItems.value = CartManager.getCartItems()
        _totalPrice.value = CartManager.getTotalPrice()
        Log.d("totalPrice1", "${_totalPrice.value}")
        Log.d("totalPrice2", "${totalPrice.value}")
    }

    init {
        loadItemCart()
    }

    fun clearCart() {
        CartManager.clearCart()
        loadItemCart() // Refresh the LiveData with updated cart items
    }

    fun deleteItemById(id: Int) {
        CartManager.deleteItemById(id)
        Log.d("DeleteItem", "${CartManager.getTotalPrice()}")
        loadItemCart() // Refresh the LiveData with updated cart items
    }

    fun pushItem(){
        loadItemCart()
    }

    fun minusItem(){
        loadItemCart()
    }

}