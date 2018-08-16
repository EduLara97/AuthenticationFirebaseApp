package com.example.itlab.authenticationfirebaseapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val regBtn = findViewById<View>(R.id.regBtn) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")


        regBtn.setOnClickListener({
            view -> register()
        })


    }

    private fun register() {
        val emailTxt = findViewById<View>(R.id.emailTxt) as EditText
        val passwordTxt = findViewById<View>(R.id.passwordTxt) as EditText
        val nameTxt = findViewById<View>(R.id.nameTxt) as EditText

        var email = emailTxt.text.toString()
        var password = passwordTxt.text.toString()
        var name = nameTxt.text.toString()

        if(!name.isEmpty() && !password.isEmpty() && !email.isEmpty()){
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, {
                task ->
                    if (task.isSuccessful) {
                        val user = mAuth.currentUser
                        val uid = user!!.uid
                        mDatabase.child(uid).child("Name").setValue(name)
                        Toast.makeText(this,"Successfully signed in ", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,Timeline::class.java));
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
