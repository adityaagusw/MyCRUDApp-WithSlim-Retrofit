package com.adityaagusw.retrofitkotlin.Menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adityaagusw.retrofitkotlin.Adapter.AdapterMahasiswa
import com.adityaagusw.retrofitkotlin.BackEnd.ApiClient
import com.adityaagusw.retrofitkotlin.Model.GetUsers
import com.adityaagusw.retrofitkotlin.Model.Mahasiswa
import com.adityaagusw.retrofitkotlin.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LihatActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat)

        recyclerView = findViewById(R.id.recyclerViewLihat)
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        getData()

    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    private fun getData() {

        ApiClient.instance.dapatUser()
            .enqueue(object : Callback<GetUsers>{
                override fun onFailure(call: Call<GetUsers>, t: Throwable) {
                    Toast.makeText(applicationContext,t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<GetUsers>, response: Response<GetUsers>) {

                    if (response.isSuccessful){
                        val data = response.body()!!.users

                        val adapter = AdapterMahasiswa(this@LihatActivity, data)
                        adapter.notifyDataSetChanged()

                        recyclerView!!.adapter = adapter

                    }

                }

            })

    }
}