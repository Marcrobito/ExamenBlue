package com.marcrobito.examen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marcrobito.examen.module.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
