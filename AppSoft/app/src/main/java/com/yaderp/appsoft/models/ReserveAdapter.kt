package com.yaderp.appsoft.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaderp.appsoft.R
import kotlinx.android.synthetic.main.layout_user.view.*


class ReserveAdapter (val reserves: List<ReservaResponse>):
    RecyclerView.Adapter<ReserveAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_user, parent, false)
        )
    }
    override fun getItemCount() = reserves.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val reserve = reserves[position]
        holder.view.Nombres.text = reserve.Nombre
        holder.view.fecha.text = reserve.Fecha
        holder.view.Turno.text = reserve.Turno
        holder.view.Especialidad.text = reserve.Especialidad
        holder.view.textViewIsNew.visibility = if(reserve.Estado == 1) View.VISIBLE else View.INVISIBLE

        var img = R.drawable.doc
        if(reserve.Tipo==1){
            img = R.drawable.hombre
            if(reserve.Sexo==2){
                img = R.drawable.mujer
            }
        }else{
            img = R.drawable.doctor
            if(reserve.Sexo==2){
                img = R.drawable.doctora
            }
        }

        holder.view.imageUsuario.setImageResource(img)
    }

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}