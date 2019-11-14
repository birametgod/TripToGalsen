package com.example.triptogalsen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triptogalsen.R
import com.example.triptogalsen.models.ReligiousModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.religious_location_item.view.*

class ReligiousLocationAdapter(val religiousLocation : List<ReligiousModel>) : RecyclerView.Adapter<ReligiousLocationAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.my_card_view_religious_item
        val picture = itemView.location_religious_picture
        val name = itemView.name_location_religious
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.religious_location_item,parent,false)
        return  ViewHolder(inflater)

    }

    override fun getItemCount(): Int {
        return religiousLocation.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val religiousLocation = religiousLocation[position]
        //holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        Picasso.get().load(religiousLocation.image).into(holder.picture)
        holder.name.text = religiousLocation.name
    }
}