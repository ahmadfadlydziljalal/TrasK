package com.depo.trask.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.depo.trask.data.db.entities.CURRENT_USER_ID
import com.depo.trask.data.db.entities.User

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user : User) : Long

    @Query("SELECT * FROM user where uid = $CURRENT_USER_ID")
    fun getUser() : LiveData<User>

    @Query("DELETE FROM user WHERE uid = $CURRENT_USER_ID")
    fun delete()

}