package kh.edu.rupp.ite.projectmad.ui.element.adapter.kh.edu.rupp.ite.projectmad.ui.element.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.projectmad.data.model.CartItem
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.ViewholderCartBinding


class ListCartAdapter(private val cartItems: List<MenuListData>) : Adapter<CartViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderCartBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val menu = cartItems[position]
        holder.bind(menu)

    }
}

class CartViewHolder(private val binding: ViewholderCartBinding) : ViewHolder(binding.root) {


    fun bind(menu: MenuListData) {
        Log.d("bind", "$menu")
        binding.titleOnCart.text = menu.name
        binding.priceInCart.text = menu.price.toString()
        binding.count.text = menu.quantity.toString()

        Picasso.get()
            .load(menu.image)
            .into(binding.imageInCart)

        Log.d("CartViewHolder", "Binding menu: $menu")

    }
}




