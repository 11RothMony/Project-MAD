package kh.edu.rupp.ite.model

import kh.edu.rupp.ite.projectmad.model.ListMenuKotlin

data class ApiResponse<T>(
    val status: String,
    val message: String,
    val data: T?,
//    val pizzas: List<ListMenuKotlin>
)
{

    fun isSuccess(): Boolean {
        return status == "success"
    }


}
