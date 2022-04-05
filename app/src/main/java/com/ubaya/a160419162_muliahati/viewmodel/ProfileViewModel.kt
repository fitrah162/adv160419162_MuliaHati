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
import com.ubaya.a160419162_muliahati.model.Profile

class ProfileViewModel(application: Application):AndroidViewModel(application) {
    val TAG ="volleyTag"
    private var queue: RequestQueue? = null
    val profileLD = MutableLiveData<Profile>()
    fun fetch(){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://gist.githubusercontent.com/fitrah162/0f895b2a4704a476c40254ae994c100b/raw/3dfd7211c36acd3dd7d2a82b92f8e06ae4fcf840/profile.json"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Profile>() {}.type
                val result = Gson().fromJson<Profile>(it, sType)
                profileLD.value = result
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