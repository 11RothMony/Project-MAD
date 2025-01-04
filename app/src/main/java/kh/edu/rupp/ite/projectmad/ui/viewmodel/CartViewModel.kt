package kh.edu.rupp.ite.projectmad.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.projectmad.data.api.client.ApiClient
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.CartManager
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<List<MenuListData>>(emptyList())
    val cartItem: LiveData<List<MenuListData>> get() = _cartItems

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> get() = _totalPrice


    private val _clearItems  = MutableLiveData<List<String>>()
//    val clearItem: LiveData<List<String>> get() = _clearItems

    private val _itemsLiveData = MutableLiveData<List<MenuListData>>()
//    val itemsLiveData : LiveData<List<MenuListData>> get() = _itemsLiveData
    val itemLiveData get() = _itemsLiveData


    fun loadItemCart(){
        _cartItems.value = CartManager.getCartItems()
        _totalPrice.value = CartManager.getTotalPrice()
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
        loadItemCart() // Refresh the LiveData with updated cart items
    }






}
