package com.example.itlab.authenticationfirebaseapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginBtn = findViewById<View>(R.id.loginBtn) as Button
        val regTxt = findViewById<View>(R.id.regTxt) as TextView

        loginBtn.setOnClickListener({
            view -> login()
        })

        regTxt.setOnClickListener({
            view ->  register()
        })


    }

    private fun register() {
        startActivity(Intent(this, Register::class.java))
    }

    private fun login() {
        val emailTxt = findViewById<View>(R.id.emailTxt) as EditText
        val passwordTxt = findViewById<View>(R.id.passwordTxt) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()

        if(!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, {
                task ->
                run {
                    if (task.isSuccessful) {
                        startActivity(Intent(this, Timeline::class.java))
                    }else{
                        Toast.makeText(this,"Error", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }else{
            Toast.makeText(this, "Ingresar credenciales", Toast.LENGTH_LONG).show()
        }

    }
}
