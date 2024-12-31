package kh.edu.rupp.ite.projectmad.ui.element.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.projectmad.data.model.MostPopularData
import kh.edu.rupp.ite.projectmad.databinding.ViewholderMostpopularBinding

class ListPopularAdapter(private val data: List<MostPopularData>) : Adapter<PopularViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderMostpopularBinding.inflate(layoutInflater, parent, false)
        return PopularViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val popular = data[position]
        holder.bind(popular)
    }
}

class PopularViewHolder(private val binding: ViewholderMostpopularBinding) : ViewHolder(binding.root){

    fun bind (popular: MostPopularData){
        binding.caption.text = popular.name
//        binding.size.text = popular.size
        binding.priceInMostPopular.text = popular.price.toString()

        Picasso.get()
            .load(popular.image)
            .into(binding.imgPopular)

    }
}