package com.ubaya.a160419162_muliahati.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.a160419162_muliahati.R
import com.ubaya.a160419162_muliahati.util.loadImage
import kotlinx.android.synthetic.main.fragment_penggalang_detail.*


class PenggalangDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_penggalang_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var penggalangNama = ""
        var penggalangTentang = ""
        var penggalangFoto = ""
        arguments?.let {
            penggalangNama = PenggalangDetailFragmentArgs.fromBundle(requireArguments()).penggalangName
            penggalangTentang = PenggalangDetailFragmentArgs.fromBundle(requireArguments()).penggalangTentang
            penggalangFoto = PenggalangDetailFragmentArgs.fromBundle(requireArguments()).penggalangFoto
        }
        if(penggalangFoto=="-"){
            imgPenggalangDetail.setBackgroundResource(R.drawable.ic_baseline_person_50)
        }
        else if(penggalangFoto!="-"){
            imgPenggalangDetail.loadImage(penggalangFoto)
        }
        if(penggalangTentang=="-"){
            txtTentangPenggalangDetail.text = "pengguna belum menulis tentang"
        }
        else if(penggalangTentang !="-"){
            txtTentangPenggalangDetail.text = penggalangTentang
        }
        txtNamaPenggalangDetail.text = penggalangNama

    }

}