package com.example.triptogalsen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triptogalsen.R
import com.example.triptogalsen.models.DicoModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_dico.view.*

class DicoAdapter(val dico : List<DicoModel>) : RecyclerView.Adapter<DicoAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val french = itemView.french
        val wolof = itemView.wolof
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dico,parent,false)
        return  ViewHolder(inflater)

    }

    override fun getItemCount(): Int {
        return dico.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dico = dico[position]
        holder.french.text = dico.french
        holder.wolof.text = dico.wolof
    }
}