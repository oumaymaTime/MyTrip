package com.example.stage

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class MyViewHolder
    (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val textViewView: TextView = itemView.findViewById(R.id.trip_name_list)
    private val imageView: ImageView = itemView.findViewById(R.id.image) as ImageView

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    fun bind(myObject: MyObject) {
        textViewView.text = myObject.name
        Picasso.get().load(myObject.getImageUrl()).into(imageView)

        val cardTrip = itemView.findViewById <LinearLayout>(R.id.card_trip)


        val c = itemView.context

        cardTrip.setOnClickListener {
            val nameT = myObject.name.toString()
            val imgT = myObject.getImageUrl()
            val localisationT = myObject.localisation
            val price = myObject.price

            val intent = Intent(c, TripDetailActivity::class.java)

            intent.putExtra("message_key", nameT)
            intent.putExtra("message_img",imgT)
            intent.putExtra("message_local",localisationT)
            intent.putExtra("message_price",price)

            c.startActivity(intent)
        }
    }


}
