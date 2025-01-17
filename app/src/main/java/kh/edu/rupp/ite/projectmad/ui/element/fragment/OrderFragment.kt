package kh.edu.rupp.ite.projectmad.ui.element.fragment

import ListOrderAdaptor
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.FragmentOrderBinding
import kh.edu.rupp.ite.projectmad.ui.viewmodel.CartViewModel
import kh.edu.rupp.ite.projectmad.ui.viewmodel.OrderViewModel


class OrderFragment : BaseFragment() {
    private lateinit var binding: FragmentOrderBinding
    private val orderViewModel  by viewModels<OrderViewModel>()

    private lateinit var btnArrowUp: ImageView
    private lateinit var btnArrowDown: ImageView
    private lateinit var trackOrder: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        orderViewModel.confirmProducts()

        btnArrowUp = view.findViewById(R.id.arrowUp)
        btnArrowDown = view.findViewById(R.id.arrowDown)
        trackOrder = view.findViewById(R.id.trackOrder)

        btnArrowDown.visibility = View.GONE
        trackOrder.visibility = View.VISIBLE
        btnArrowUp.visibility = View.VISIBLE

//        Log.d("setUiOnOrder", "${orderViewModel.confirmedProducts.value}")

        btnArrowDown.setOnClickListener {
            arrowDown()
        }

        btnArrowUp.setOnClickListener {
            arrowUp()
        }

        setupUi()
//        orderViewModel.log()
    }

    private fun setupUi() {
        Log.d("setUiOrder", "${orderViewModel.confirmedProducts.value}")
        orderViewModel.confirmedProducts.observe(viewLifecycleOwner) { data ->
            if (data != null && data.isNotEmpty()) {
                displayItems(data)
                Log.d("setUiOnOrder", "$data")
            } else {
                Log.d("setUiOnOrder", "No confirmed products data available")
            }
        }
    }

    private fun arrowDown() {
        btnArrowUp.visibility = View.VISIBLE
        btnArrowDown.visibility = View.GONE
        trackOrder.visibility = View.VISIBLE
    }

    private fun arrowUp() {
        btnArrowDown.visibility = View.VISIBLE
        btnArrowUp.visibility = View.GONE
        trackOrder.visibility = View.GONE
    }

    private fun displayItems(menu: List<MenuListData>) {
        Log.d("displayOrder", "$menu")
        binding.recycleOnOrder.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recycleOnOrder.adapter = ListOrderAdaptor(menu)
    }

}