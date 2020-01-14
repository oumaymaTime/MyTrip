package com.example.stage

import android.content.Intent
import android.util.Log
import android.util.Xml
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cell_cards.view.*
import android.widget.AdapterView.OnItemClickListener

class CampListAdapter (internal var list: List<MyObject>) : RecyclerView.Adapter<MyViewHolder>() {
    val glag: TextView?=null

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_cards, viewGroup, false)
//        view.setOnClickListener {
//
//            val c = view.context
//            val intentCamp = Intent (c, TripDetailActivity::class.java)
//            c.startActivity(intentCamp)
//        }
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val myObject = list[position]
        myViewHolder.bind(myObject)
//       Log.d("faditName",list[position].name.toString())
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
