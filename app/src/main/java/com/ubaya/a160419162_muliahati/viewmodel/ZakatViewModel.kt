package com.ubaya.a160419162_muliahati.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.a160419162_muliahati.model.Donasi
import com.ubaya.a160419162_muliahati.model.Zakat

class ZakatViewModel(application: Application) :AndroidViewModel(application) {
    val TAG ="volleyTag"
    private var queue: RequestQueue? = null
    val zakatLD = MutableLiveData<ArrayList<Zakat>>()
    val zakatLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh(){
        zakatLoadErrorLD.value = false
        loadingLD.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://gist.githubusercontent.com/fitrah162/e356993740eb5932cb9b6f38b055f556/raw/7203f2ac7c0bcaa671a26f4392b4c3dd950b1481/donasi.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Zakat>>() {}.type
                val result = Gson().fromJson<ArrayList<Zakat>>(it, sType)
                zakatLD.value = result
                loadingLD.value = false
                Log.d("showvolley", it)
            },
            {
                zakatLoadErrorLD.value = true
                loadingLD.value = false
                Log.d("errorvolley",it.toString())
            }).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}