package com.depo.trask.data.repositories

import com.depo.trask.data.db.AppDatabase
import com.depo.trask.data.db.entities.User
import com.depo.trask.data.network.MyApi
import com.depo.trask.data.network.SafeApiRequest
import com.depo.trask.data.network.responses.LoginResponse


class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(username: String, password: String): LoginResponse {
        return apiRequest { api.userLogin(username, password) }
    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    suspend fun deleteUser() = db.getUserDao().delete()
    fun getUser() = db.getUserDao().getUser()

}