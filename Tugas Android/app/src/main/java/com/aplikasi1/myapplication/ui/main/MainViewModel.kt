package com.aplikasi1.myapplication.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aplikasi1.myapplication.api.ApiService
import com.aplikasi1.myapplication.model.UserListModel
import com.aplikasi1.myapplication.model.UserModel
import kotlinx.coroutines.launch

class MainViewModel(val apiService: ApiService) : ViewModel() {

    private var _users = MutableLiveData<List<UserModel>>()
    val users: LiveData<List<UserModel>> get() = _users

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val users = apiService.users()
                _users.postValue(users)
            } catch (e: Exception) {
                Log.e("MainViewModel", "getUsers: ${e.message.toString()}", )
            }
        }
    }

    companion object {
        fun provideFactory(apiService: ApiService): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(apiService) as T
            }
        }
    }
}