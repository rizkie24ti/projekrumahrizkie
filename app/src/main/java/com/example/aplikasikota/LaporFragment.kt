package com.example.aplikasikota

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LaporFragment : Fragment(R.layout.fragment_lapor) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.boxUnggah).setOnClickListener {
            Toast.makeText(requireContext(), "Fitur unggah foto", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<Button>(R.id.btnSubmit).setOnClickListener {
            val lokasi = view.findViewById<EditText>(R.id.etCari).text.toString().trim()
            if (lokasi.isEmpty()) {
                Toast.makeText(requireContext(), "Tulis lokasi atau keluhan dulu", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Laporan berhasil dikirim", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
