package com.example.triptogalsen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triptogalsen.R
import com.example.triptogalsen.models.CloutLocationModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.famous_picture_item.view.*

class FamousPictureAdapter(val famousPicture : List<CloutLocationModel>, val itemClickListener: View.OnClickListener) : RecyclerView.Adapter<FamousPictureAdapter.ViewHolder>(){

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.my_card_view_picture
        val picture = itemView.picture_item_famous
        val location = itemView.location_picture
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.famous_picture_item,parent,false)
        return  ViewHolder(inflater)

    }

    override fun getItemCount(): Int {
        return famousPicture.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val famousPicture = famousPicture[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        Picasso.get().load(famousPicture.image).into(holder.picture)
        holder.location.text = famousPicture.location
    }
}