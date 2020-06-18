package com.yaderp.appsoft.models

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaderp.appsoft.R
import kotlinx.android.synthetic.main.layout_doctor.view.*


class DoctorAdapter(items:ArrayList<Doctor>, var clickListener:ClickListener):RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>(){
    var items:ArrayList<Doctor>? = null
    var viewHolder: DoctorViewHolder?=null
    init{
        this.items= items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.layout_doctor, parent,false)
        viewHolder = DoctorViewHolder(vista, clickListener)
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = items?.get(position)
        holder.vista.Nombres.text = doctor?.Nombres
        holder.vista.Especialidad.text = doctor?.Especialidad

        var img = R.drawable.doctor
        if(doctor?.Sexo==2){
            img = R.drawable.doctora
        }
        holder.vista.imageDoctor.setImageResource(img)
    }

    class DoctorViewHolder(val vista:View,listener:ClickListener):RecyclerView.ViewHolder(vista),View.OnClickListener{

        //var vista = vista
        //var foto:ImageView?=null
        //var nombre:TextView?=null
        // direccion:TextView?=null
        // telefono:TextView?=null
        // califica:TextView?=null
        var listener:ClickListener?=null
        init{
            //foto = vista.imageViewFoto
            //nombre = vista.textViewNombres
            //direccion = vista.textViewDireccion
            //telefono = vista.textViewTelefono
            //califica = vista.textViewCalifica

            this.listener= listener
            vista.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!,adapterPosition)
        }
    }
}


/*
class DoctorAdapter (val doctores:List<Doctor>): RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        return DoctorViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_doctor, parent, false)
        )
    }

    override fun getItemCount()= doctores.size

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctores[position]
        holder.view.Nombres.text= doctor.Nombres
        holder.view.Especialidad.text = doctor.Especialidad

        var img = R.drawable.doctor
        if(doctor.Sexo==2){
            img = R.drawable.doctora
        }
        holder.view.imageDoctor.setImageResource(img)
    }

    class DoctorViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}*/