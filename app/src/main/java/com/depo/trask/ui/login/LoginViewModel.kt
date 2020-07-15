package com.depo.trask.ui.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.depo.trask.data.db.entities.User
import com.depo.trask.data.network.responses.LoginResponse
import com.depo.trask.data.repositories.UserRepository
import com.depo.trask.util.ApiException
import com.depo.trask.util.Coroutines
import com.depo.trask.util.NoInternetException

class LoginViewModel(
    private val repository: UserRepository
) : ViewModel() {


    suspend fun userLogin(
        username: String,
        password: String) = repository.userLogin(username, password)

    suspend fun saveLoggedInUser(user : User) = repository.saveUser(user)

    suspend fun deleteLoggedInUser() = repository.deleteUser()


    fun getLoggedInUser() = repository.getUser()



}