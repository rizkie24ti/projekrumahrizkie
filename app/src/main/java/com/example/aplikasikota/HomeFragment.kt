package com.example.aplikasikota

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = requireContext().getSharedPreferences("PekaKotaPref", Context.MODE_PRIVATE)
        val username = pref.getString("username", "Warga") ?: "Warga"

        view.findViewById<TextView>(R.id.tvUsername).text = username
        view.findViewById<TextView>(R.id.tvInisial).text = username.take(2).uppercase()
    }
}
