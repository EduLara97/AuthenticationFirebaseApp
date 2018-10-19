package com.example.itlab.authenticationfirebaseapp.views.presenters

import com.example.itlab.authenticationfirebaseapp.views.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth

/**
 * Created by itlab on 10/9/18.
 */
class LoginPresenter(val view : LoginActivity) {

    private val mAuth = FirebaseAuth.getInstance()

    fun login(email : String, password : String){

        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(view) { task ->
                run {
                    if (task.isSuccessful) {
                        view.correctLogin()
                    } else {
                        view.incorrectLogin("Fallo de autenticación")
                    }
                }
            }
        } else {
            view.incorrectLogin("Ingresar credenciales")

        }

    }

    fun currentUser(){
        if (mAuth.currentUser != null) {
            view.correctLogin()
        }

    }

    interface LoginDelegate{
        fun correctLogin()

        fun incorrectLogin(mensaje : String)
    }
}