

package com.depo.trask.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.depo.trask.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}