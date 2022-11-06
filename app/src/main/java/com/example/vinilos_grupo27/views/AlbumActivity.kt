package com.example.vinilos_grupo27.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.vinilos_grupo27.MainActivity
import com.example.vinilos_grupo27.R
import com.example.vinilos_grupo27.databinding.ActivityAlbumBinding
import com.example.vinilos_grupo27.databinding.ActivityMainBinding

class AlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAlbum.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}