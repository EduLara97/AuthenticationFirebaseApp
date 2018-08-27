package com.example.itlab.authenticationfirebaseapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        regBtn.setOnClickListener({
            register()
        })


    }

    private fun register() {

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        var name = nameTxt.text.toString()

        if(!name.isEmpty() && !password.isEmpty() && !email.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, {
                task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this,"Successfully signed in ", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,Timeline::class.java))
                    } else {
                        Log.i("ErrorFirebase",task.exception.toString())
                        Toast.makeText(this, "Error (Recordar que la contraseña debe tener al menos 6 caracteres)", Toast.LENGTH_LONG).show()
                    }

            })
        }else{
            Toast.makeText(this,"Ingresar credenciales", Toast.LENGTH_LONG).show()
        }
    }
}
