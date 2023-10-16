package com.sgs.mytaskview.database

import com.sgs.mytaskview.database.LoginDataBase
import com.sgs.mytaskview.database.LoginModel
import kotlinx.coroutines.flow.Flow

class LoginRepo(private var login : LoginDataBase) {

    suspend fun insert (myData : LoginModel) = login.loginDao().insert(myData)

    fun getMaterialListSiteWise(): Flow<List<LoginModel>?>? {
        return login.loginDao().getMaterialListSiteWise()
    }

    fun checkCredentials(username: String, password: String): Boolean? {
        val user = login.loginDao().valid(username, password)
        return user != null
    }


}