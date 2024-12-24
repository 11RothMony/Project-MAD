//package kh.edu.rupp.ite.projectmad.ui.element.activity
//
//import android.app.Activity
//import android.content.Intent
//import android.os.Bundle
//import android.os.PersistableBundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.activity.viewModels
//import com.google.firebase.auth.FirebaseAuth
//import kh.edu.rupp.ite.projectmad.R
//import kh.edu.rupp.ite.projectmad.data.model.ApiState
//import kh.edu.rupp.ite.projectmad.data.model.LoginData
//import kh.edu.rupp.ite.projectmad.data.model.State
//import kh.edu.rupp.ite.projectmad.databinding.ActivityLoginBinding
//import kh.edu.rupp.ite.projectmad.global.AppEncryptedPref
//import kh.edu.rupp.ite.projectmad.global.AppPref
//import kh.edu.rupp.ite.projectmad.ui.viewmodel.LogInViewModel
//import kh.edu.rupp.ite.visitme.ui.element.activity.BaseActivity
//
//class LoginActivityTh: BaseActivity() {
//
//    private lateinit var binding: ActivityLoginBinding
//
//    private lateinit var auth: FirebaseAuth
//
//    private val viewModel by viewModels<LogInViewModel>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setupUi()
//        setupListener()
//        setupObserver()
//    }
//
//
//
//    private fun setupUi() {
//        binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//
//    private fun setupListener() {
//        binding.btnLogin.setOnClickListener { onLogInButtonClick() }
//    }
//
//    private fun setupObserver() {
//        viewModel.logInData.observe(this) { handleState(it) }
//    }
//
//    private fun onLogInButtonClick() {
//
//        val username = findViewById<EditText>(R.id.etEmail)
//        val passwordField = findViewById<EditText>(R.id.etPassword)
//        val loginButton = findViewById<Button>(R.id.btnLogin)
//
//        loginButton.setOnClickListener{
//
//            val username = username.text.toString().trim()
//            val password = passwordField.text.toString().trim()
//
//
////        val username = binding.etEmail.text.toString().trim()
////        val password = binding.etPassword.text.toString().trim()
//        if (username.isEmpty() || password.isEmpty()) {
//            showAlert("Invalid Input", "Please enter username and password")
//            return@setOnClickListener
//        }
//
////        viewModel.login(username, password)
//        loginUser(username, password)
//        }
//    }
//
////    private fun handleState(state: ApiState<LoginData>) {
////        when (state.state) {
////            State.Loading -> showLoading()
////            State.Success -> {
////                AppPref.get().storeProfile(this, state.data!!.profile)
////                AppEncryptedPref.get().storeToken(this, state.data.token)
////                setResult(RESULT_OK)
////                finish()
////            }
////
////            State.Error -> {
////                hideLoading()
////                showAlert("Error", state.message ?: "Unexpected Error")
////            }
////
////            else -> {}
////        }
////    }
//
//    private fun loginUser(email: String, password: String) {
//        auth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    startActivity(Intent(this, MainActivity::class.java))
//
//
//                } else {
//                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            startActivity(Intent(this, MainActivity::class.java))
//            finish()
//        }
//
//    }
//}