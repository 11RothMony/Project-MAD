package kh.edu.rupp.ite.projectmad.ui.element.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kh.edu.rupp.ite.projectmad.databinding.FragmentMyOrderBinding

class MyOrderFragment : BaseFragment() {
    private lateinit var binding : FragmentMyOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}