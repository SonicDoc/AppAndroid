package com.yaderp.appsoft.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yaderp.appsoft.R
import com.yaderp.appsoft.api.RetrofitClient
import com.yaderp.appsoft.models.LoginResponse
import com.yaderp.appsoft.models.ReservaResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle(R.string.title_activity_login)

        buttonLogin.setOnClickListener {

            val username = editUsername.text.toString().trim()
            val password = editPassword.text.toString().trim()

            if(username.isEmpty()){
                editUsername.error = "username required"
                editUsername.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty()){
                editPassword.error = "Password required"
                editPassword.requestFocus()
                return@setOnClickListener
            }
            LoadLogin(username,password)
            //LoadReserve()
        }
    }

    private fun LoadLogin(username:String,password:String){
        RetrofitClient.instance.userLogin(username,password).enqueue(object:
            retrofit2.Callback<LoginResponse>
        {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(!response.body()?.Error!!){
                    var msgLogin:String?
                    var iduser:Int
                    var estado:Int
                    msgLogin= response.body()?.Mensaje!!
                    iduser = response.body()?.ID!!
                    estado = response.body()?.Estado!!
                    //Se realiza un intent para enviar elmensaje de bienvenida del API

                    val intent:Intent= Intent(this@LoginActivity, ListReserveActivity::class.java)
                    intent.putExtra("username", msgLogin)
                    intent.putExtra("iduser", iduser)
                    intent.putExtra("estado", estado)
                    startActivity(intent)
                }else{
                    textMessaje.text = response.body()?.Mensaje!!
                }

            }
        })
    }

}

