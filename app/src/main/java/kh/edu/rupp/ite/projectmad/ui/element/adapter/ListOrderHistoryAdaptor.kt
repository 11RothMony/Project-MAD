import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.projectmad.data.model.CartManager
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.ViewholderCartOrderBinding
import kh.edu.rupp.ite.projectmad.databinding.ViewholderMenuBinding
import kh.edu.rupp.ite.projectmad.databinding.ViewholderOrderHistoryBinding
import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartInNavigationBarViewModel

class ListOrderHistoryAdaptor(private val data: List<MenuListData>) : Adapter<OrderHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderOrderHistoryBinding.inflate(layoutInflater, parent, false)
        return OrderHistoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: OrderHistoryViewHolder, position: Int) {
        val menu = data[position]
        holder.bind(menu)
    }

}

class OrderHistoryViewHolder(private val binding: ViewholderOrderHistoryBinding
) : ViewHolder(binding.root ) {


    fun bind(menu: MenuListData) {

        binding.titleInHistory.text = menu.name
//        binding.size.text = menu.size
        binding.priceInHistory.text = menu.price.toString()
//        binding.vergetatial.text = menu.isVegetarian.toString()
//        binding.numberOfProduct.text = menu.quantity.toString()
        Picasso.get()
            .load(menu.image)
            .into(binding.imageInHistory)
//
//        binding.countInmenu.visibility = View.GONE
//
//        binding.addButton.setOnClickListener {
//            CartManager.addToCart(menu)
//            binding.addButton.visibility = View.GONE
//            binding.countInmenu.visibility = View.VISIBLE
//        }
//
//        binding.minus.setOnClickListener{
//            if(menu.quantity > 1) {
//                menu.quantity -= 1
//                binding.numberOfProduct.text = menu.quantity.toString()
//            }else{
//                binding.countInmenu.visibility = View.GONE
//                binding.addButton.visibility = View.VISIBLE
//                CartManager.deleteItemById(menu.id)
//
//            }
//        }
//        binding.plus.setOnClickListener{
//            menu.quantity += 1
//            binding.numberOfProduct.text = menu.quantity.toString()
//        }

    }
}
