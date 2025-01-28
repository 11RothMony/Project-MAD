import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.projectmad.data.model.ApiState
import kh.edu.rupp.ite.projectmad.data.api.client.ApiClient
import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListMenuViewModel : ViewModel() {


    private val _menuListData = MutableLiveData<ApiState<List<MenuListData>>>()
    val menuListData get() = _menuListData

    private val _btn = MutableLiveData<Boolean>()
    val btn: MutableLiveData<Boolean> get() = _btn

    fun loadMenu() {

        var apiState = ApiState.loading<List<MenuListData>>()
        _menuListData.postValue(apiState)


        viewModelScope.launch {

            apiState = try {
                val menuResponse = ApiClient.get().apiService.loadMenu()


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


