package com.example.aplikasikota

// Model data untuk satu laporan warga
data class Laporan(
    val judul: String,
    val lokasi: String,
    val tanggal: String,
    val kategori: String,
    val status: String
)
