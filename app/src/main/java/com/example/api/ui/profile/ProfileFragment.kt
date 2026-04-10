package com.example.api.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.base.BaseFragment
import coil.load
import com.example.api.R
import com.example.api.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::inflate
) {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.profileResponse.observe(viewLifecycleOwner) {

            // ❌ যদি API fail হয়
            if (!it.isSuccessful || it.body() == null) return@observe

            val profile = it.body()!!

            binding.apply {
                nameTextView.text = profile.name ?: "N/A"
                emailTextView.text = profile.email ?: "N/A"
                roleTextView.text = profile.role ?: "N/A"
                txtId.text = profile.id?.toString() ?: "0"

                profileImageView.load(profile.avatar)
            }
        }
        binding.takeProfileBtn.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_fileUploadFragment)
        }
    }
}
