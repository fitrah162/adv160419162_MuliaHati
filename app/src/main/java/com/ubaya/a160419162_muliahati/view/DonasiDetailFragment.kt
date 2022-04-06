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
import com.ubaya.a160419162_muliahati.util.loadImageDonasi
import com.ubaya.a160419162_muliahati.viewmodel.DonasiDetailViewModel
import com.ubaya.a160419162_muliahati.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.donasi_list_item.*
import kotlinx.android.synthetic.main.fragment_detail_profile.*
import kotlinx.android.synthetic.main.fragment_donasi_detail.*


class DonasiDetailFragment : Fragment() {

    private lateinit var viewModel: DonasiDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donasi_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var donasiID = ""
        arguments?.let {
            donasiID = DonasiDetailFragmentArgs.fromBundle(requireArguments()).donasiID
        }
        viewModel = ViewModelProvider(this).get(DonasiDetailViewModel::class.java)
        viewModel.fetch(donasiID)
        observeViewModel()
    }

    private fun observeViewModel() {

        viewModel.donasiLD.observe(viewLifecycleOwner) {
            val donasiDetail = viewModel.donasiLD.value
            donasiDetail?.let {
                imgDonasiDetail.loadImage(it.foto_donasi, 1000, 400)
                txtJudulDonasiDetail.text = it.judulDonasi
                txtTerkumpulDonasiDetail.text = "Rp. ${it.terkumpul}"
                if(it.target != 0){
                    txtTargetDonasiDetail.text = "terkumpul dari Rp. ${it.target}"
                }
                if(it.penggalang.foto_penggalang != "-"){
                    imgPenggalangDonasiDetail.loadImage(it.penggalang.foto_penggalang)

                }
                progressDonasiDetail.max = it.target!!
                progressDonasiDetail.progress = it.terkumpul.toInt()
                txtDurasiDonasiDetail.text = it.durasi.toString()
                txtPenggalangDonasiDetail.text = it.penggalang.nama
                txtCeritaDonasiDetail.text = it.cerita
                var penggalangNama = it.penggalang.nama.toString()
                var penggalangTentang = it.penggalang.tentang.toString()
                var penggalangFoto = it.penggalang.foto_penggalang.toString()
                cardPenggalangDonasiDetail.setOnClickListener {
                    val action = DonasiDetailFragmentDirections.actionDonasiDetailFragment2ToPenggalangDetailFragment(penggalangNama,penggalangTentang,penggalangFoto)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }



}