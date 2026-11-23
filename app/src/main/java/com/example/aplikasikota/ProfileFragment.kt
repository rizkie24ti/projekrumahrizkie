package com.example.aplikasikota

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = requireContext().getSharedPreferences("PekaKotaPref", Context.MODE_PRIVATE)
        val username = pref.getString("username", "Warga") ?: "Warga"

        view.findViewById<TextView>(R.id.tvNama).text = username
        view.findViewById<TextView>(R.id.tvAvatar).text = username.take(1).uppercase()

        view.findViewById<View>(R.id.btnLogout).setOnClickListener {
            (activity as MainActivity).konfirmasiLogout()
        }
    }
}
