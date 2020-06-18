package com.yaderp.appsoft.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yaderp.appsoft.R
import com.yaderp.appsoft.api.RetrofitClient
import com.yaderp.appsoft.models.DefaultRespuesta
import com.yaderp.appsoft.models.LoginResponse
import com.yaderp.appsoft.models.Tipo
import kotlinx.android.synthetic.main.activity_buscar.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_reserva.*
import retrofit2.Call
import retrofit2.Response


class ReservaActivity : AppCompatActivity() {
    var iduser:Int = 0
    var iddoc:Int = 0
    var idturno:Int = 1
    var nombre:String=""
    var espec:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva)
        setTitle("Reservar")

        val intent: Intent = intent
        iduser= intent.getIntExtra("iduser",0)
        iddoc = intent.getIntExtra("iddoc",0)
        nombre = intent.getStringExtra("nombre")
        espec = intent.getStringExtra("espec")

        tvNombres.text = nombre
        tvEspecialidad.text = espec

        ApiTurno()
        btnTurno.setOnClickListener {
            ApiTurnoPath()
        }

        btnReservar.setOnClickListener{
            val fecha = etFecha.text.toString().trim()
            if(fecha.isEmpty()){
                etFecha.error = "Campo Obligatorio"
                etFecha.requestFocus()
                return@setOnClickListener
            }

            addReserva(fecha)
        }

    }

    private fun ApiTurno(){
        RetrofitClient.instance.getAllTurno().enqueue(object:
            retrofit2.Callback<List<Tipo>>
        {
            override fun onFailure(call: Call<List<Tipo>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Tipo>>, response: Response<List<Tipo>>) {
                var lista = response.body()
                if(lista==null){
                    Toast.makeText(applicationContext, "Valores nulos", Toast.LENGTH_LONG).show()
                }else{
                    lista?.let {
                        LoadTurno(it)
                    }
                }
            }

        })
    }

    private fun ApiTurnoPath(){
        RetrofitClient.instance.getTurnoDoc(1,"05/08/2020").enqueue(object:
            retrofit2.Callback<List<Tipo>>
        {
            override fun onFailure(call: Call<List<Tipo>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Tipo>>, response: Response<List<Tipo>>) {
                Toast.makeText(applicationContext, "ENTROO", Toast.LENGTH_LONG).show()
                /*
                var lista = response.body()
                if(lista==null){
                    Toast.makeText(applicationContext, "Valores nulos", Toast.LENGTH_LONG).show()
                }else{
                    lista?.let {
                        LoadTurno(it)
                    }
                }*/
            }

        })
    }

    private fun LoadTurno(lista: List<Tipo>){

        var vmlista:ArrayList<String> = ArrayList()

        for(index in lista.indices){
            var nombre = lista.get(index).Nombres.toString()
            vmlista.add(nombre)
        }

        spTurno.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,vmlista)
        spTurno.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                idturno = lista.get(position).ID
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }


    private fun addReserva(fecha:String){
        RetrofitClient.instance.setReserva(iddoc,iduser,fecha,idturno).enqueue(object:
            retrofit2.Callback<DefaultRespuesta> {
            override fun onFailure(call: Call<DefaultRespuesta>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(
                call: Call<DefaultRespuesta>,
                response: Response<DefaultRespuesta>
            ) {
                if(!response.body()?.error!!){
                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}
