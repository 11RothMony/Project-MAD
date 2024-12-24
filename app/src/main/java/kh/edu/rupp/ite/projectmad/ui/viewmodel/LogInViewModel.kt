package kh.edu.rupp.ite.projectmad.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kh.edu.rupp.ite.projectmad.data.api.client.ApiClient
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.LoginData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class LogInViewModel : ViewModel() {

    private val _logInData = MutableLiveData<ApiState<LoginData>>()
    val logInData get() = _logInData

    private lateinit var auth: FirebaseAuth

    //    fun login(token: String) {
//        var apiState = ApiState.loading<LoginData>()
//        _logInData.postValue(apiState)
//
//        viewModelScope.launch {
//            try {
//                /* val response = ApiClient.get().apiService.login(username, password) */
//                val response = ApiClient.get().apiService.login(token)
//                if (response.isSuccess()) {
//                    Log.d("vieModelLogin", "Success")
//                    ApiState.success(response.data)
//
//                } else {
//                    ApiState.error(response.message)
//                }
//            } catch (ex: Exception) {
//                ApiState.error(ex.message)
//            }
//
//            withContext(Dispatchers.Main) {
//                _logInData.postValue(apiState)
//            }
//        }
//    }
    fun login(email: String, password: String) {
        var apiState = ApiState.loading<LoginData>()
        _logInData.postValue(apiState)

//        auth = FirebaseAuth.getInstance()
        if (!::auth.isInitialized) {
            auth = FirebaseAuth.getInstance()
        }

        Log.d("ViewModelLogin", "Login")


        viewModelScope.launch {
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                if (result.user != null) {
//                        val user = task.result?.user
//                        val loginData = LoginData(user?.uid ?: "", user?.email ?: "")
                    val loginData = LoginData(result.user?.uid ?: "", result.user?.email ?: "")
                    apiState = ApiState.success(loginData)
                    Log.d("isSuccess", "Success")
                } else {
                    apiState = ApiState.error(apiState.message)
                    Log.e("ErrorViewModelLogin", "$apiState")

                }

            } catch (ex: Exception) {
                apiState = ApiState.error(ex.message)
                Log.e("ErrorViewModelLoginCatch", "$apiState")
            }

            withContext(Dispatchers.Main) {
                _logInData.postValue(apiState)
            }
        }
    }
}
