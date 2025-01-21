//package kh.edu.rupp.ite.projectmad.ui.viewmodel
//
//import android.util.Log
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import kh.edu.rupp.ite.projectmad.data.model.CartManager
//import kh.edu.rupp.ite.projectmad.data.model.MenuListData
//
//class OrderViewModel :  ViewModel()  {
//    // LiveData for confirmed products
//    private var _confirmedProducts = MutableLiveData<List<MenuListData>>()
//    val confirmedProducts: LiveData<List<MenuListData>> get() = _confirmedProducts
//
//    // Confirm products by taking items from cartItems
//    fun confirmProducts() {
//        val cartItems = CartManager.getCartItems()// Default to empty list if null
//        _confirmedProducts.postValue(cartItems)
//        Log.d("confirmProduct", "${CartManager.getCartItems()}")
//    }
//}