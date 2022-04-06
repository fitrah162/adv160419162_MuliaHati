package com.ubaya.a160419162_muliahati.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419162_muliahati.R
import com.ubaya.a160419162_muliahati.model.Donasi
import com.ubaya.a160419162_muliahati.util.loadImageDonasi
import kotlinx.android.synthetic.main.donasi_list_item.view.*
import kotlinx.android.synthetic.main.fragment_donasi_detail.view.*

class DonasiListAdapter(val donasiList: ArrayList<Donasi>):RecyclerView.Adapter<DonasiListAdapter.DonasiViewHolder>() {
class DonasiViewHolder(var view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DonasiViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.donasi_list_item, parent, false)
        return DonasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: DonasiViewHolder, position: Int) {
        val donasi = donasiList[position]
        with (holder.view){
            txtJudulDonasi.text = donasi.judulDonasi
            txtNamaPenggalangDonasi.text = donasi.penggalang.nama
            txtDonasiTerkumpul.text = "Rp. ${donasi.terkumpul.toString()}"
            txtSisaHariDonasi.text = "${donasi.durasi.toString()}"
            cardDonasiList.setOnClickListener {
                val action = DonasiListFragmentDirections.actionDonasiListFragmentToDonasiDetailFragment2(donasi.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            imgDonasiList.loadImageDonasi(donasi.foto_donasi,progressFotoDonasi)
        }
    }

    override fun getItemCount() =donasiList.size
    fun updateDonasiList (newDonasiList: ArrayList<Donasi>){
        donasiList.clear()
        donasiList.addAll(newDonasiList)
        notifyDataSetChanged()
    }
}