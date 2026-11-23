package com.example.aplikasikota

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat

// Custom adapter untuk menampilkan data Laporan dengan layout item_laporan
class LaporanAdapter(
    private val context: Context,
    private var data: List<Laporan>
) : BaseAdapter() {

    // ViewHolder: menyimpan referensi view agar findViewById tidak dipanggil berulang
    private class ViewHolder(view: View) {
        val tvJudul: TextView = view.findViewById(R.id.tvJudul)
        val tvLokasi: TextView = view.findViewById(R.id.tvLokasi)
        val tvTanggal: TextView = view.findViewById(R.id.tvTanggal)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun getCount(): Int = data.size
    override fun getItem(position: Int): Laporan = data[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_laporan, parent, false)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        val item = getItem(position)
        holder.tvJudul.text = item.judul
        holder.tvLokasi.text = "${item.kategori} • ${item.lokasi}"
        holder.tvTanggal.text = item.tanggal
        holder.tvStatus.text = item.status

        // Warna badge status mengikuti palette grand design
        val warna = when (item.status) {
            "Diterima" -> R.color.vibrant_orange
            "Diproses" -> R.color.slate_gray
            "Diperbaiki" -> R.color.gold
            else -> R.color.green_done
        }
        holder.tvStatus.backgroundTintList =
            android.content.res.ColorStateList.valueOf(ContextCompat.getColor(context, warna))

        return view
    }

    // Dipakai untuk fitur pencarian: ganti data lalu refresh tampilan
    fun updateData(dataBaru: List<Laporan>) {
        data = dataBaru
        notifyDataSetChanged()
    }
}
