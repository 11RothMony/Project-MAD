package kh.edu.rupp.ite.projectmad.api

import kh.edu.rupp.ite.model.ApiResponse

import kh.edu.rupp.ite.projectmad.model.ListMenuKotlin
import retrofit2.http.GET

interface ApiService  {
    @GET("879b-293b-4171-b49a")
//    @GET("products")
    suspend fun loadMenu(): ApiResponse<ListMenuKotlin>
}