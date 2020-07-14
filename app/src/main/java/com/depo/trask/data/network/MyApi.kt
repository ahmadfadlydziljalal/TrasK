package com.depo.trask.data.network



import com.depo.trask.data.network.responses.LoginResponse
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
        operator fun invoke(): MyApi {
            return Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.106:8080/api/")
                .build()
                .create(MyApi::class.java)
        }
    }


}