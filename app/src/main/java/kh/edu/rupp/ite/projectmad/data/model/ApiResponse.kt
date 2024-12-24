package kh.edu.rupp.ite.model

import kh.edu.rupp.ite.projectmad.global.AppConstant

data class ApiResponse<T>(
//    val token: String,
    val status: String,
    val message: String,
    val data: T?,
//    val pizzas: T?
) {

    fun isSuccess(): Boolean {
        return status == AppConstant.API_STATUS_SUCCESS
    }

//    fun body(): Any {
//        return status == AppConstant.API_STATUS_SUCCESS
//
//    }


}
