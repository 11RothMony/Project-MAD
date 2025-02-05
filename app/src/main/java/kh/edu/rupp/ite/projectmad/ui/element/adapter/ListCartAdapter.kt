package kh.edu.rupp.ite.projectmad.ui.element.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso

import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.ViewholderCartBinding

import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartViewModel


class ListCartAdapter(
    var cartItems: List<MenuListData>,
    private val viewModel: CartViewModel,

    ) : Adapter<CartViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderCartBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val menu = cartItems[position]
        holder.bind(menu)
        holder.binding.deleteItem.setOnClickListener {
            viewModel.deleteItemById(menu.id)
            cartItems = cartItems.filter { it.id != menu.id } // Update the list
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)
            Log.d("Delete Item", "$cartItems")
            notifyDataSetChanged()
        }
        holder.binding.plus.setOnClickListener {
            menu.quantity += 1
            viewModel.pushItem()
            // Update the UI
            holder.binding.count.text = menu.quantity.toString()
            holder.binding.numberOfProduct.text = menu.quantity.toString()
            holder.binding.totalPrice.text = (menu.price * menu.quantity).toString()

            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)
            Log.d("Delete Item", "$cartItems")
            notifyDataSetChanged()
        }
        holder.binding.minus.setOnClickListener {
            if (menu.quantity > 1) {
                menu.quantity -= 1
                viewModel.minusItem()
                // Update the UI
                holder.binding.count.text = menu.quantity.toString()
                holder.binding.numberOfProduct.text = menu.quantity.toString()
                holder.binding.totalPrice.text = (menu.price * menu.quantity).toString()
            } else {
                Toast.makeText(
                    holder.binding.root.context,
                    "Minimum quantity is 1!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
}

class CartViewHolder(
    val binding: ViewholderCartBinding,
) : ViewHolder(binding.root) {

    fun bind(menu: MenuListData) {
        Log.d("bind", "$menu")
        binding.titleOnCart.text = menu.name
        binding.priceInCart.text = menu.price.toString()
        binding.count.text = menu.quantity.toString()
        binding.totalPrice.text = menu.totalPrice.toString()
        binding.numberOfProduct.text = menu.quantity.toString()

        Picasso.get()
            .load(menu.image)
            .into(binding.imageInCart)
        Log.d("CartViewHolder", "Binding menu: ${menu.quantity}")
    }

}





