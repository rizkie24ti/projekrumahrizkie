package com.example.aplikasikota

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Tampilkan splash selama 2,5 detik, lalu cek status login
        Handler(Looper.getMainLooper()).postDelayed({

            val pref = getSharedPreferences("PekaKotaPref", MODE_PRIVATE)
            val isLogin = pref.getBoolean("isLogin", false)

            // Jika sudah login -> MainActivity, jika belum -> Welcome -> Login
            val tujuan = if (isLogin) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, WelcomeActivity::class.java)
            }

            startActivity(tujuan)
            finish()
        }, 2500)
    }
}
