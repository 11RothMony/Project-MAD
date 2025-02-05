package kh.edu.rupp.ite.projectmad.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.projectmad.data.model.CartManager

class CartInNavigationBarViewModel : ViewModel() {

    private val _totalQuantity = MutableLiveData(0)
    val totalQuantity: LiveData<Int> get() = _totalQuantity

    fun totalQuantity(){
        _totalQuantity.value = CartManager.getCartItems().sumOf { it.quantity }
        Log.d("CartViewModel quantity1", "${totalQuantity.value}")
    }

    fun minusItemToCart() {
        _totalQuantity.value = CartManager.getCartItems().sumOf { it.quantity } - 1
//        _totalQuantity.value = CartManager.deleteItemById(id)
        Log.d("addItemToCart_Quantity", "${totalQuantity.value}")
    }
}