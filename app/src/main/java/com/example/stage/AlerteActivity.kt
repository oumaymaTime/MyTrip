package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView



class AlerteActivity : AppCompatActivity() {

    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alerte)
        textView = findViewById(R.id.textView2)
        //getting the notification message
        val message = intent.getStringExtra("message")
        textView.text = message
    }
}
