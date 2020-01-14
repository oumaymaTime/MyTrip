package com.example.stage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.RadioButton
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_reservation.*
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_trip_details.*

class ReservationActivity : AppCompatActivity(), View.OnClickListener {

    private var mAuth: FirebaseAuth? = null
    private var user: FirebaseUser? = null

    private val activity = this

    private lateinit var appCompatButtonConfirm: AppCompatButton
    private lateinit var appCompatButtonReset: AppCompatButton
    private lateinit var acceptNotif: RadioButton

    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextTel: TextInputEditText
    private lateinit var textInputEditTextNumber: TextInputEditText
    private lateinit var textInputEditTextLastName: TextInputEditText
    private lateinit var textInputEditTextFirstName: TextInputEditText

    private lateinit var inputValidation: InputValidation

    var firebaseAuth: FirebaseAuth? = null
    var databaseReservation: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        mAuth = FirebaseAuth.getInstance()
        user = mAuth!!.currentUser


        firebaseAuth = FirebaseAuth.getInstance()
        databaseReservation = FirebaseDatabase.getInstance().getReference("reservation")

        textInputEditTextFirstName = findViewById(R.id.textInputEditTextFirstName)
        textInputEditTextLastName = findViewById<TextInputEditText>(R.id.textInputEditTextLastName)
        textInputEditTextEmail = findViewById<TextInputEditText>(R.id.textInputEditTextEmail)
        textInputEditTextNumber = findViewById<TextInputEditText>(R.id.textInputEditTextNumber)
        textInputEditTextTel = findViewById<TextInputEditText>(R.id.textInputEditTextTel)

        appCompatButtonConfirm = findViewById<AppCompatButton>(R.id.appCompatButtonConfirm)
        appCompatButtonReset = findViewById<AppCompatButton>(R.id.appCompatButtonReset)

        appCompatButtonReset.setOnClickListener(this)
        appCompatButtonConfirm.setOnClickListener(this)

        inputValidation = InputValidation(activity)

//        val btnLogOut = findViewById <ImageView> (R.id.logout)
//        if (user != null) {
//            btnLogOut.setOnClickListener { view ->
//                showMessage(view, "Logging Out...")
//
//                mAuth!!.signOut()
//                finish()
//
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//                finish()
//            }
//        }

        btn_menu_res.setOnClickListener {
            val popMenu: PopupMenu = PopupMenu(this,btn_menu_res)
            popMenu.menuInflater.inflate(R.menu.navigation,popMenu.menu)
            popMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
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
            popMenu.show()
        }
    }

    private fun confirmReservation(){
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextFirstName!!, textInputLayoutFirstName!!, getString(R.string.error_message_name))) {
            return
        }

        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextLastName!!, textInputLayoutLastName!!, getString(R.string.error_message_last_name))) {
            return
        }

        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail!!, textInputLayoutEmail!!, getString(R.string.error_message_email))) {
            return
        }

        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextNumber!!, textInputLayoutNumber!!, getString(R.string.error_message_nb_pers))) {
            return
        }

        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextTel!!, textInputLayoutTel!!, getString(R.string.error_message_phone))) {
            return
        } else {
            val id: String = databaseReservation?.push()?.key!!
            val reservation: Reservation =
                Reservation(
                    firstName = textInputEditTextFirstName.text.toString().trim(), lastName = textInputEditTextLastName.text.toString().trim(),
                    email = textInputEditTextEmail.text.toString().trim() ,
                    nbPer = textInputEditTextNumber.text.toString().trim() ,
                    phone = textInputEditTextTel.text.toString().trim())
            databaseReservation!!.child(id).setValue(reservation)

            val intentHom = Intent (this, HomeActivity::class.java)
            startActivity(intentHom)
        }

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.appCompatButtonConfirm -> confirmReservation()
        }
    }
}
