package com.example.aplikasikota

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var root: LinearLayout
    private lateinit var toolbar: Toolbar
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        root = findViewById(R.id.root)
        toolbar = findViewById(R.id.toolbar)
        bottomNav = findViewById(R.id.bottomNav)

        // Pasang toolbar sebagai action bar
        setSupportActionBar(toolbar)

        // Beri padding atas sebesar status bar agar konten tidak tertimpa
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, bars.top, 0, 0)
            insets
        }

        // Tombol back di toolbar
        toolbar.setNavigationOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                bottomNav.selectedItemId = R.id.nav_home
            }
        }

        // Saat kembali dari Tracking Timeline, kembalikan judul toolbar
        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount == 0) {
                aturToolbar(bottomNav.selectedItemId)
            }
        }

        // Bottom navigation
        bottomNav.setOnItemSelectedListener { item ->
            supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_info -> InfoFragment()
                R.id.nav_status -> LaporFragment()
                R.id.nav_manage -> PantauFragment()
                else -> ProfileFragment()
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
            aturToolbar(item.itemId)
            true
        }

        if (savedInstanceState == null) {
            bottomNav.selectedItemId = R.id.nav_home
        } else {
            aturToolbar(bottomNav.selectedItemId)
        }
    }

    // Dipanggil dari PantauFragment (tombol Lihat Detail)
    fun bukaTracking() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, TrackingFragment())
            .addToBackStack(null)
            .commit()
        setToolbar("Pantau Tracking Timeline", tampil = true, back = true, logo = false)
    }

    private fun aturToolbar(id: Int) {
        when (id) {
            R.id.nav_home -> setToolbar("PekaKota", tampil = true, back = false, logo = true)
            R.id.nav_info -> setToolbar("Info", tampil = true, back = true, logo = false)
            R.id.nav_status -> setToolbar("Lapor Masalah", tampil = true, back = true, logo = false)
            R.id.nav_manage -> setToolbar("Pantau Laporan", tampil = true, back = true, logo = false)
            else -> setToolbar("", tampil = false, back = false, logo = false)
        }
    }

    private fun setToolbar(judul: String, tampil: Boolean, back: Boolean, logo: Boolean) {
        toolbar.visibility = if (tampil) View.VISIBLE else View.GONE
        supportActionBar?.title = judul
        toolbar.navigationIcon = if (back) ContextCompat.getDrawable(this, R.drawable.ic_back) else null
        toolbar.logo = if (logo) ContextCompat.getDrawable(this, R.drawable.ic_logo) else null
        // Tab Profile: area status bar ikut oranye sesuai desain
        root.setBackgroundColor(
            ContextCompat.getColor(this, if (tampil) R.color.deep_navy else R.color.vibrant_orange)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            konfirmasiLogout()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Dipakai oleh menu toolbar dan tombol logout di Profil
    fun konfirmasiLogout() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Yakin ingin keluar?")
            .setPositiveButton("Ya") { _, _ ->
                // Hapus sesi: isLogin kembali false
                getSharedPreferences("PekaKotaPref", MODE_PRIVATE).edit().clear().apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton("Batal", null)
            .show()
    }
}
