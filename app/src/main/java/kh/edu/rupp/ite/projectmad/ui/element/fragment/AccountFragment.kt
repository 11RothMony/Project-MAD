package kh.edu.rupp.ite.projectmad.ui.element.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.ProfileUser
import kh.edu.rupp.ite.projectmad.data.model.State
import kh.edu.rupp.ite.projectmad.databinding.FragmentAccountBinding
import kh.edu.rupp.ite.projectmad.ui.element.activity.LoginActivity
import kh.edu.rupp.ite.projectmad.ui.viewmodel.ProfileViewModel

class AccountFragment : BaseFragment() {

    private val viewModel by viewModels<ProfileViewModel>()
    private lateinit var binding: FragmentAccountBinding

    private lateinit var allProfileFragment: RelativeLayout
    private lateinit var loginButton: Button
    private lateinit var signOutButton: LinearLayout
    private lateinit var lineaProfile: LinearLayout

    private lateinit var auth: FirebaseAuth

//    val db = Firebase.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allProfileFragment = view.findViewById(R.id.allProfileFragment)
        loginButton = view.findViewById(R.id.myButton)
        signOutButton = view.findViewById(R.id.btnSignOut)
        lineaProfile = view.findViewById(R.id.linearProfile)

        auth = FirebaseAuth.getInstance()

        setupUi()
        viewModel.fetchProfile()
    }


    private fun setupUi() {
        val currentUser = auth.currentUser

        if (currentUser != null) {

            setupObserver()
            showButtonSignOut()

        } else {
            showButtonLogin()
        }

    }

    private fun setupObserver() {
        viewModel.profileData.observe(viewLifecycleOwner) {
            handleState(it)
        }
    }


   private fun handleState(state: ApiState<ProfileUser>) {
        when (state.state) {
            State.Loading -> showLoading()
            State.Success -> {
                hideLoading()
                showProfile(state.data!!)
            }

            State.Error -> {
                showAlert("Error", state.message ?: "unexpected Error")
                Log.d("ErrorUn", "${state.message}")
                hideLoading()
            }

            else -> {}
        }
    }


    private fun showProfile(profile: ProfileUser) {

        Log.d("ShowProfile", "$profile")
        binding.firstName.text = profile.profile.firstname
        binding.lastName.text = profile.profile.lastname

        Picasso.get().load(profile.profile.profileimage).into(binding.imageProfile)
//        Picasso.get().load(profile.profile.coverimage).into(binding.coverImage)
    }

    private fun showButtonLogin() {
        Toast.makeText(requireContext(), "Function executed!", Toast.LENGTH_SHORT).show()

        allProfileFragment.visibility = View.GONE
        lineaProfile.visibility = View.GONE

        loginButton.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showButtonSignOut() {
        signOutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }

        loginButton.visibility = View.GONE
    }

}
