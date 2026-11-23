package com.example.aplikasikota

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment

class InfoFragment : Fragment(R.layout.fragment_info) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Data sederhana (list of String) sesuai tema PekaKota
        val dataInfo = listOf(
            "Perbaikan jalan rusak dimulai minggu ini",
            "Pembersihan drainase kota secara bertahap",
            "Jadwal perbaikan lampu jalan wilayah Pekanbaru",
            "Perbaikan trotoar di kawasan pusat kota",
            "Cara melapor: pilih kategori, unggah foto, lalu Submit",
            "Pantau status laporan lewat menu Manage",
            "Kumpulkan poin kontribusi dari setiap laporan",
            "Layanan darurat kota: hubungi 112"
        )

        // ListView + ArrayAdapter dengan layout bawaan simple_list_item_1
        val listView = view.findViewById<ListView>(R.id.lvInfo)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            dataInfo
        )
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(requireContext(), dataInfo[position], Toast.LENGTH_SHORT).show()
        }
    }
}
