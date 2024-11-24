package kh.edu.rupp.ite.projectmad.fragment
import ListMenuViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kh.edu.rupp.ite.model.ApiResponse
import kh.edu.rupp.ite.model.State
import kh.edu.rupp.ite.projectmad.databinding.FragmentMenuBinding
import kh.edu.rupp.ite.projectmad.model.ListMenuKotlin

class FragmentMenu : Fragment() {

    private val viewModel by viewModels<ListMenuViewModel>()

    private lateinit var binding: FragmentMenuBinding

    var menuKotlin: ListMenuKotlin? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.dataMenuList.observe(viewLifecycleOwner) { dataMenuState ->

            when (dataMenuState.state) {
//            State.loading -> showLoading()
                State.success -> {

                   val menuKotlin = dataMenuState.data
                   if (menuKotlin != null) {
                        displayMenu(dataMenuState.data)
                    } else {
                        Log.e("ruppite", "[MenuFragmentKotlin] Data is null in State.success $dataMenuState")
                    }

                }

                State.error -> {

                }

                else -> {}
            }
        }
        if (menuKotlin != null) {
            displayMenu(menuKotlin!!)
        } else {
            viewModel.loadMenu()
            Log.d("ruppite", "[ProfileFragmentKotlin] unsuccessful")
        }
    }

    private fun displayMenu(ListMenu: ListMenuKotlin) {
        binding.name.text = ListMenu.name
        binding.size.text = ListMenu.size
        binding.price.text = ListMenu.price.toString()
        binding.vergetatial.text = ListMenu.isVegetarian.toString()

    }




}


