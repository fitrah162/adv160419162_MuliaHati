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

class DonasiDetailViewModel(application: Application) :AndroidViewModel(application) {
    val TAG ="volleyTag"
    private var queue: RequestQueue? = null
    val donasiLD = MutableLiveData<Donasi>()

    fun fetch(donasiID: String){

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://gist.githubusercontent.com/fitrah162/e356993740eb5932cb9b6f38b055f556/raw/7203f2ac7c0bcaa671a26f4392b4c3dd950b1481/donasi.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Donasi>>() {}.type
                val result = Gson().fromJson<ArrayList<Donasi>>(it, sType)
                for (item in result){
                    if(item.id == donasiID){
                        donasiLD.value = item
                    }
                }

                Log.d("showvolley", it)
            },
            {
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