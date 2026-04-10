package com.example.api.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.models.profile.ResponseProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.api.data.repositories.userRpositoris
import kotlinx.coroutines.launch
import retrofit2.Response



@HiltViewModel
class ProfileViewModel
@Inject
constructor(private val repo: userRpositoris) : ViewModel() {

    private var _response = MutableLiveData<Response<ResponseProfile>>()
    val profileResponse: LiveData<Response<ResponseProfile>> = _response

    init {
        profile()
    }

    private fun profile() {
        viewModelScope.launch {
            try {
                val response = repo.profile()
                _response.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

