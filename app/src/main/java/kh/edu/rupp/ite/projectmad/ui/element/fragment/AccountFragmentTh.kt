//package kh.edu.rupp.ite.projectmad.ui.element.fragment
//
//import android.app.Activity
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.view.isVisible
//import com.squareup.picasso.Picasso
//import kh.edu.rupp.ite.projectmad.data.model.ProfileData
//import kh.edu.rupp.ite.projectmad.databinding.FragmentAccountBinding
//import kh.edu.rupp.ite.projectmad.global.AppPref
//import kh.edu.rupp.ite.projectmad.ui.element.activity.LoginActivity
//import kh.edu.rupp.ite.projectmad.ui.element.activity.LoginActivityTh
//
//class AccountFragmentTh : BaseFragment() {
//
//    private lateinit var binding: FragmentAccountBinding
//
//    private val activityLogInResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if (it.resultCode == Activity.RESULT_OK) {
//                val profile = AppPref.get().getProfile(requireContext())
//                showProfile(profile!!)
//            }
//        }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        binding = FragmentAccountBinding.inflate(layoutInflater, container, false)
//        return binding.root
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setupUi()
//        setupListener()
//    }
//
//    private fun setupUi() {
//        val profile = AppPref.get().getProfile(requireContext())
//        if (profile == null) {
//            showLogInButton()
//
//        } else {
//            showProfile(profile)
//        }
//    }
//
//    private fun setupListener() {
//        binding.myButton.setOnClickListener { onLogInButtonClick() }
//    }
//
//    private fun onLogInButtonClick() {
//        val intent = Intent(requireContext(), LoginActivityTh::class.java)
//        activityLogInResult.launch(intent)
////        startActivity(intent)
//    }
//
//
//
//    private fun showProfile(profile: ProfileData) {
//        binding.allProfileFragment.isVisible = true
//        binding.myButton.isVisible = false
//
//        binding.firstName.text = profile.fullName()
//        Picasso.get().load(profile.coverImage).into(binding.coverImage)
//        Picasso.get().load(profile.profileImage).into(binding.imageProfile)
//    }
//
//    private fun showLogInButton() {
//        binding.allProfileFragment.isVisible = false
//        binding.myButton.isVisible = true
//    }
//}