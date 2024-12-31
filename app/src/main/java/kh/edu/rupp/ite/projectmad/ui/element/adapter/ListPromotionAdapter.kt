package kh.edu.rupp.ite.projectmad.ui.element.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.projectmad.data.model.NewProductData
import kh.edu.rupp.ite.projectmad.data.model.PromotionData
import kh.edu.rupp.ite.projectmad.databinding.ViewholderNewBinding
import kh.edu.rupp.ite.projectmad.databinding.ViewholderPromotionBinding

class ListPromotionAdapter(private val data: List<PromotionData>) : Adapter<PromotionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderPromotionBinding.inflate(layoutInflater, parent, false)
        return PromotionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
        val product = data[position]
        holder.bind(product)
    }
}

class PromotionViewHolder(private val binding: ViewholderPromotionBinding) : ViewHolder(binding.root){

    fun bind (product: PromotionData){

        Picasso.get()
            .load(product.image)
            .into(binding.imgPopular)

    }
}