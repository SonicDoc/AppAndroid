package com.yaderp.appsoft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yaderp.appsoft.activities.BuscarActivity
import com.yaderp.appsoft.activities.LoginActivity
import com.yaderp.appsoft.activities.ReservaActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))

    }

}
