package com.example.stage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_add.*


class CampActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    private var recyclerView: RecyclerView? = null

    private val cities = arrayListOf<MyObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camp)
        setSupportActionBar(toolbar)


        mAuth = FirebaseAuth.getInstance()
        user = mAuth!!.currentUser

        ajouterVilles()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        recyclerView!!.adapter = CampListAdapter(cities)

//        val btnLogOut = findViewById <ImageView> (R.id.logout_t)
//        if (user != null) {
//            btnLogOut.setOnClickListener { view ->
//                showMessage(view, "Logging Out...")
//
//                mAuth!!.signOut()
//                finish()
//
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//            }
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.nav_logout -> {
//                Toast.makeText(applicationContext, "click on logout", Toast.LENGTH_LONG).show()

                mAuth!!.signOut()
                finish()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            R.id.nav_home -> {
                startActivity(Intent(this, HomeActivity::class.java))
                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun ajouterVilles() {
        cities.add(
            MyObject(
                "Camp 1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ",
                "Localisation1",
                "65 Dt",
                "https://get.pxhere.com/photo/landscape-sea-nature-horizon-wilderness-mountain-cloud-sky-sunrise-sunset-sunlight-morning-hill-dawn-peak-mountain-range-evening-camping-tent-summit-camp-alps-landform-atmospheric-phenomenon-atmosphere-of-earth-mountainous-landforms-1398120.jpg"
            )
        )

        cities.add(
            MyObject(
                "Camp 2",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ",
                "Localisation2",
                "50 Dt",
                "https://theskirenters.com/wp-content/uploads/2014/06/unsplash-13-800x321.jpg"
            )
        )
        cities.add(
            MyObject(
                "Camp 3",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut",
                "Localisation3",
                "40 Dt",
                "http://www.linstant-m.tn/uploads/4086.png"
            )
        )
        cities.add(
            MyObject(
                "Camp 4",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ",
                "Localisation4",
                "30 Dt",
                "https://s3.amazonaws.com/imagescloud/images/medias/blogue/annexe-camping-econo-principale.jpg"
            )
        )

    }

}
