package com.example.triptogalsen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triptogalsen.R
import com.example.triptogalsen.models.CloutLocationModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.clout_location_item.view.*

class CloutLocationAdapter(val cloutLocation : List<CloutLocationModel>) : RecyclerView.Adapter<CloutLocationAdapter.ViewHolder>(){

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.my_card_view
        val picture = itemView.picture_item
        val location = itemView.location
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.clout_location_item,parent,false)
        return  ViewHolder(inflater)

    }

    override fun getItemCount(): Int {
        return cloutLocation.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cloutLocation = cloutLocation[position]
        //holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        Picasso.get().load(cloutLocation.image).into(holder.picture)
        holder.location.text = cloutLocation.location
    }
}