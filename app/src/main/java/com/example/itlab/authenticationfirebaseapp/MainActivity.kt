package com.example.itlab.authenticationfirebaseapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        loginBtn.setOnClickListener({
            login()
        })

        regTxt.setOnClickListener({
            register()
        })


    }

    private fun register() {
        startActivity(Intent(this, Register::class.java))
    }

    private fun login() {

        val email = emailTxt.text.toString()
        val password = passwordTxt.text.toString()

        if(!email.isEmpty() && !password.isEmpty()){
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, {
                task ->
                run {
                    if (task.isSuccessful) {
                        emailTxt.setText("")
                        passwordTxt.setText("")
                        startActivity(Intent(this, Timeline::class.java))
                    }else{
                        Toast.makeText(this,"Fallo de autenticación",
                                Toast.LENGTH_LONG).show()
                    }
                }
            })
        }else{
            Toast.makeText(this, "Ingresar credenciales", Toast.LENGTH_LONG).show()
        }

    }
}
