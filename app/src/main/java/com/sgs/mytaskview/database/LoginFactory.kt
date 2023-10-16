package com.sgs.mytaskview.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginFactory (private val mainRepository: LoginRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(mainRepository) as T
    }
}