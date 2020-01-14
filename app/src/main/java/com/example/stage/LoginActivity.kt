package com.example.stage

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*
import com.google.android.gms.tasks.Task
import androidx.annotation.NonNull
import android.app.Activity
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.textInputEditTextEmail
import kotlinx.android.synthetic.main.activity_register.textInputEditTextPassword
import kotlinx.android.synthetic.main.activity_register.textInputLayoutEmail
import kotlinx.android.synthetic.main.activity_register.textInputLayoutPassword


class LoginActivity : AppCompatActivity() {

    private val activity = this@LoginActivity

    private var email: TextInputEditText? = null
    private var password: TextInputEditText? = null
    private var btnLogin: AppCompatButton? = null
    private var creationLink: AppCompatTextView? = null

    private var auth : FirebaseAuth?= null

    private val mAuthListener: FirebaseAuth.AuthStateListener? = null

    private lateinit var inputValidation: InputValidation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inputValidation = InputValidation(activity)

//        add my code
        auth = FirebaseAuth.getInstance()

        creationLink = findViewById(R.id.textViewLinkRegister) as AppCompatTextView
        btnLogin = findViewById(R.id.appCompatButtonLogin) as AppCompatButton
        password = findViewById(R.id.textInputEditTextPasswordLog) as TextInputEditText
        email = findViewById(R.id.textInputEditTextEmailLog) as TextInputEditText

        btnLogin!!.setOnClickListener(View.OnClickListener {view ->
            signIn(view,textInputEditTextEmailLog.text.toString(), textInputEditTextPasswordLog.text.toString())
        })
        creationLink!!.setOnClickListener(View.OnClickListener {
            val intentRe = Intent (this, RegisterActivity::class.java)
            startActivity(intentRe)
        })
    }
    public override fun onStart() {
        super.onStart()
        if (mAuthListener != null) {
            auth!!.addAuthStateListener(mAuthListener)
        }
    }

    fun signIn(view: View,email: String, password: String){

        Log.d("test",email.toString()+" "+password.toString())
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmailLog, textInputLayoutEmailLog, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPasswordLog, textInputLayoutPasswordLog, getString(R.string.error_message_password))) {
            return
        }
        else {
            showMessage(view, "Authenticating...")

            auth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                    if (task.isSuccessful) {
                        var intent = Intent(this, HomeActivity::class.java)
//                        intent.putExtra("id", auth!!.currentUser?.email)
                        startActivity(intent)
                        Log.d("test1","started")
                        finish()

                    }
//                    else {
//                        showMessage(view, "Error: ${task.exception?.message}")
//                    }
                })
        }

    }


    fun showMessage(view:View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

}