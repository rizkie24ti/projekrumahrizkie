package com.example.aplikasikota

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class PantauFragment : Fragment(R.layout.fragment_pantau) {

    // Data laporan warga (sesuai tema PekaKota)
    private val semuaLaporan = listOf(
        Laporan("Jalan berlubang besar", "Jl. Sudirman", "5 Mei 2022", "Jalan Rusak", "Diterima"),
        Laporan("Drainase tersumbat sampah", "Jl. Soekarno Hatta", "5 Mei 2022", "Drainase", "Diproses"),
        Laporan("Trotoar retak dan amblas", "Jl. Ahmad Yani", "8 Mei 2022", "Trotoar Rusak", "Diperbaiki"),
        Laporan("Lampu jalan mati", "Jl. Diponegoro", "9 Mei 2022", "Lampu Jalan", "Diterima"),
        Laporan("Jalan rusak dekat sekolah", "Jl. Imam Munandar", "10 Mei 2022", "Jalan Rusak", "Diproses"),
        Laporan("Got meluap saat hujan", "Jl. Tuanku Tambusai", "11 Mei 2022", "Drainase", "Selesai"),
        Laporan("Lampu taman padam", "Jl. Cut Nyak Dien", "12 Mei 2022", "Lampu Jalan", "Selesai"),
        Laporan("Trotoar dipakai parkir", "Jl. Gajah Mada", "13 Mei 2022", "Trotoar Rusak", "Selesai")
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.lvLaporan)
        val tvKosong = view.findViewById<TextView>(R.id.tvKosong)
        val etCari = view.findViewById<EditText>(R.id.etCariLaporan)

        // Pasang custom adapter ke ListView
        val adapter = LaporanAdapter(requireContext(), semuaLaporan)
        listView.adapter = adapter

        // Klik item -> buka Tracking Timeline
        listView.setOnItemClickListener { _, _, _, _ ->
            (activity as MainActivity).bukaTracking()
        }

        // Pencarian berdasarkan judul, lokasi, atau kategori
        etCari.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val kata = s.toString().trim().lowercase()
                val hasil = semuaLaporan.filter {
                    it.judul.lowercase().contains(kata) ||
                        it.lokasi.lowercase().contains(kata) ||
                        it.kategori.lowercase().contains(kata)
                }
                adapter.updateData(hasil)
                tvKosong.visibility = if (hasil.isEmpty()) View.VISIBLE else View.GONE
            }
        })
    }
}
