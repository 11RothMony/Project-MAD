package kh.edu.rupp.ite.projectmad.data.api.service

import kh.edu.rupp.ite.model.ApiResponse

import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import retrofit2.http.GET

interface ApiService  {
//        @GET("879b-293b-4171-b49a")
    @GET("5045-8098-4e99-b7f0")
    suspend fun loadMenu(): ApiResponse<List<MenuListData>>
}