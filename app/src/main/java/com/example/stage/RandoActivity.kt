package com.example.stage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_add.*


class RandoActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var mAuth: FirebaseAuth? = null
    private var user: FirebaseUser? = null


    private val cities = arrayListOf<MyObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rando)
        setSupportActionBar(toolbar)
        mAuth = FirebaseAuth.getInstance()
        user = mAuth!!.currentUser

        //remplir la destination
        ajouterVilles()

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        recyclerView!!.adapter = RandoListAdapter(cities)

//        val btnLogOut = findViewById <ImageView> (R.id.logout)
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
                "Rando 1",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ",
                "Localisation1",
                "80 Dt",
                "https://www.belambra.fr/les-echappees/wp-inside/uploads/2017/04/faire-un-sac-de-randonnee-quoi-emporter-et-comment-le-charger.jpg"
            )
        )

        cities.add(
            MyObject(
                "Rando 2",
                 "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ",
                "Localisation2",
                "65 Dt",
                "https://upload.wikimedia.org/wikipedia/commons/9/91/EL_AMINE.jpg"
            )
        )
        cities.add(
            MyObject(
                "Rando 3",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ",
                "Localisation3",
                "65 Dt",
                "https://www.notretemps.com/cache/com_zoo_images/d2/randonnee-bienfaits_2f09e5a653be6d5220240825ceb02513.jpg"
            )
        )
        cities.add(
            MyObject(
                "Rando 4",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut ",
                "Localisation4",
                "30 Dt",
                "http://static.apidae-tourisme.com/filestore/objets-touristiques/images/84/50/733780.jpg"
            )
        )

    }


}
