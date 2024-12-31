package kh.edu.rupp.ite.projectmad.ui.element.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.projectmad.data.model.NewProductData
import kh.edu.rupp.ite.projectmad.databinding.ViewholderNewBinding

class ListNewProductAdapter(private val data: List<NewProductData>) : Adapter<NewProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderNewBinding.inflate(layoutInflater, parent, false)
        return NewProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NewProductViewHolder, position: Int) {
        val product = data[position]
        holder.bind(product)
    }
}

class NewProductViewHolder(private val binding: ViewholderNewBinding) : ViewHolder(binding.root){

    fun bind (product: NewProductData){
        binding.caption.text = product.name
//        binding.size.text = popular.size
        binding.priceInMostPopular.text = product.price.toString()

        Picasso.get()
            .load(product.image)
            .into(binding.imgPopular)

    }
}