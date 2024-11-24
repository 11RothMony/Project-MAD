import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.edu.rupp.ite.model.ApiResponse
import kh.edu.rupp.ite.model.ApiState
import kh.edu.rupp.ite.model.State
import kh.edu.rupp.ite.projectmad.api.ApiManager
import kh.edu.rupp.ite.projectmad.model.ListMenuKotlin
import kotlinx.coroutines.launch

class ListMenuViewModel : ViewModel() {

//    private val repository = DataRepository()

    private val _dataMenuList = MutableLiveData<ApiState<ListMenuKotlin>>()
    val dataMenuList: LiveData<ApiState<ListMenuKotlin>> get() = _dataMenuList

//    private val _error = MutableLiveData<String?>()
//    val error: LiveData<String?> get() = _error

    fun loadMenu() {

        val apiService = ApiManager.getApiService()

        _dataMenuList.postValue(ApiState(State.loading, null))

        viewModelScope.launch {

            try {

                val menuResponse = apiService.loadMenu()

                if (menuResponse.isSuccess()) {

                    _dataMenuList.postValue(ApiState(State.success, menuResponse.data))

                    Log.e("ruppite", "[Load data from api ] Success: ${menuResponse.data}")
                    Log.e("ruppite", "[Load data from api ] Success: ${menuResponse.data}")

                } else {

                    Log.e("ruppite", "[Load ] error: ${menuResponse.message}")

                    _dataMenuList.postValue(ApiState(State.error, null))
                }
            } catch (ex: Exception) {
                Log.e("ruppite", "loadmenu $ex")
                _dataMenuList.postValue(ApiState(State.error, null))
            }
        }
    }
}


