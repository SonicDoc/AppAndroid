package com.yaderp.appsoft.models

import android.view.View
import android.widget.TextView

interface ClickListener {
    fun onClick(vista: View, posicion:Int)
}

interface ClickTexView{
    fun onClick(textView: TextView, posicion: Int)
}