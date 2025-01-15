package kh.edu.rupp.ite.projectmad.ui.element.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.databinding.FragmentOrderBinding


class OrderFragment : BaseFragment() {
    private lateinit var binding: FragmentOrderBinding

    private lateinit var btnArrowUp: ImageView
    private lateinit var btnArrowDown: ImageView
    private lateinit var trackOrder: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnArrowUp = view.findViewById(R.id.arrowUp)
        btnArrowDown = view.findViewById(R.id.arrowDown)
        trackOrder = view.findViewById(R.id.trackOrder)



        btnArrowDown.visibility = View.VISIBLE
        trackOrder.visibility = View.VISIBLE
        btnArrowUp.visibility = View.GONE
        setupUi()

        btnArrowDown.setOnClickListener {
            arrowDown()
        }

        btnArrowUp.setOnClickListener {
            arrowUp()
        }
    }

    private fun setupUi() {


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

//    private fun displayItems(menu: List<MenuListData>) {
//        Log.d("displayMenu", "$menu")
//        binding.recycleOnOrder.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        binding.recycleOnOrder.adapter = ListMenuAdaptor(menu)
//    }

}