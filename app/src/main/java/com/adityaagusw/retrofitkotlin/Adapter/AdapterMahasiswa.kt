package com.adityaagusw.retrofitkotlin.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adityaagusw.retrofitkotlin.Activity.DetailActivity
import com.adityaagusw.retrofitkotlin.Model.Mahasiswa
import com.adityaagusw.retrofitkotlin.R

class AdapterMahasiswa(
    var context : Context, var listMahasiswa : List<Mahasiswa>) :
    RecyclerView.Adapter<AdapterMahasiswa.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false))

    }

    override fun getItemCount(): Int {
        return listMahasiswa.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = listMahasiswa[position]

        holder.rowNama.text = data.nama
        holder.rowJurusan.text = data.jurusan
        holder.rowAlamat.text = data.alamat

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("nama", data.nama)
            intent.putExtra("jurusan", data.jurusan)
            intent.putExtra("alamat", data.alamat)

            context.startActivity(intent)
        }

    }


    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val rowNama : TextView = item.findViewById(R.id.rowNama)
        val rowJurusan : TextView = item.findViewById(R.id.rowJurusan)
        val rowAlamat : TextView = item.findViewById(R.id.rowAlamat)
    }

}