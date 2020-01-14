package com.example.stage

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_cards.view.*


class RandoListAdapter//ajouter un constructeur prenant en entrée une liste
    (internal var list: List<MyObject>) : RecyclerView.Adapter<MyViewHolder>() {

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_cards, viewGroup, false)

//        view.setOnClickListener {
//            val c = view.context
////            Log.d("glagRando",list[itemType].name)
//            val intentRe = Intent (c, TripDetailActivity::class.java)
//            c.startActivity(intentRe)
//        }
        return MyViewHolder(view)
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val myObject = list[position]
        myViewHolder.bind(myObject)

    }

    override fun getItemCount(): Int {
        return list.size
    }

}
