package com.example.itlab.authenticationfirebaseapp.View.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.itlab.authenticationfirebaseapp.R
import com.example.itlab.authenticationfirebaseapp.View.Presenters.RegisterPresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()!!
    private val mPresenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        regBtn.setOnClickListener {
            register()
        }
    }

    private fun register() {

        val email = emailTxt.text.toString()
        val password = passwordTxt.text.toString()
        val name = nameTxt.text.toString()

        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            val task = mAuth.createUserWithEmailAndPassword(email, password)
            task.addOnSuccessListener(this) { result ->
                val values = mPresenter.toHashMap(name, email)
                mPresenter.createUserDBReference(result.user.uid, values)
                Toast.makeText(this, "Successfully signed in ", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, TimelineActivity::class.java))
            }
            task.addOnFailureListener(this) { err ->
                Log.i("ErrorFirebase", err.localizedMessage)
                Toast.makeText(this, "Error (Recordar que la contraseña debe tener al menos 6 caracteres)", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Ingresar credenciales", Toast.LENGTH_LONG).show()
        }
    }
}
