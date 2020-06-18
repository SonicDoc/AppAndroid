package com.yaderp.appsoft.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yaderp.appsoft.R
import com.yaderp.appsoft.api.RetrofitClient
import com.yaderp.appsoft.models.ClickListener
import com.yaderp.appsoft.models.Doctor
import com.yaderp.appsoft.models.DoctorAdapter
import com.yaderp.appsoft.models.Tipo
import kotlinx.android.synthetic.main.activity_buscar.*
import retrofit2.Call
import retrofit2.Response

class BuscarActivity : AppCompatActivity() {
    var iduser:Int = 0
    var iddoc:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        val intent: Intent = intent
        iduser= intent.getIntExtra("iduser",0)

        setTitle("Buscar Doctor IdUser: "+ iduser)
        ApiEspecialidad()
    }

    private fun ApiEspecialidad(){
        RetrofitClient.instance.getEspecialidad().enqueue(object:
            retrofit2.Callback<List<Tipo>>
        {
            override fun onFailure(call: Call<List<Tipo>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error de Conexion", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Tipo>>, response: Response<List<Tipo>>) {

                val lista = response.body()
                if(lista==null){
                    Toast.makeText(applicationContext, "Valolores nulos", Toast.LENGTH_LONG).show()
                }else{
                    lista?.let {
                        LoadEspecialidad(it)
                    }
                }
            }
        })
    }

    private fun LoadEspecialidad(lista: List<Tipo>){
        var especialidad:ArrayList<String> = ArrayList()

        for(index in lista.indices){
            var nombre = lista.get(index).Nombres.toString()
            especialidad.add(nombre)
        }

        spEspec.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,especialidad)

        spEspec.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                ApiDoctores(lista.get(position).ID)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }


    //Carga doctores segun especialidad
    private fun ApiDoctores(IdEsp: Int){
        RetrofitClient.instance.getDoctor(IdEsp).enqueue(object:
            retrofit2.Callback<List<Doctor>>
        {
            override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
                val lista = response.body()
                if(lista==null){
                    Toast.makeText(applicationContext, "Valolores nulos", Toast.LENGTH_LONG).show()
                }else{
                    val doctores = response.body()
                    doctores?.let {
                        listarDoctores(it)
                    }
                }
            }

        })
    }

    private fun listarDoctores(lista : List<Doctor>){
        recyclerViewDoctor.layoutManager = LinearLayoutManager(this)
        recyclerViewDoctor.adapter = DoctorAdapter(lista as ArrayList<Doctor>,object :
            ClickListener {
            override fun onClick(vista: View, posicion: Int) {
                iddoc = lista.get(posicion).ID
                var nombre = lista.get(posicion).Nombres
                var espec = lista.get(posicion).Especialidad
                val intent:Intent= Intent(this@BuscarActivity, ReservaActivity::class.java)
                intent.putExtra("iddoc", iddoc)
                intent.putExtra("iduser", iduser)
                intent.putExtra("nombre", nombre)
                intent.putExtra("espec", espec)
                startActivity(intent)
            }

        })
    }
}
