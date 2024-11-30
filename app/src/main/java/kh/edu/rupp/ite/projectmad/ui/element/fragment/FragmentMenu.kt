package kh.edu.rupp.ite.projectmad.ui.element.fragment
import ListMenuAdaptor
import ListMenuViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.State
import kh.edu.rupp.ite.projectmad.databinding.FragmentMenuBinding
import kh.edu.rupp.ite.projectmad.data.model.MenuListData


class FragmentMenu : BaseFragment() {

    private val viewModel by viewModels<ListMenuViewModel>()

    private lateinit var binding: FragmentMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setupObserver()
    viewModel.loadMenu()

}


    private fun setupObserver(){

        viewModel.menuListData.observe(viewLifecycleOwner){
            handleState(it)
        }
    }

    private fun handleState(state: ApiState<List<MenuListData>>){
        when (state.state) {
            State.Loading  -> showLoading()
            State.Success  -> {
                hideLoading()
                displayMenu(state.data!!)
            }
            State.Error -> {
                showAlert("Error", state.message?: "unexpected Error")
                hideLoading()
            }

            else -> {}
        }
    }


//    private fun displayMenu(ListMenu: MenuListData) {
//        Log.d("ruppite", "Load data in displaymen")
//        binding.name.text = ListMenu.name
//        binding.size.text = ListMenu.size
//        binding.price.text = ListMenu.price.toString()
//        binding.vergetatial.text = ListMenu.isVegetarian.toString()
//
//        Picasso.get()
//            .load(ListMenu.image)
//            .into(binding.image)
//
//
//    }
    private fun displayMenu(menu: List<MenuListData>){
        Log.d("displayMenu", "$menu")
        binding.recycleview.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleview.adapter = ListMenuAdaptor(menu)
    }



}


