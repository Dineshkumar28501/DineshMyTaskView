package com.sgs.mytaskview.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDao {

    @Insert
    suspend fun insert(myData : LoginModel)

    @Query("select * from loginDatabase")
    fun getMaterialListSiteWise(): Flow<List<LoginModel>>

    @Query("SELECT * FROM loginDatabase WHERE username = :username AND password = :password")
    fun valid(username: String, password: String): LoginModel?

}