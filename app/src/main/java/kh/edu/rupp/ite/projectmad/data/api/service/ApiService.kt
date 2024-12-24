package kh.edu.rupp.ite.projectmad.data.api.service

import kh.edu.rupp.ite.model.ApiResponse
import kh.edu.rupp.ite.projectmad.data.model.LoginData

import kh.edu.rupp.ite.projectmad.data.model.MenuListData
import kh.edu.rupp.ite.projectmad.data.model.ProfileData
import kh.edu.rupp.ite.projectmad.data.model.ProfileUser
//import kh.edu.rupp.ite.projectmad.data.model.UserProfileResponse

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService  {

//    @GET("5045-8098-4e99-b7f0")  // For production
   @GET("menu") //For testing
    suspend fun loadMenu(): ApiResponse<List<MenuListData>>

    @GET("user")
//    suspend fun loadProfile(@Header("Authorization") token: String): ApiResponse<ProfileData>
    suspend fun loadProfile(): ApiResponse<ProfileUser>

//    @FormUrlEncoded
//    @POST("login.json")
//    suspend fun login(@Field("username") username: String, @Field("password") password: String): ApiResponse<LoginData>


//    @POST("login")
//    suspend fun login(@Body request: String): ApiResponse<ProfileData>
}