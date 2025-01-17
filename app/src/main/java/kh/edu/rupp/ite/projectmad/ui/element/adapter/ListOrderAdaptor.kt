import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.projectmad.data.model.CartManager
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.ViewholderCartOrderBinding
import kh.edu.rupp.ite.projectmad.databinding.ViewholderMenuBinding

class ListOrderAdaptor(private val data: List<MenuListData>) : Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderCartOrderBinding.inflate(layoutInflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val menu = data[position]
        holder.bind(menu)
    }
}

class OrderViewHolder(private val binding: ViewholderCartOrderBinding) : ViewHolder(binding.root) {

    fun bind(menu: MenuListData) {
        Log.d("bind100", "$menu")
        binding.titleOnCart.text = menu.name
        binding.priceInCart.text = menu.price.toString()
        binding.count.text = menu.quantity.toString()
        binding.totalPrice.text = menu.totalPrice.toString()
        Picasso.get()
            .load(menu.image)
            .into(binding.imageInCart)


    }
}

//fun addData(menu: MenuListData) {
//    val mutableList: MutableList<MenuListData> = arrayListOf()
//    mutableList.add(menu)
//    Log.d("ArrayList", "Updated List: ${mutableList.joinToString { it.name }}")
//}