package com.example.api.ui.upload

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import coil.load
import com.base.BaseFragment
import com.example.api.databinding.FragmentFileUploadBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.utils.load
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream


@AndroidEntryPoint
class FileUploadFragment : BaseFragment<FragmentFileUploadBinding>(FragmentFileUploadBinding::inflate) {


   private val viewModel : UploadViewModel by viewModels()
   private var fileUri: Uri ?=null


    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                 fileUri  = data?.data!!

                 binding.profileImageView.setImageURI(fileUri)


                    binding.uploadAnImagebtn.visibility = View.VISIBLE

                }
                ImagePicker.Companion.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show()


                    binding.uploadAnImagebtn.visibility = View.VISIBLE

                }
                else -> {
                    Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       super.onViewCreated(view, savedInstanceState)

        viewModel.uploadResponse.observe(viewLifecycleOwner) {

            if (it.isSuccessful) {

                it.body()?.location?.let { it1 ->
                    binding.profilePreview.load(it1)
                }

            }

        }

      binding.pickAnImageBtn.setOnClickListener {


          ImagePicker.Companion.with(this)
              .compress(512)         //Final image size will be less than 1 MB(Optional)
              .maxResultSize(
                  512,
                  512
              )  //Final image resolution will be less than 1080 x 1080(Optional)

              .createIntent { intent ->
                  startForProfileImageResult.launch(intent)
              }

      }

      binding.uploadAnImagebtn.setOnClickListener {



      fileUri?.let { fileUri ->

          uploadFile(fileUri)

      }


      }

    }

    private fun uploadFile(fileUri: Uri) {

        val fileDir = requireActivity().filesDir
       val file = File(fileDir, "${System.currentTimeMillis()}..png")

       val inputStream = requireActivity().contentResolver.openInputStream(fileUri)

       val outputStream = FileOutputStream(file)

        inputStream?.copyTo(outputStream)

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())

        val part = MultipartBody.Part.createFormData("file", file.name, requestBody)

        viewModel.upload(part)

       inputStream?.close()
        outputStream.close()


    }
}