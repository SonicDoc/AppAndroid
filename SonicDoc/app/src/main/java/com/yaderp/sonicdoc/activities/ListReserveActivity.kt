package com.yaderp.sonicdoc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yaderp.sonicdoc.R
import com.yaderp.sonicdoc.api.RetrofitClient
import com.yaderp.sonicdoc.models.reserve.Reserve
import com.yaderp.sonicdoc.models.reserve.ReserveAdapter
import kotlinx.android.synthetic.main.activity_list_reserve.*
import retrofit2.Call
import retrofit2.Response

class ListReserveActivity : AppCompatActivity() {
    var msgLogin:String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_reserve)

        val intent: Intent = intent
        msgLogin = intent.getStringExtra("username")

        textTitulo.text = msgLogin

        buttonListar.setOnClickListener {
            cargarListareserva()
        }
    }

// funcion prueba para cargar el recyclerView de manera manual
    private fun cargarListareserva(){
        var listaReserva: ArrayList<Reserve> = ArrayList()

        listaReserva.add(Reserve(1,"Juan perez","12/06/2020","08:00 - 08:30",1,1))
        listaReserva.add(Reserve(1,"Marlene Chavez","12/06/2020","09:00 - 09:30",2,2))
        listaReserva.add(Reserve(1,"Maria Elena","15/06/2020","11:00 - 11:30",2,2))
        listaReserva.add(Reserve(1,"Armando Torpoco","16/06/2020","10:00 - 10:30",1,1))
        listaReserva.add(Reserve(1,"Carlos  Rosas","17/06/2020","13:00 - 13:30",1,1))
        listaReserva.add(Reserve(1,"Martina Del Cielo","18/06/2020","16:30 - 17:00",2,2))

        //listarReservas(listaReserva)
        recyclerViewReserves.layoutManager = LinearLayoutManager(this)
        recyclerViewReserves.adapter = ReserveAdapter(listaReserva)
    }

    // funcion para cargar la lista de reservas en el recyclerView
    private fun listarReservas(lista : List<Reserve>){

        recyclerViewReserves.layoutManager = LinearLayoutManager(this)
        recyclerViewReserves.adapter = ReserveAdapter(lista)
    }


    //Funcion captar reserva desde la api --> No funciona

    private fun getReserva(){

        RetrofitClient.instance.reserveUser(3).enqueue(object:
            retrofit2.Callback<List<Reserve>>
        {
            override fun onFailure(call: Call<List<Reserve>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<List<Reserve>>, response: Response<List<Reserve>>) {
                Toast.makeText(applicationContext, "Lista reserva", Toast.LENGTH_LONG).show()
            }
        })

    }
}

