package com.example.aplikasikota

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    // Data akun yang valid (hardcode untuk keperluan tugas)
    private val validUsername = "admin"
    private val validPassword = "12345"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Beri padding atas sebesar status bar agar toolbar tidak tertimpa
        ViewCompat.setOnApplyWindowInsetsListener(toolbar) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, bars.top, v.paddingRight, v.paddingBottom)
            insets
        }

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // 1. Cek apakah input kosong
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username dan password wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Cek kecocokan username dan password
            if (username == validUsername && password == validPassword) {

                // 3. Berhasil -> simpan isLogin = true di SharedPreferences
                val pref = getSharedPreferences("PekaKotaPref", MODE_PRIVATE)
                val editor = pref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()

                // 4. Pindah ke MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
