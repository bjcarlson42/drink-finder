package com.example.finalproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.drink.view.*

// Adapter for the list of restaurants

class DrinkAdapter(val context: Context, val item: List<Drink>) : RecyclerView.Adapter<DrinkAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.drink, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurants = item[position]
        holder.bind(restaurants)
    }

    override fun getItemCount() = item.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(d: Drink) {
            // set the values in the view to the actual values
            itemView.drink_name.text = d.drink_name

            //use a placeholder image if there is none. Else, use the actual image
            if (d.img == null || d.img.isEmpty()){
                Glide.with(itemView)
                    .load("https://via.placeholder.com/150")
                    .into(itemView.img)
            }
            else {
                Glide.with(itemView).load(d.img).into(itemView.img)
            }
        }
    }
}