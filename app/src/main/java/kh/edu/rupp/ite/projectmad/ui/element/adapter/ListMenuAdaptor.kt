import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.ViewholderMenuBinding

class ListMenuAdaptor(private val data: List<MenuListData>) : Adapter<MenuViewHolder>() {

//    private var data = emptyList<MenuListData>()

//    fun setData(data: List<MenuListData>) {
//        this.data = data
//    }


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
        holder.bind(menu)
    }

}

class MenuViewHolder(private val binding: ViewholderMenuBinding) : ViewHolder(binding.root) {

    fun bind(menu: MenuListData) {
        Log.d("bind", "$menu")
        binding.name.text = menu.name
        binding.size.text = menu.size
        binding.price.text = menu.price.toString()
        binding.vergetatial.text = menu.isVegetarian.toString()

        Picasso.get()
            .load(menu.image)
            .into(binding.image)

    }

}