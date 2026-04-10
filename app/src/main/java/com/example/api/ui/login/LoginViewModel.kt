package com.example.api.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.models.login.RequestLogin
import com.example.api.data.models.login.ResponseLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.api.data.repositories.AuthRpositoris
import kotlinx.coroutines.launch
import retrofit2.Response


@HiltViewModel
    class LoginViewModel @Inject constructor
    (private val repo: AuthRpositoris) : ViewModel() {


     private var _response = MutableLiveData<Response<ResponseLogin>>()
        val loginResponse: LiveData<Response<ResponseLogin>> = _response



    fun login(requestLogin: RequestLogin) {

        viewModelScope.launch {

            _response.postValue(repo.login(requestLogin))
        }
    }
}

