package com.ubaya.a160419162_muliahati.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419162_muliahati.R
import com.ubaya.a160419162_muliahati.model.Donasi
import com.ubaya.a160419162_muliahati.model.Zakat
import com.ubaya.a160419162_muliahati.util.loadImageDonasi
import kotlinx.android.synthetic.main.donasi_list_item.view.*
import kotlinx.android.synthetic.main.fragment_zakat_detail.*

class ZakatListAdapter(val zakatList: ArrayList<Zakat>): RecyclerView.Adapter<ZakatListAdapter.ZakatViewHolder>() {
    class ZakatViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZakatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.donasi_list_item, parent, false)
        return ZakatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ZakatViewHolder, position: Int) {
        val donasi = zakatList[position]
        with (holder.view){
            txtJudulDonasi.text = donasi.judulDonasi
            txtNamaPenggalangDonasi.text = donasi.penggalang.nama
            txtDonasiTerkumpul.text = "Rp. ${donasi.terkumpul.toString()}"
            cardDonasiList.setOnClickListener {
                val action = ZakatListFragmentDirections.actionZakatListFragmentToZakatDetailFragment(donasi.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            if(donasi.durasi !=0){
                txtSisaHariDonasi.text = donasi.durasi.toString()
            }
            else{
                txtSisaHariDonasi.text = Character.toString('\u221e')

            }
            imgDonasiList.loadImageDonasi(donasi.foto_donasi,progressFotoDonasi)
        }
    }

    override fun getItemCount() =zakatList.size
    fun updateZakatList (newZakatList: ArrayList<Zakat>){
        zakatList.clear()
        zakatList.addAll(newZakatList)
        notifyDataSetChanged()
    }
}