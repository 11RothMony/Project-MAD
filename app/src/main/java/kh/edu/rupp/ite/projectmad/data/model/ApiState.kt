package kh.edu.rupp.ite.projectmad.data.model


data class ApiState<T>(
    val state: State,
    val message: String?,
    val data: T?
) {
    companion object{
        fun <T> loading(): ApiState<T> {
            return ApiState(State.Loading, null, null)
        }
        fun <T> success(data: T?): ApiState<T> {
            return ApiState(State.Success , null, data)
        }
        fun <T> error(message: String?): ApiState<T> {
            return ApiState(State.Error, message, null)
        }
    }
}

enum class State {
     Loading, Success, Error,
}