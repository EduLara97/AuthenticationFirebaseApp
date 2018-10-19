package com.example.itlab.authenticationfirebaseapp.views.presenters

import android.util.Log
import com.example.itlab.authenticationfirebaseapp.views.activities.RegisterActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterPresenter(val view: RegisterActivity) {

    private val mAuth = FirebaseAuth.getInstance()!!

    val db = FirebaseDatabase.getInstance().reference

    fun createUserDBReference(uid: String, values: HashMap<String, Any>) {
        db.child("users/$uid").setValue(values)
    }

    fun toHashMap(name: String, email: String): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()
        hashMap["email"] = email
        hashMap["name"] = name
        return hashMap
    }

    fun register(email: String, password: String, name: String){
        if (!name.isEmpty() && !password.isEmpty() && !email.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(view, {
                task ->
                run {
                    if (task.isSuccessful) {
                        // val values = toHashMap(name, email)
                        // createUserDBReference(task.result!!.user.uid, values)
                        view.correctRegister()
                    } else {
                        Log.i("Error",task.exception.toString())
                        view.incorrectRegister("Error en el registro")
                    }
                }
            })

        } else {
            view.incorrectRegister("Ingresar credenciales")
        }

    }

    interface RegisterDelegate{
        fun correctRegister()
        fun incorrectRegister(mensaje:String)
    }

}