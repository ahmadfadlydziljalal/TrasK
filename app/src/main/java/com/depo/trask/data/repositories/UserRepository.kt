package com.depo.trask.data.repositories

import com.depo.trask.data.network.MyApi
import com.depo.trask.data.network.responses.LoginResponse
import retrofit2.Response

class UserRepository {

    suspend fun userLogin(username : String, password : String) : Response<LoginResponse>{
        return MyApi().userLogin(username,password)
    }

}