package com.ubaya.a160419162_muliahati.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.a160419162_muliahati.R
import com.ubaya.a160419162_muliahati.util.loadImage
import com.ubaya.a160419162_muliahati.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_detail_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*


class DetailProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.fetch()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.profileLD.observe(viewLifecycleOwner) {
            val profile = viewModel.profileLD.value
            profile?.let {
                editBio.setText(it.bio)
                editFullName.setText(it.fullName)
                editDOB.setText(it.date_of_birth)
                editEmail.setText(it.email)
                imgProfileDetail.loadImage(it.photo_profile.toString())
            }
        }
    }

}