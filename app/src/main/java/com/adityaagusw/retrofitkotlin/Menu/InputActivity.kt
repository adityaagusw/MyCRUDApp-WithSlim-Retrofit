package com.adityaagusw.retrofitkotlin.Menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.adityaagusw.retrofitkotlin.BackEnd.ApiClient
import com.adityaagusw.retrofitkotlin.Model.DefaultResponse
import com.adityaagusw.retrofitkotlin.R
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        visibleGone()

        btnKirimData.setOnClickListener {

            val nama1 = edtNama.text.toString().trim()
            val jurusan1 = edtJurusan.text.toString().trim()
            val alamat1 = edtAlamat.text.toString().trim()

            if (nama1.isEmpty() || jurusan1.isEmpty() || alamat1.isEmpty()) {
                Toast.makeText(applicationContext, "Masih kosong !", Toast.LENGTH_SHORT).show()
            } else {
                kirimDataUser()
            }

        }

    }

    private fun kirimDataUser() {

        visibleAvailable()

        val nama = edtNama.text.toString().trim()
        val jurusan = edtJurusan.text.toString().trim()
        val alamat = edtAlamat.text.toString().trim()

        ApiClient.instance.membuatUser(nama, jurusan, alamat)
            .enqueue(object : Callback<DefaultResponse> {

                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    visibleGone()
                }

                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT)
                        .show()
                    clearText()
                    visibleGone()
                }

            })

    }

    private fun visibleGone() {
        progressInput.visibility = View.GONE
    }

    private fun visibleAvailable(){
        progressInput.visibility = View.VISIBLE
    }

    private fun clearText() {
        edtNama.text.clear()
        edtJurusan.text.clear()
        edtAlamat.text.clear()
    }
}
