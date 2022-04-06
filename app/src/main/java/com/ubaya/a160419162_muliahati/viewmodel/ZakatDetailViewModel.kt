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
import com.ubaya.a160419162_muliahati.model.Zakat

class ZakatDetailViewModel(application: Application) :AndroidViewModel(application) {
    val TAG ="volleyTag"
    private var queue: RequestQueue? = null
    val zakatLD = MutableLiveData<Zakat>()

    fun fetch(zakatID: String){

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://gist.githubusercontent.com/fitrah162/e356993740eb5932cb9b6f38b055f556/raw/f0071fa34f618e4f3ed59e72ac8e88752dad28e9/donasi.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Zakat>>() {}.type
                val result = Gson().fromJson<ArrayList<Zakat>>(it, sType)
                for (item in result){
                    if(item.id == zakatID){
                        zakatLD.value = item
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