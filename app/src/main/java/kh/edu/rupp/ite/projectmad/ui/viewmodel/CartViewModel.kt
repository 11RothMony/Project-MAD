package kh.edu.rupp.ite.projectmad.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private var _confirmedProducts = MutableLiveData<List<MenuListData>>()
    val confirmedProducts: LiveData<List<MenuListData>> get() = _confirmedProducts

    private val _buttonClicked = MutableLiveData<List<MenuListData>>()
    private val buttonClicked: LiveData<List<MenuListData>> get() = _buttonClicked


    fun loadItemCart() {
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
        Log.d("DeleteItem", "${CartManager.getTotalPrice()}")
        loadItemCart() // Refresh the LiveData with updated cart items
    }

    fun pushItem() {
        loadItemCart()
    }

    fun minusItem() {
        loadItemCart()
    }

    fun onButtonClicked() {
        val currentCartItems = _cartItems.value ?: emptyList()
        _buttonClicked.value = currentCartItems
    }

    fun confirmProducts() {
        val selectedItems = buttonClicked.value ?: emptyList()
        _confirmedProducts.value = selectedItems
        Log.d("ConfirmItem", "Confirmed Products: $selectedItems")
    }

}