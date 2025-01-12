package kh.edu.rupp.ite.projectmad.ui.element.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.LoginData
import kh.edu.rupp.ite.projectmad.data.model.State
import kh.edu.rupp.ite.projectmad.ui.viewmodel.LogInViewModel
import kh.edu.rupp.ite.visitme.ui.element.activity.BaseActivity

class LoginActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth
    private val viewModel by  viewModels<LogInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        onLoginBtn()
        setupObserver()

    }


    private fun setupObserver() {
        viewModel.logInData.observe(this) { handleState(it) }
    }

//    private fun loginUser(email: String, password: String) {
//
//        val user = auth.currentUser
//        user?.getIdToken(true)?.addOnCompleteListener { tokenTask ->
//            if(tokenTask.isSuccessful){
//                val idToken = tokenTask.result?.token
//                if (idToken != null) {
//                    viewModel.login(idToken)
//                }
//            }else{
//
//            }
//        }
//
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    startActivity(Intent(this, MainActivity::class.java))
//
//                } else {
//                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT)
//                        .show()
//                    Log.d("LoginActivity", "Error: ${task.exception?.message}")
//                }
//            }
//
//    }

    private fun onLoginBtn(){
        val emailField = findViewById<EditText>(R.id.etEmail)
        val passwordField = findViewById<EditText>(R.id.etPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }
//            Log.d("LoginUser", "${auth.currentUser}")
//            loginUser(email, password)
            viewModel.login(email, password)

        }
    }


    private fun handleState(state: ApiState<LoginData>) {
        when (state.state) {
            State.Loading -> showLoading()
            State.Success -> {
//                AppPref.get().storeProfile(this, state.data!!.profile)
//                AppEncryptedPref.get().storeToken(this, state.data.token)
//                setResult(RESULT_OK)
//                finish()
                Log.d("LoginActivity", "${State.Success}")
                startActivity(Intent(this, MainActivity::class.java))

            }

            State.Error -> {
                hideLoading()
                showAlert("ErrorOnLogin", state.message ?: "Unexpected Error")
                Log.d("ErrorOnLogin", state.message?: "Unexpected Error")
            }

            else -> {}
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}
