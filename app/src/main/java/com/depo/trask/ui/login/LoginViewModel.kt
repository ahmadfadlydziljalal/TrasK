package com.depo.trask.ui.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.depo.trask.data.repositories.UserRepository
import com.depo.trask.util.ApiException
import com.depo.trask.util.Coroutines
import com.depo.trask.util.NoInternetException

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var username : String? = null
        get() = field
    var password : String? = null
        get() = field

    var loginListener: LoginListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onClickButtonLogin(view: View) : Unit{
        loginListener?.onStarted()

        if(username.isNullOrEmpty() || password.isNullOrEmpty()){
            loginListener?.onFailure("Invalid username or password")
            return
        }

        Coroutines.main {
            try {
                val loginResponse = repository.userLogin(username!!, password!!)
                loginResponse.user?.let {
                    loginListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }

                loginListener?.onFailure(loginResponse.message!!)

            }catch (e: ApiException){
                loginListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                loginListener?.onFailure(e.message!!)
            }
        }
    }
}