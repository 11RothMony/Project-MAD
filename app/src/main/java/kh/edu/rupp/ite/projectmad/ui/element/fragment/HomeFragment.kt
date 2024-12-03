import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.databinding.FragmentHomeBinding
import kh.edu.rupp.ite.projectmad.ui.element.fragment.BaseFragment
import kh.edu.rupp.ite.projectmad.ui.element.fragment.FragmentMenu

class HomeFragment : BaseFragment() {
    //    private val  viewModel by viewModel
    private lateinit var binding: FragmentHomeBinding

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
    }

    fun switchToMenu() {
        val fragmentMenu = FragmentMenu() // Create an instance of the fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.lyFragment, fragmentMenu) // Replace the current fragment
            .addToBackStack(null) // Optional: Add the transaction to the back stack
            .commit()
    }

}




