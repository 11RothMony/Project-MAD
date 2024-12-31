package kh.edu.rupp.ite.projectmad.ui.element.adapter.kh.edu.rupp.ite.projectmad.ui.element.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kh.edu.rupp.ite.projectmad.data.model.CartData
import kh.edu.rupp.ite.projectmad.databinding.ViewholderCartBinding

class ListCartAdapter(private val data: List<CartData>) : Adapter<CartViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderCartBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val menu = data[position]
        holder.bind(menu)


    }
}


class CartViewHolder(private val binding: ViewholderCartBinding) : ViewHolder(binding.root) {


    fun bind(menu: CartData) {
        Log.d("bind", "$menu")

    }
}




