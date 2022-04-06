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
import com.ubaya.a160419162_muliahati.model.Zakat
import com.ubaya.a160419162_muliahati.viewmodel.DonasiViewModel
import com.ubaya.a160419162_muliahati.viewmodel.ZakatViewModel
import kotlinx.android.synthetic.main.fragment_donasi_list.*
import kotlinx.android.synthetic.main.fragment_donasi_list.refreshLayout
import kotlinx.android.synthetic.main.fragment_zakat_list.*


class ZakatListFragment : Fragment() {
    private lateinit var zakatViewModel: ZakatViewModel
    private val zakatListAdapter = ZakatListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zakat_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        zakatViewModel = ViewModelProvider(this).get(ZakatViewModel::class.java)
        zakatViewModel.refresh()

        recViewZakat.layoutManager = LinearLayoutManager(context)
        recViewZakat.adapter = zakatListAdapter

        observeViewModel()
        refreshLayout.setOnRefreshListener {
            recViewZakat.visibility = View.GONE
            txtErrorLoadZakat.visibility = View.GONE
            progressLoadZakat.visibility = View.VISIBLE
            zakatViewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
    private fun observeViewModel() {
        zakatViewModel.zakatLD.observe(viewLifecycleOwner){
            var zakat: ArrayList<Zakat> = arrayListOf()
            for (item in it){
                if(item.kategori == "zakat"){
                    zakat.add(item)
                }
            }
            zakatListAdapter.updateZakatList(zakat)
        }
        zakatViewModel.zakatLoadErrorLD.observe(viewLifecycleOwner){
            txtErrorLoadZakat.visibility = if(it) View.VISIBLE else View.GONE
        }
        zakatViewModel.loadingLD.observe(viewLifecycleOwner){
            if(it){
                recViewZakat.visibility = View.GONE
                progressLoadZakat.visibility = View.VISIBLE
            }
            else{
                recViewZakat.visibility =View.VISIBLE
                progressLoadZakat.visibility = View.GONE
            }
        }
    }

}