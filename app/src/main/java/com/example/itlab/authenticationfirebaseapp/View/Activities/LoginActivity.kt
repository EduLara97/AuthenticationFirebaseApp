package com.example.itlab.authenticationfirebaseapp.View.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.itlab.authenticationfirebaseapp.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (mAuth.currentUser != null) {
            endLogin()
        }

        loginBtn.setOnClickListener {
            login()
        }

        regTxt.setOnClickListener {
            register()
        }


    }

    private fun register() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun login() {

        val email = emailTxt.text.toString()
        val password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                run {
                    if (task.isSuccessful) {
                        endLogin()
                    } else {
                        Toast.makeText(this, "Fallo de autenticación",
                                Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Ingresar credenciales", Toast.LENGTH_LONG).show()
        }
    }

    private fun endLogin() {
        emailTxt.setText("")
        passwordTxt.setText("")
        val intent = Intent(this, TimelineActivity::class.java)
        startActivity(intent)
    }
}
