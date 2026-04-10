package com.example.api.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.models.Registration.RequestRagistration
import com.example.api.data.models.Registration.ResponseRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.api.data.repositories.AuthRpositoris
import kotlinx.coroutines.launch
import retrofit2.Response



@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repo: AuthRpositoris) : ViewModel() {

    private var _response = MutableLiveData<Response<ResponseRegistration>>()

    val registrationResponse: LiveData<Response<ResponseRegistration>> = _response

    fun registration(request: RequestRagistration) {
        viewModelScope.launch {
            _response.postValue(repo.registration(request))
        }
    }
}

