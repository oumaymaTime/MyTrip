package com.example.stage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import android.widget.TextView
import com.google.firebase.auth.FirebaseUser




class LoggedInActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var user: FirebaseUser? = null
    private var Email: TextView? = null
    private var logout: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_in)
        Email = findViewById<TextView>(R.id.profileEmail)
//        Uid = findViewById<TextView>(R.id.profileUid)
        mAuth = FirebaseAuth.getInstance()
        logout = findViewById<Button>(R.id.button_logout)
        user = mAuth!!.currentUser

        if (user != null) {
            val email = user!!.email
            val uid = user!!.uid
            Email!!.text = email
//            Uid!!.text = uid
        }

        var btnLogOut = findViewById<Button>(R.id.button_logout)

        btnLogOut.setOnClickListener{ view ->
            showMessage(view, "Logging Out...")
//            signOut()
            mAuth!!.signOut()
            finish()

            val intent = Intent (this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

    fun showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }