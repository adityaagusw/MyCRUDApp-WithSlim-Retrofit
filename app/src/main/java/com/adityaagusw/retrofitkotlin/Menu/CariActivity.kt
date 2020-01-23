package com.adityaagusw.retrofitkotlin.Menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adityaagusw.retrofitkotlin.Adapter.AdapterMahasiswa
import com.adityaagusw.retrofitkotlin.BackEnd.ApiClient
import com.adityaagusw.retrofitkotlin.Model.DefaultResponse
import com.adityaagusw.retrofitkotlin.Model.GetUsers
import com.adityaagusw.retrofitkotlin.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CariActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari)

        recyclerView = findViewById(R.id.recyclerViewCari)
        recyclerView!!.layoutManager = LinearLayoutManager(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(nama: String?): Boolean {
        recyclerView!!.visibility = View.GONE

        if (nama != null) {

            ApiClient.instance.cariUser(nama)
                .enqueue(object : Callback<GetUsers> {
                    override fun onFailure(call: Call<GetUsers>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<GetUsers>, response: Response<GetUsers>) {
                        recyclerView!!.visibility = View.VISIBLE

                        val respon = response.body()!!.users
                        val adapter = AdapterMahasiswa(this@CariActivity, respon)
                        recyclerView!!.adapter = adapter


                    }

                })

        }

        return false

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_search, menu)
        val item = menu?.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(item) as SearchView

        searchView.queryHint = "Cari Nama Mahasiswa"
        searchView.isIconified = false
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    /*override fun onResume() {
        super.onResume()
        dataMahasiswa()
    }

    private fun dataMahasiswa(){
        ApiClient.instance.dapatUser()
            .enqueue(object : Callback<GetUsers>{
                override fun onFailure(call: Call<GetUsers>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<GetUsers>, response: Response<GetUsers>) {
                    val respon = response.body()!!.users
                    val adapter = AdapterMahasiswa(this@CariActivity, respon)
                    adapter.notifyDataSetChanged()
                    recyclerView!!.adapter = adapter
                }

            })
    }*/

}
