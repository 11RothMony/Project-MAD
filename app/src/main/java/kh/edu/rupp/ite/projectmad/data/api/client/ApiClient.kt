package kh.edu.rupp.ite.projectmad.data.api.client

import kh.edu.rupp.ite.projectmad.data.api.service.ApiService
import kh.edu.rupp.ite.projectmad.global.AppConstant
import kh.edu.rupp.ite.visitme.data.api.client.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {

    private val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(logInterceptor)
        .addInterceptor(HeaderInterceptor())
        .build()



    private val retrofit = Retrofit.Builder()
        .baseUrl(AppConstant.API_BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    companion object {
        private var instance: ApiClient? = null

        fun get(): ApiClient {
            if (instance == null) {
                instance = ApiClient()
            }
            return instance!!
        }
    }
}