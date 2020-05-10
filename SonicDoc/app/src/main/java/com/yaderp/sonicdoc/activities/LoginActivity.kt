package com.yaderp.sonicdoc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yaderp.sonicdoc.R
import com.yaderp.sonicdoc.models.user.LoginResponse
import com.yaderp.sonicdoc.api.RetrofitClient
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

            RetrofitClient.instance.userLogin(username,password).enqueue(object:
                retrofit2.Callback<LoginResponse>
            {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if(!response.body()?.Error!!){
                        startActivity(Intent(this@LoginActivity, ListReserveActivity::class.java))
                    }else{
                        textMessaje.text = response.body()?.Mensaje!!
                    }

                }
            })


        }
    }
}
