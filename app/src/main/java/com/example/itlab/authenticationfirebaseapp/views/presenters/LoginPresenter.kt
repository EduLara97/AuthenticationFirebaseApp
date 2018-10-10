package com.example.itlab.authenticationfirebaseapp.views.presenters

import com.example.itlab.authenticationfirebaseapp.views.activities.LoginActivity

/**
 * Created by itlab on 10/9/18.
 */
class LoginPresenter(val view : LoginActivity) {

    // Declarar instancia de FirebaseAuth
    //private val mAuth = FirebaseAuth.getInstance()

    fun login(email : String, password : String){

        // Logear usuario en firebase

    }

    fun currentUser(){
        // verificar si el usuario ya esta logueado

    }

    interface LoginDelegate{
        fun correctLogin()

        fun incorrectLogin(mensaje : String)
    }
}