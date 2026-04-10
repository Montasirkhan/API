package com.example.api.ui.upload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.models.Registration.RequestRagistration
import com.example.api.data.models.Registration.ResponseRegistration
import com.example.api.data.models.upload.ResponseUpload
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.api.data.repositories.AuthRpositoris
import com.example.api.data.repositories.uploadRpositoris
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import retrofit2.Response



@HiltViewModel
class UploadViewModel @Inject constructor(private val repo: uploadRpositoris) : ViewModel() {

    private var _response = MutableLiveData<Response<ResponseUpload>>()

    val uploadResponse: LiveData<Response<ResponseUpload>> = _response

    fun upload(file : MultipartBody.Part) {
        viewModelScope.launch {
            _response.postValue(repo.upload(file))
        }
    }
}

