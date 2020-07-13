package com.depo.trask.data.network.responses

import com.depo.trask.data.db.entities.User

data class LoginResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)