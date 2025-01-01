package kh.edu.rupp.ite.projectmad.data.model

import android.util.Log


object CartManager {

    private val cartItems = mutableListOf<MenuListData>()

    fun addToCart(product: MenuListData) {
        val existingProduct = cartItems.find { it.id == product.id }

        if (existingProduct != null) {
            val updatedProduct = existingProduct.copy(quantity = existingProduct.quantity + product.quantity)
            cartItems[cartItems.indexOf(existingProduct)] = updatedProduct
        } else {
            cartItems.add(product)
        }

        Log.d("CartManager", "Added to cart: ${product.name}")
        Log.d("CartManager1", "Current cart items: $cartItems")

    }

    fun getCartItems(): List<MenuListData> = cartItems



    fun clearCart() {
        cartItems.clear()
    }

    fun getTotalPrice(): Double = cartItems.sumOf { it.price * it.quantity }
}