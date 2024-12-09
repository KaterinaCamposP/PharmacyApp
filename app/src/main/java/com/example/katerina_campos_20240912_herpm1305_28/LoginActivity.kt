package com.example.katerina_campos_20240912_herpm1305_28

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.katerina_campos_20240912_herpm1305_28.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val userName = binding.userNameInput.text.toString()
            if (userName.isNotBlank()) {
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                sharedPreferences.edit().putString("userName", userName).apply()

                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
