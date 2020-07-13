package com.depo.trask.ui.login

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.depo.trask.data.repositories.UserRepository

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

        val loginResponse = UserRepository().userLogin(username!!, password!!)

        loginListener?.onSuccess(loginResponse)

    }

}