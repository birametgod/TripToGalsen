package com.example.triptogalsen.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.triptogalsen.R
import com.example.triptogalsen.models.GastroModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.gastro_item.view.*

class GastroAdapter( val gastroGalsen : List<GastroModel>,val itemClickListener: View.OnClickListener) : RecyclerView.Adapter<GastroAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.my_card_view_gastro
        val picture = itemView.gastro_picture
        val name = itemView.name_plat
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.gastro_item,parent,false)
        return  ViewHolder(inflater)

    }

    override fun getItemCount(): Int {
        return gastroGalsen.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = gastroGalsen[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        Picasso.get().load(food.image).into(holder.picture)
        holder.name.text = food.name
    }
}