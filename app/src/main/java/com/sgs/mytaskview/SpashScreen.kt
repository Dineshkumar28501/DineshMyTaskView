package com.sgs.mytaskview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SpashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash_screen)

        Handler().postDelayed({
            val intent = Intent(this@SpashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}