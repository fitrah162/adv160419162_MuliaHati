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
import com.ubaya.a160419162_muliahati.viewmodel.DonasiDetailViewModel
import com.ubaya.a160419162_muliahati.viewmodel.ZakatDetailViewModel
import kotlinx.android.synthetic.main.fragment_donasi_detail.*
import kotlinx.android.synthetic.main.fragment_zakat_detail.*


class ZakatDetailFragment : Fragment() {

    private lateinit var viewModel: ZakatDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zakat_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var zakatID = ""
        arguments?.let {
            zakatID = ZakatDetailFragmentArgs.fromBundle(requireArguments()).zakatID
        }
        viewModel = ViewModelProvider(this).get(ZakatDetailViewModel::class.java)
        viewModel.fetch(zakatID)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.zakatLD.observe(viewLifecycleOwner) {

            val donasiDetail = viewModel.zakatLD.value
            donasiDetail?.let {
                imgZakatDetail.loadImage(it.foto_donasi,1000,400)
                txtJudulZakatDetail.text = it.judulDonasi
                txtTerkumpulZakatDetail.text = "Rp. ${it.terkumpul}"

                if(it.target != 0){
                    txtTargetZakatDetail.text = "terkumpul dari Rp. ${it.target}"
                    progressZakatDetail.max = it.target!!
                    progressZakatDetail.progress = it.terkumpul.toInt()
                }
                else if(it.target == 0){
                    progressZakatDetail.max = 100
                    progressZakatDetail.progress = 100
                }
                if(it.penggalang.foto_penggalang != "-"){
                    imgPenggalangZakatDetail.loadImage(it.penggalang.foto_penggalang)

                }
                if(it.durasi !=0){
                    txtDurasiZakatDetail.text = it.durasi.toString()
                }
                else{
                    txtDurasiZakatDetail.text = Character.toString('\u221e')

                }
                txtDonaturZakatDetail.text = it.donatur.toString()
                txtPenggalangZakatDetail.text = it.penggalang.nama
                txtCeritaZakatDetail.text = it.cerita
                var penggalangNama = it.penggalang.nama.toString()
                var penggalangTentang = it.penggalang.tentang.toString()
                var penggalangFoto = it.penggalang.foto_penggalang.toString()
                cardPenggalangZakatDetail.setOnClickListener {
                    val action = ZakatDetailFragmentDirections.actionZakatDetailFragmentToPenggalangDetailFragment(penggalangNama,penggalangTentang,penggalangFoto)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }

}