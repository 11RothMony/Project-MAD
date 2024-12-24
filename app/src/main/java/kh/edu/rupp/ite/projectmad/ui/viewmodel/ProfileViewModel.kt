package kh.edu.rupp.ite.projectmad.ui.viewmodel

import android.util.Log

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kh.edu.rupp.ite.projectmad.data.api.client.ApiClient

import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.ProfileData
import kh.edu.rupp.ite.projectmad.data.model.ProfileUser
//import kh.edu.rupp.ite.projectmad.data.model.UserProfileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel : ViewModel() {


    private val _profileData = MutableLiveData<ApiState<ProfileUser>>()
    val profileData get() = _profileData



    fun fetchProfile() {

        var apiState = ApiState.loading<ProfileUser>()
        _profileData.postValue(apiState)


        viewModelScope.launch {


            apiState = try {
//                val profileResponse = ApiClient.get().apiService.loadProfile("Bearer $token")
//                Log.d("ViewModelProfile", "$profileResponse")

                val profileResponse = ApiClient.get().apiService.loadProfile()
                Log.d("ViewModelUnderIf", "$profileResponse")
                if (profileResponse.isSuccess()) {
//                    ApiState.success(profileResponse.data)
                    ApiState.success(profileResponse.data)


                } else {
                    Log.e("ErrorOnProfileViewModel", " viewmodel ${profileResponse.message}")
                    ApiState.error(profileResponse.message)
                }

            } catch (ex: Exception) {

                ApiState.error(ex.message)
            }

            withContext(Dispatchers.Main) {
                _profileData.postValue(apiState)
            }
        }

    }


}



