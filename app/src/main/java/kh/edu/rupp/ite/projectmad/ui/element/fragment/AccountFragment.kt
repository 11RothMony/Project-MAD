package kh.edu.rupp.ite.projectmad.ui.element.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.ui.element.activity.LoginActivity
import kh.edu.rupp.ite.projectmad.ui.element.activity.MainActivity

class AccountFragment : BaseFragment() {

    private lateinit var allProfileFragment: RelativeLayout

    //    private lateinit var coverImageView: ImageView
    private lateinit var loginButton: Button
    private lateinit var signOutButton: Button

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        allProfileFragment = view.findViewById(R.id.allProfileFragment)
        loginButton = view.findViewById(R.id.myButton)
        signOutButton = view.findViewById(R.id.btnSignOut)


        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        if (currentUser != null) {
            showProfile(currentUser)
            showButtonSignOut()
        } else {
            showButtonLogin()
        }


    }


    private fun showProfile(user: FirebaseUser) {

    }


    private fun showButtonLogin() {
        Toast.makeText(requireContext(), "Function executed!", Toast.LENGTH_SHORT).show()

        allProfileFragment.visibility = View.GONE

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

    }


}