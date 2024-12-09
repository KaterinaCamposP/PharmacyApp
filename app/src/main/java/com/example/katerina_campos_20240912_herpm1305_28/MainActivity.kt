package com.example.katerina_campos_20240912_herpm1305_28

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.katerina_campos_20240912_herpm1305_28.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL
import com.example.katerina_campos_20240912_herpm1305_28.models.Pharmacy


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("userName", null)

        if (userName == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            binding.welcomeText.text = "Bienvenido: $userName"
            fetchPharmacies()
        }

        binding.logoutButton.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun fetchPharmacies() {
        binding.progressBar.visibility = View.VISIBLE

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = URL("https://midas.minsal.cl/farmacia_v2/WS/getLocalesTurnos.php").readText()

                val pharmacies: List<Pharmacy> = Gson().fromJson(response, object : TypeToken<List<Pharmacy>>() {}.type)

                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    binding.recyclerView.adapter = PharmacyAdapter(pharmacies)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}
