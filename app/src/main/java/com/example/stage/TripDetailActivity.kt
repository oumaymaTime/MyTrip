package com.example.stage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.Task
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_reservation.*
import kotlinx.android.synthetic.main.activity_trip_details.*
import kotlinx.android.synthetic.main.cell_cards.*
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_trip_details.btn_reserv
import kotlinx.android.synthetic.main.activity_trip_details.map_btn
import kotlinx.android.synthetic.main.activity_trip_details.name
import kotlinx.android.synthetic.main.cell_cards.view.*
import kotlinx.android.synthetic.main.recycle_items.*
import kotlinx.android.synthetic.main.recycle_items.toolbar as toolbar1


//@Suppress("UNREACHABLE_CODE")
class TripDetailActivity : AppCompatActivity() {

    var firebaseAuth: FirebaseAuth? = null
    var databaseTrip: DatabaseReference? = null

    private var user: FirebaseUser? = null

    var receiver_msg: TextView? = null
    var receiver_img: ImageView? = null
    var receiver_local: TextView? = null
    var receiver_price: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_details)

        firebaseAuth = FirebaseAuth.getInstance()
        databaseTrip = FirebaseDatabase.getInstance().getReference("trip")


        user = firebaseAuth!!.currentUser

        receiver_msg = findViewById(R.id.nameTrip)
        receiver_img = findViewById(R.id.imgT_detail)
        receiver_local = findViewById(R.id.localisation)
        receiver_price = findViewById(R.id.price)

        val intent = intent

        val nameT = intent.getStringExtra("message_key")
        val imgT = intent.getStringExtra("message_img")

        val localisationT = intent.getStringExtra("message_local")
        val price = intent.getStringExtra("message_price")

        // display the string into textView
        receiver_msg!!.text = nameT
        Picasso.get().load(imgT).into(receiver_img)

        receiver_local!!.text = localisationT
        receiver_price!!.text = price


//        logo_detail.setOnClickListener {
//            startActivity(Intent(this,HomeActivity::class.java))
//        }

        btn_reserv.setOnClickListener {
            if(user!=null){
                val intentReserv = Intent (this, ReservationActivity::class.java)
                startActivity(intentReserv)
            }else{
                startActivity(Intent(this,LoginActivity::class.java))
            }

        }


        map_btn.setOnClickListener {
            val intentMap = Intent(this, MapsActivity::class.java)
            startActivity(intentMap)
        }

        button.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,button)
            popupMenu.menuInflater.inflate(R.menu.navigation,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.nav_home ->
                        startActivity(Intent(this,HomeActivity::class.java))
                    R.id.nav_logout ->{

                        firebaseAuth!!.signOut()
                        finish()

                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                true
            })
            popupMenu.show()
        }

    }


}
