package com.adityaagusw.retrofitkotlin.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.adityaagusw.retrofitkotlin.BackEnd.ApiClient
import com.adityaagusw.retrofitkotlin.Model.DefaultResponse
import com.adityaagusw.retrofitkotlin.Model.Mahasiswa
import com.adityaagusw.retrofitkotlin.R
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var dtlsNama : TextView
    private lateinit var dtlsJurusan : TextView
    private lateinit var dtlsAlamat : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        dtlsNama = findViewById(R.id.dtlsNama)
        dtlsJurusan = findViewById(R.id.dtlsJurusan)
        dtlsAlamat = findViewById(R.id.dtlsAlamat)

        val nama = intent.getStringExtra("nama")
        val jurusan = intent.getStringExtra("jurusan")
        val alamat = intent.getStringExtra("alamat")

        dtlsNama.text = nama
        dtlsJurusan.text = jurusan
        dtlsAlamat.text = alamat

        btnUpdate.setOnClickListener {

            val namaUp = dtlsNama.text.toString().trim()
            val jurusanUp = dtlsJurusan.text.toString().trim()
            val alamatUp = dtlsAlamat.text.toString().trim()

            if (namaUp.isEmpty() || jurusanUp.isEmpty() || alamatUp.isEmpty()){
                Toast.makeText(applicationContext, "Update Kosong !", Toast.LENGTH_SHORT).show()
            }else{
                updateMahasiswa()
            }

        }

    }

    private fun updateMahasiswa() {
        val nama1 = dtlsNama.text.toString().trim()
        val jurusan1 = dtlsJurusan.text.toString().trim()
        val alamat1 = dtlsAlamat.text.toString().trim()

        ApiClient.instance.updateUser(nama1, jurusan1, alamat1)
            .enqueue(object : Callback<DefaultResponse>{
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
                    finish()
                }

            })

    }
}
