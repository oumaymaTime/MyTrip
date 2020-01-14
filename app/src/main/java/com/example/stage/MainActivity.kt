package com.example.stage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth
import androidx.annotation.NonNull





class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        btn_logo_home.setOnClickListener{
            val intent = Intent (this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_started.setOnClickListener{
            if ( mAuth.currentUser == null) {
                val intentLog = Intent(this, LoginActivity::class.java)
                startActivity(intentLog)
                finish()
            }else{
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }
        }
    }
}
