package com.example.triptogalsen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triptogalsen.R
import com.example.triptogalsen.models.Sites
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.touriste_item.view.*

class TouristiqueAdapter(val touristiqueSite : List<Sites>,val itemClickListener: View.OnClickListener) : RecyclerView.Adapter<TouristiqueAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.my_card_view_touriste
        val picture = itemView.picture_item_famous
        val location = itemView.location_picture
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.touriste_item,parent,false)
        return  ViewHolder(inflater)

    }

    override fun getItemCount(): Int {
        return touristiqueSite.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val site = touristiqueSite[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        Picasso.get().load(site.image).into(holder.picture)
        holder.location.text = site.location
    }
}