package com.yaderp.sonicdoc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yaderp.sonicdoc.R
import com.yaderp.sonicdoc.ReservaActivity


class MainActivity : AppCompatActivity() {

    //main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this@MainActivity, LoginActivity::class.java))

    }
}
