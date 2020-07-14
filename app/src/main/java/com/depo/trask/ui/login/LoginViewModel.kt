package com.depo.trask.ui.login

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.depo.trask.data.repositories.UserRepository
import com.depo.trask.util.Coroutines

class LoginViewModel : ViewModel() {

    var username : String? = null
        get() = field
    var password : String? = null
        get() = field

    var loginListener: LoginListener? = null

    fun onClickButtonLogin(view: View) : Unit{
        loginListener?.onStarted()

        if(username.isNullOrEmpty() || password.isNullOrEmpty()){
            loginListener?.onFailure("Invalid username or password")
            return
        }

        Coroutines.main {
            val response = UserRepository().userLogin(username!!, password!!)
            if (response.isSuccessful){
                loginListener?.onSuccess(response.body()?.user!!)
            }else{
                loginListener?.onFailure("Error Code: ${response.code()} ${response.message()} ${response.headers()}")
            }
        }


    }

}