package com.yaderp.sonicdoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yaderp.sonicdoc.api.RetrofitClient
import com.yaderp.sonicdoc.models.Tipo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)

    }
}
