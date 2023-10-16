package com.sgs.mytaskview.database

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sgs.mytaskview.database.LoginModel
import com.sgs.mytaskview.database.LoginRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private var repo: LoginRepo) : ViewModel() {

    suspend fun insert (myData : LoginModel) = repo.insert(myData)

    fun getMaterialListSiteWise(): Flow<List<LoginModel>?>? {
        return repo.getMaterialListSiteWise()
    }


    val loginResultLiveData = MutableLiveData<Boolean?>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val isUserValid = withContext(Dispatchers.IO) {
                repo.checkCredentials(username, password)
            }
            loginResultLiveData.postValue(isUserValid)
        }
    }



}
