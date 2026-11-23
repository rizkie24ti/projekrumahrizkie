package com.example.aplikasikota

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class TrackingFragment : Fragment(R.layout.fragment_tracking) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tvTerimaSub).text = "Laporan proses peninjauan"
    }
}
