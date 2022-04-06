package com.ubaya.a160419162_muliahati.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419162_muliahati.R
import com.ubaya.a160419162_muliahati.model.Donasi
import com.ubaya.a160419162_muliahati.viewmodel.DonasiViewModel
import kotlinx.android.synthetic.main.fragment_donasi_list.*


class DonasiListFragment : Fragment() {

    private lateinit var donasiViewModel: DonasiViewModel
    private val donasiListAdapter = DonasiListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donasi_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        donasiViewModel = ViewModelProvider(this).get(DonasiViewModel::class.java)
        donasiViewModel.refresh()

        recViewDonasi.layoutManager = LinearLayoutManager(context)
        recViewDonasi.adapter = donasiListAdapter

        observeViewModel()
        refreshLayout.setOnRefreshListener {
            recViewDonasi.visibility = View.GONE
            txtErrorLoadDonasi.visibility = View.GONE
            progressLoadDonasi.visibility = View.VISIBLE
            donasiViewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        donasiViewModel.donasiLD.observe(viewLifecycleOwner){
            var donasi: ArrayList<Donasi> = arrayListOf()
            for (item in it){
                if(item.kategori == "donasi"){
                    donasi.add(item)
                }
            }
            donasiListAdapter.updateDonasiList(donasi)
        }
        donasiViewModel.donasiLoadErrorLD.observe(viewLifecycleOwner){
            txtErrorLoadDonasi.visibility = if(it) View.VISIBLE else View.GONE
        }
        donasiViewModel.loadingLD.observe(viewLifecycleOwner){
            if(it){
                recViewDonasi.visibility = View.GONE
                progressLoadDonasi.visibility = View.VISIBLE
            }
            else{
                recViewDonasi.visibility =View.VISIBLE
                progressLoadDonasi.visibility = View.GONE
            }
        }
    }

}