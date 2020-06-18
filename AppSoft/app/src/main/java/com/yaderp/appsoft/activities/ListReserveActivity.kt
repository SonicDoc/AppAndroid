package com.yaderp.appsoft.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yaderp.appsoft.R
import com.yaderp.appsoft.api.RetrofitClient
import com.yaderp.appsoft.models.ReservaResponse
import com.yaderp.appsoft.models.ReserveAdapter
import kotlinx.android.synthetic.main.activity_list_reserve.*
import retrofit2.Call
import retrofit2.Response

class ListReserveActivity : AppCompatActivity() {
    var msgLogin:String =""
    var iduser:Int = 0
    var estado:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_reserve)

        val intent: Intent = intent
        msgLogin = intent.getStringExtra("username")
        iduser= intent.getIntExtra("iduser",0)
        estado = intent.getIntExtra("estado",0)

        setTitle(msgLogin)
        LoadReserve(estado,iduser)

        btnBack.setOnClickListener{
            startActivity(Intent(this@ListReserveActivity, LoginActivity::class.java))
        }

        btnHome.setOnClickListener {
            val intent:Intent= Intent(this@ListReserveActivity, BuscarActivity::class.java)
            intent.putExtra("iduser", iduser)
            startActivity(intent)
        }
    }

    private fun LoadReserve(estado:Int,IdUser: Int){
        //estado 1: Doctor
        //estado 2: Paciente/usuario
        if(estado==2){
            ListaPaciente(IdUser)
        }else{
            ListaDoctor(IdUser)
        }
    }

    private fun ListaPaciente(IdUser:Int){
        RetrofitClient.instance.docReserva(IdUser).enqueue(object:
            retrofit2.Callback<List<ReservaResponse>>
        {
            override fun onFailure(call: Call<List<ReservaResponse>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<ReservaResponse>>, response: Response<List<ReservaResponse>>) {
                val reserves = response.body()
                reserves?.let {
                    listarReservas(it)
                }
            }
        })
    }

    private fun ListaDoctor(IdUser:Int){
        RetrofitClient.instance.userReserva(IdUser).enqueue(object:
            retrofit2.Callback<List<ReservaResponse>>
        {
            override fun onFailure(call: Call<List<ReservaResponse>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<ReservaResponse>>, response: Response<List<ReservaResponse>>) {
                val reserves = response.body()
                reserves?.let {
                    listarReservas(it)
                }
            }
        })
    }

    private fun listarReservas(lista : List<ReservaResponse>){

        recyclerViewReserves.layoutManager = LinearLayoutManager(this)
        recyclerViewReserves.adapter = ReserveAdapter(lista)
    }
}
