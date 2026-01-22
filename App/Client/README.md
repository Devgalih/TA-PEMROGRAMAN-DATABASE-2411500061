# Android Client - Kotlin

## Requirements
- Android Studio Ladybugs (2024.2.1) atau lebih tinggi
- Android SDK 24 (Android 7.0) atau lebih tinggi
- Kotlin 1.9.20 atau lebih tinggi

## Setup

1. Buka project di Android Studio
2. Sync Gradle files
3. **PENTING**: Edit file `RetrofitClient.kt` dan ubah `BASE_URL` sesuai dengan URL server PHP Anda:
   ```kotlin
   // Untuk emulator Android, gunakan:
   private const val BASE_URL = "http://10.0.2.2/Rest%20API/"
   
   // Untuk device fisik di jaringan yang sama:
   private const val BASE_URL = "http://192.168.1.XXX/Rest%20API/"
   
   // Atau jika menggunakan server online:
   private const val BASE_URL = "https://yourdomain.com/Rest%20API/"
   ```

4. Pastikan server PHP sudah berjalan dan dapat diakses
5. Build dan run aplikasi

## Fitur

- **GET Request**: Mengambil semua items dari server
- **POST Request**: Menambahkan item baru ke server
- **RecyclerView**: Menampilkan daftar items dengan card layout
- **Material Design**: UI menggunakan Material Design 3
- **Error Handling**: Menampilkan pesan error jika terjadi masalah

## Struktur Project

```
app/src/main/java/com/mobileapp/database/
├── MainActivity.kt          # Activity utama
├── ApiService.kt            # Interface untuk API calls
├── RetrofitClient.kt        # Konfigurasi Retrofit
├── Models.kt                # Data models (Item, ItemRequest, ApiResponse)
└── ItemAdapter.kt           # Adapter untuk RecyclerView
```

## Dependencies

- Retrofit 2.9.0 - HTTP client untuk REST API
- Gson - JSON parsing
- OkHttp - HTTP client
- Coroutines - Async operations
- Material Design Components

## Testing

1. Pastikan server PHP sudah berjalan
2. Jalankan aplikasi di emulator atau device
3. Klik "Refresh List (GET)" untuk mengambil data
4. Isi form dan klik "Add Item (POST)" untuk menambahkan data baru

## Troubleshooting

### Error: "Unable to resolve host"
- Pastikan BASE_URL sudah benar
- Untuk emulator, gunakan `10.0.2.2` bukan `localhost`
- Pastikan server PHP dapat diakses dari device/emulator

### Error: "Connection refused"
- Pastikan server PHP sudah berjalan
- Cek firewall settings
- Pastikan port tidak diblokir

### Error: "CLEARTEXT communication not permitted"
- Aplikasi sudah dikonfigurasi untuk allow cleartext traffic
- Jika masih error, pastikan `android:usesCleartextTraffic="true"` ada di AndroidManifest.xml
