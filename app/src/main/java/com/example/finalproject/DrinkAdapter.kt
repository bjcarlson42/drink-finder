package com.example.finalproject

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.drink.view.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_three.*
import java.lang.reflect.Type
var myMediaPlayer : MediaPlayer? = null

class DrinkAdapter(val context: Context, val item: List<Drink>) : RecyclerView.Adapter<DrinkAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.drink, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val drinks = item[position]
        holder.bind(drinks)
    }

    override fun getItemCount() = item.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        init {

            // on long press, save the drink
            itemView.setOnLongClickListener {
                val drinkToAdd = item[adapterPosition].drink_name
                Toast.makeText(itemView.context, "Saved $drinkToAdd", Toast.LENGTH_LONG).show()

                var l = getArrayList("favs")
                if (l != null) {

                    if (!l.contains(drinkToAdd)) {
                        l.add(drinkToAdd)
                        saveArrayList(l, "favs")
                    }
                } else {
                    var list = ArrayList<String?>()
                    list.add(drinkToAdd)
                    saveArrayList(list, "favs")
                }
                myMediaPlayer = MediaPlayer.create(itemView.context, R.raw.liked)
                myMediaPlayer?.start()
                return@setOnLongClickListener true
            }

        }

        val FILE_NAME = "favList"
        // helper function to save https://stackoverflow.com/questions/7057845/save-arraylist-to-sharedpreferences
        fun saveArrayList(list: java.util.ArrayList<String?>?, key: String?) {
            val prefs = itemView.context.getSharedPreferences(FILE_NAME,
                AppCompatActivity.MODE_PRIVATE
            )
            val editor = prefs.edit()
            val gson = Gson()
            val json: String = gson.toJson(list)
            editor.putString(key, json)
            editor.apply()
        }

        // helper function to get https://stackoverflow.com/questions/7057845/save-arraylist-to-sharedpreferences
        fun getArrayList(key: String?): java.util.ArrayList<String?>? {
            val prefs = itemView.context.getSharedPreferences(FILE_NAME,
                AppCompatActivity.MODE_PRIVATE
            )
            val gson = Gson()
            val json: String? = prefs.getString(key, null)
            val type: Type = object : TypeToken<java.util.ArrayList<String?>?>() {}.getType()
            return gson.fromJson(json, type)
        }


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

            itemView.setOnClickListener {
                Log.d("TAGI", "clicked ${d.drink_name}")

                val myIntent = Intent(context, ActivitySix::class.java)
                myIntent.putExtra("drink", d.drink_name)
                context.startActivity(myIntent)
            }
        }
    }
}