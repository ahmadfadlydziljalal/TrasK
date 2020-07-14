package com.depo.trask.ui.login

import com.depo.trask.data.db.entities.User

interface LoginListener {
    fun onStarted()
    fun onSuccess(user : User)
    fun onFailure(message : String)
}
