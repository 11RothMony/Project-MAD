import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.MostPopularData
import kh.edu.rupp.ite.projectmad.data.model.NewProductData
import kh.edu.rupp.ite.projectmad.data.model.PromotionData
import kh.edu.rupp.ite.projectmad.data.model.State
import kh.edu.rupp.ite.projectmad.databinding.FragmentHomeBinding
import kh.edu.rupp.ite.projectmad.ui.element.adapter.ListNewProductAdapter
import kh.edu.rupp.ite.projectmad.ui.element.adapter.ListPopularAdapter
import kh.edu.rupp.ite.projectmad.ui.element.adapter.ListPromotionAdapter
import kh.edu.rupp.ite.projectmad.ui.element.fragment.BaseFragment
import kh.edu.rupp.ite.projectmad.ui.element.fragment.FragmentMenu
import kh.edu.rupp.ite.projectmad.ui.viewmodel.NewProductViewModel
import kh.edu.rupp.ite.projectmad.ui.viewmodel.PopularViewModel
import kh.edu.rupp.ite.projectmad.ui.viewmodel.PromotionViewModel

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    private val populaviewModel by viewModels<PopularViewModel>()
    private val newProductViewModel by viewModels<NewProductViewModel> ()
    private val promotionViewModel by viewModels<PromotionViewModel> ()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<ImageView>(R.id.pizzaButton)

        button.setOnClickListener {
            switchToMenu()
        }
        setupObserver()
        populaviewModel.loadMostPopular()
        newProductViewModel.loadNewProduct()
        promotionViewModel.loadPromotion()

    }

    private fun setupObserver() {

        populaviewModel.menuListData.observe(viewLifecycleOwner) { popularState ->
            val newProductState = newProductViewModel.menuListData.value ?: ApiState.loading()
            val promotionState = promotionViewModel.menuListDataPromotion.value ?: ApiState.loading()
            handleState(popularState, newProductState, promotionState)
        }

        newProductViewModel.menuListData.observe(viewLifecycleOwner) { newProductState ->
            val popularState = populaviewModel.menuListData.value ?: ApiState.loading()
            val promotionState = promotionViewModel.menuListDataPromotion.value ?: ApiState.loading()
            handleState(popularState, newProductState, promotionState)
        }

        promotionViewModel.menuListDataPromotion.observe(viewLifecycleOwner) { promotionState ->
            val popularState = populaviewModel.menuListData.value ?: ApiState.loading()
            val newProductState = newProductViewModel.menuListData.value ?: ApiState.loading()
            handleState(popularState, newProductState, promotionState)
        }
    }

    private fun switchToMenu() {
        val fragmentMenu = FragmentMenu() // Create an instance of the fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.lyFragment, fragmentMenu) // Replace the current fragment
            .addToBackStack("lyFragment") // Optional: Add the transaction to the back stack
            .commit()
    }



    private fun handleState(popularState: ApiState<List<MostPopularData>>, newProductState: ApiState<List<NewProductData>>, promotionState: ApiState<List<PromotionData>>) {
        when (popularState.state) {
            State.Loading -> showLoading()
            State.Success -> {
                hideLoading()
                displayPopular(popularState.data!!)

            }

            State.Error -> {
                showAlert("Error", popularState.message ?: "unexpected Error")
                Log.d("ErroronMenu", popularState.message?: "k")
                hideLoading()
            }

            else -> {}
        }
        when (newProductState.state) {
            State.Loading -> showLoading() // Optional: Avoid duplicate loading indicators
            State.Success -> {
                hideLoading()
                displayNewProduct(newProductState.data ?: emptyList())
            }
            State.Error -> {
                showAlert("Error in New Product Data", newProductState.message ?: "Unexpected Error")
                Log.d("ErrorOnNewProduct", newProductState.message ?: "No message")
                hideLoading()
            }
            else -> {}
        }


        when (promotionState.state) {
            State.Loading -> showLoading() // Optional: Avoid duplicate loading indicators
            State.Success -> {
                hideLoading()
                displayPromotion(promotionState.data ?: emptyList())
            }
            State.Error -> {
                showAlert("Error in New Product Data", promotionState.message ?: "Unexpected Error")
                Log.d("ErrorOnNewProduct", promotionState.message ?: "No message")
                hideLoading()
            }
            else -> {}
        }
    }


    private fun displayPopular(menu: List<MostPopularData>) {
        Log.d("displayMenu", "$menu")
        binding.recycleviewOnMostpopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycleviewOnMostpopular.adapter = ListPopularAdapter(menu)
    }

    private fun displayNewProduct(product: List<NewProductData>) {
        Log.d("displayMenu", "$product")
        binding.recycleviewOnNewproduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycleviewOnNewproduct.adapter = ListNewProductAdapter(product)
    }

    private fun displayPromotion(product: List<PromotionData>) {
        Log.d("displayMenu", "$product")
        binding.recycleviewOnPromotion.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycleviewOnPromotion.adapter = ListPromotionAdapter(product)
    }

}




