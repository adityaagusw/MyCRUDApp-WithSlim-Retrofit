package com.adityaagusw.retrofitkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adityaagusw.retrofitkotlin.Menu.CariActivity
import com.adityaagusw.retrofitkotlin.Menu.InputActivity
import com.adityaagusw.retrofitkotlin.Menu.LihatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onListener()

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnInput -> {
                val intent = Intent(applicationContext, InputActivity::class.java)
                startActivity(intent)
            }
            R.id.btnLihatData -> {
                startActivity(Intent(applicationContext, LihatActivity::class.java))
            }
            R.id.btnCariData -> {
                startActivity(Intent(applicationContext, CariActivity::class.java))
            }
        }
    }

    private fun onListener() {
        btnInput.setOnClickListener(this)
        btnLihatData.setOnClickListener(this)
        btnCariData.setOnClickListener(this)
    }

}
