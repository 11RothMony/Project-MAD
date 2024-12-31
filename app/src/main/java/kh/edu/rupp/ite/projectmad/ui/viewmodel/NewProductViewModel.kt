package kh.edu.rupp.ite.projectmad.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.projectmad.data.api.client.ApiClient
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.data.model.MostPopularData
import kh.edu.rupp.ite.projectmad.data.model.NewProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

    class NewProductViewModel : ViewModel() {


        private val _menuListData = MutableLiveData<ApiState<List<NewProductData>>>()
        val menuListData get() = _menuListData


        fun loadNewProduct() {

            var apiState = ApiState.loading<List<NewProductData>>()
            _menuListData.postValue(apiState)

            viewModelScope.launch {

                apiState = try {
                    val menuResponse = ApiClient.get().apiService.loadNew()


                    if (menuResponse.isSuccess()) {
//                    ApiState.success(menuResponse.data)
                        ApiState.success(menuResponse.data)


                    } else {
                        Log.e("error"," ${menuResponse.message}")
                        ApiState.error(menuResponse.message)
                    }

                } catch (ex: Exception) {

                    ApiState.error(ex.message)
                }

                withContext(Dispatchers.Main) {
                    _menuListData.postValue(apiState)
                }
            }
        }

    }
