package com.example.itunesapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itunesapp.ListItunesData
import com.example.itunesapp.network.RetroService
import com.example.itunesapp.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class ActivityMainViewModel : ViewModel() {
    lateinit var recyclerListData : MutableLiveData<ListItunesData>
    init {
        recyclerListData = MutableLiveData()
    }

    fun getRecyclerListDataObserver()  : MutableLiveData<ListItunesData> {
        return recyclerListData
    }

    fun makeApiCall(input : String){
        val retroInstance = RetrofitInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getItunesDataFromAPI(input,"music")
        call.enqueue(object : retrofit2.Callback<ListItunesData>{
            override fun onResponse(call: Call<ListItunesData>, response: Response<ListItunesData>) {
                if(response.isSuccessful)
                {
                   // recyclerViewAdapter.setListData(response.body()?.items!!)
                   // recyclerViewAdapter.notifyDataSetChanged()
                    recyclerListData.postValue(response.body())
                }
                else
                    recyclerListData.postValue(null)
            }

            override fun onFailure(call: Call<ListItunesData>, t: Throwable) {

            }

        })
    }
}