package kh.edu.rupp.ite.projectmad.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kh.edu.rupp.ite.projectmad.data.model.CartData

class CartViewModel : ViewModel() {

    private val _cart = MutableLiveData<MutableList<CartData>>(mutableListOf())
    val cart: LiveData<MutableList<CartData>> get() = _cart

    fun addToCart(cart : CartData){

        val currentCart = _cart.value ?: mutableListOf()
        currentCart.add(cart)
        _cart.value = currentCart

    }

    fun getCartItem(): List<CartData>{
        return _cart.value ?: emptyList()
    }

}