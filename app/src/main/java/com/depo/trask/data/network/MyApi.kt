package com.depo.trask.data.network



import com.depo.trask.data.network.responses.LoginResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {


    // suspend digunakan untuk pemanggilan via Coroutine
    // Artinya bisa di pause atau resume



    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<LoginResponse>


    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {

            val okkHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()


            val IPADDRESS = "http://192.168.0.106:8080/api/"
//            val IPADDRESS = "http://192.168.43.17:8080/api/"
            return Retrofit.Builder()
                .client(okkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(IPADDRESS)
                .build()
                .create(MyApi::class.java)
        }
    }


}