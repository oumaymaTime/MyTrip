package com.example.stage

import android.Manifest
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import android.annotation.SuppressLint
import android.app.*
import android.os.Build
import android.telephony.SmsManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import android.app.PendingIntent
import android.content.pm.PackageManager
import android.util.Log
import android.util.Log.d
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Switch
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.NotificationManager
import android.content.Context
import android.location.Location
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*


class HomeActivity : AppCompatActivity() {


    private val requestReceiveSms = 1
    private var notificationManager: NotificationManager? = null
    private val MY_PERMISSIONS_REQUEST_SEND_SMS = 1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigation)
//        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        rando.setOnClickListener {
            val intentRando = Intent(this, RandoActivity::class.java)
            startActivity(intentRando)
        }

        camp.setOnClickListener {
            val intentCamp = Intent(this, CampActivity::class.java)
            startActivity(intentCamp)
        }

        btn_alert.setOnClickListener(View.OnClickListener { v -> sendNotif(v) })

//        btn_alert.setOnClickListener {
//            fun onClick(v: View) {
//                addNotification()
//            }
//            @Suppress("DEPRECATION")
//            val scAddress: String? = null
//            val sentIntent: PendingIntent? = null
//            val deliveryIntent: PendingIntent? = null
//            val smsManager = SmsManager.getDefault()
//
//            smsManager.sendTextMessage(
//                "+21695063327", scAddress, "first test",
//                sentIntent, deliveryIntent
//            )
//
//            val dialogBuilder = AlertDialog.Builder(this)
//            dialogBuilder.setMessage("Votre alerte est bien envoyée")
//                // if the dialog is cancelable
//                .setCancelable(false)
//                .setNegativeButton("Cancel") { dialog, _ ->
//                    dialog.cancel()
//                }
//
//            val alert = dialogBuilder.create()
//            alert.setTitle("Notification")
//            alert.show()
//        }
//    }

    }


    private fun sendNotif(v: View?) {
        var resultIntent = Intent(this, CurrentPosActivity::class.java)

        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val builder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.messageicon) //set icon for notification
            .setContentTitle("ALERTE") //set title of notification
            .setContentText("I'm LOST In")//this is notification message
            .setContentIntent(resultPendingIntent)

        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        d("Hay12", "DCM12")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            builder.setChannelId("com.example.stage")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val channel = NotificationChannel(
                "com.example.stage",
                "stage",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(2, builder.build())


    }

}
