package kh.edu.rupp.ite.projectmad.data.model

data class MenuListData(
    val id: Int,
    val name: String,
    val image: String,
    val price: Double,
    val size: String,
    val isVegetarian: Boolean,
    val quantity: Int
){
    val totalPrice: Double
        get() = price * quantity
}
