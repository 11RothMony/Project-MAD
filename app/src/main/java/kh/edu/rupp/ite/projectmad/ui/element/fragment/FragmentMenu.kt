package kh.edu.rupp.ite.projectmad.ui.element.fragment

import HomeFragment
import ListMenuAdaptor
import ListMenuViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.CartManager
import kh.edu.rupp.ite.projectmad.data.model.State
import kh.edu.rupp.ite.projectmad.databinding.FragmentMenuBinding
import kh.edu.rupp.ite.projectmad.data.model.MenuListData


class FragmentMenu : BaseFragment() {

    private val viewModel by viewModels<ListMenuViewModel>()

    private lateinit var binding: FragmentMenuBinding
//    private lateinit  var btnAdd : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<ImageView>(R.id.arrowback)
//        btnAdd = view.findViewById(R.id.addButton)

        setupObserver()
        viewModel.loadMenu()

        button.setOnClickListener {
            switchToHome()
        }

    }

    private fun setupObserver() {
        viewModel.menuListData.observe(viewLifecycleOwner) {
            handleState(it)
        }

    }

    private fun handleState(state: ApiState<List<MenuListData>>) {
        when (state.state) {
            State.Loading -> showLoading()
            State.Success -> {
                hideLoading()
                displayMenu(state.data!!)
            }

            State.Error -> {
                showAlert("Error", state.message ?: "unexpected Error")
                Log.d("ErroronMenu", state.message ?: "k")
                hideLoading()
            }

            else -> {}
        }
    }

    private fun displayMenu(menu: List<MenuListData>) {
        Log.d("displayMenu", "$menu")
        binding.recycleview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleview.adapter = ListMenuAdaptor(menu)
//        if (){
//            btnAdd.visibility = View.GONE
//        }
    }

    private fun switchToHome() {
        val fragmentHome = HomeFragment() // Create an instance of the fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.lyFragment, fragmentHome) // Replace the current fragment
            .addToBackStack("HomeFragment") // Optional: Add the transaction to the back stack
            .commit()
    }


}



