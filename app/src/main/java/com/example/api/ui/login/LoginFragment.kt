
package com.example.api.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.BaseFragment
import com.example.api.R
import com.example.api.data.models.login.RequestLogin
import com.example.api.databinding.FragmentLoginBinding
import com.utils.Keys
import com.utils.PrefManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var prefManager: PrefManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLogin()

        binding.RegistarBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // ✅ Input validation
            if (email.isEmpty()) {
                binding.etEmail.error = "Enter email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.etPassword.error = "Enter password"
                return@setOnClickListener
            }

            handleLogin(email, password)
        }
    }

    private fun observeLogin() {

        viewModel.loginResponse.observe(viewLifecycleOwner) {

            // 🔄 Loading OFF
            binding.loadingBar.visibility = View.GONE

            if (it.isSuccessful && it.body() != null) {

                val response = it.body()!!


                prefManager.setPref(Keys.ACCESS_TOKEN, response.accessToken ?: "")
                prefManager.setPref(Keys.REFRESH_TOKEN, response.refreshToken ?: "")

                Toast.makeText(requireContext(), "Login Success ✅", Toast.LENGTH_SHORT).show()


                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)

            } else {


                Toast.makeText(requireContext(), "Login Failed ❌", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun handleLogin(email: String, password: String) {

        // 🔄 Loading ON
        binding.loadingBar.visibility = View.VISIBLE

        val requestLogin = RequestLogin(email = email, password = password)

        viewModel.login(requestLogin)
    }
}
