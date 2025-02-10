import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.projectmad.data.model.CartManager
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.ViewholderMenuBinding
import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartInNavigationBarViewModel

class ListMenuAdaptor(private val data: List<MenuListData>, private val viewModel: CartInNavigationBarViewModel) : Adapter<MenuViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewholderMenuBinding.inflate(layoutInflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = data[position]
        holder.bind(menu, viewModel)
    }

}

class MenuViewHolder(private val binding: ViewholderMenuBinding
) : ViewHolder(binding.root ) {


    fun bind(menu: MenuListData, viewModel: CartInNavigationBarViewModel) {

        binding.name.text = menu.name
        binding.size.text = menu.size
        binding.price.text = menu.price.toString()
        binding.vergetatial.text = menu.isVegetarian.toString()
        binding.numberOfProduct.text = menu.quantity.toString()
        Picasso.get()
            .load(menu.image)
            .into(binding.image)

        binding.countInmenu.visibility = View.GONE

        binding.addButton.setOnClickListener {
            CartManager.addToCart(menu)
            binding.addButton.visibility = View.GONE
            binding.countInmenu.visibility = View.VISIBLE
//            viewModel.addItemToCart()
            viewModel.totalQuantity()
        }

        binding.minus.setOnClickListener{
            viewModel.minusItemToCart()
            if(menu.quantity > 1) {
                menu.quantity -= 1
                binding.numberOfProduct.text = menu.quantity.toString()
            }else{
                binding.countInmenu.visibility = View.GONE
                binding.addButton.visibility = View.VISIBLE
                CartManager.deleteItemById(menu.id)

            }
        }
        binding.plus.setOnClickListener{
            menu.quantity += 1
            binding.numberOfProduct.text = menu.quantity.toString()
//            viewModel.addItemToCart()
            viewModel.totalQuantity()
        }

    }
}
