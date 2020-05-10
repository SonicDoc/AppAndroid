package com.yaderp.sonicdoc.models.reserve

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaderp.sonicdoc.R
import kotlinx.android.synthetic.main.layout_user.view.*

class ReserveAdapter (
    val reserves: List<Reserve>): RecyclerView.Adapter<ReserveAdapter.UserViewHolder>() {

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
        holder.view.Fecha.text = reserve.Fecha
        holder.view.Turno.text = reserve.Turno
        holder.view.textViewIsNew.visibility = if(reserve.Estado == 1) View.VISIBLE else View.INVISIBLE

        var img = R.drawable.hombre
        if(reserve.Sexo!=1){
            img = R.drawable.mujer
        }
        holder.view.imageUser.setImageResource(img)
    }

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}