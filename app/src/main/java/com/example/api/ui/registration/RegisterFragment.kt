package com.example.api.ui.registration


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.BaseFragment
import com.example.api.R
import com.example.api.data.models.Registration.RequestRagistration
import com.example.api.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    val viewModel: RegistrationViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        viewModel.registrationResponse.observe(viewLifecycleOwner) {


            if (it.isSuccessful) {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

            }
        }

        binding.BtnRegister.setOnClickListener {


            val name = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val imageAvatar = "https://www.facebook.com/"



            val registerRequest = RequestRagistration(
                name = name,
                email = email,
                password = password,
                avatar = imageAvatar
            )

            viewModel.registration(registerRequest)


        }
    }
}