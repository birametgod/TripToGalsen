package com.example.triptogalsen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triptogalsen.R
import com.example.triptogalsen.models.EthnisModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.ethnis_item.view.*

class EthnisAdapter(val ethnis : List<EthnisModel>,val itemClickListener: View.OnClickListener) : RecyclerView.Adapter<EthnisAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.my_card_view_ethnis
        val picture = itemView.ethnis_picture
        val name = itemView.ethnis_name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.ethnis_item,parent,false)
        return  ViewHolder(inflater)

    }

    override fun getItemCount(): Int {
        return ethnis.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ethni = ethnis[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        Picasso.get().load(ethni.image).into(holder.picture)
        holder.name.text = ethni.name
    }
}