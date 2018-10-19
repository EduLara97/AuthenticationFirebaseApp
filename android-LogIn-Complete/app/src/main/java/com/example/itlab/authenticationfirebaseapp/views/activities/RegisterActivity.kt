package com.example.itlab.authenticationfirebaseapp.views.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.itlab.authenticationfirebaseapp.R
import com.example.itlab.authenticationfirebaseapp.views.presenters.RegisterPresenter
import com.example.itlab.authenticationfirebaseapp.views.presenters.RegisterPresenter.RegisterDelegate
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterDelegate {

    //private val mAuth = FirebaseAuth.getInstance()!!
    private val mPresenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        toolbar.setTitle(R.string.register)

        cancelTxt.setOnClickListener {
            onBackPressed()
        }

        regBtn.setOnClickListener {
            register()
        }
    }

    private fun register() {

        val email = emailTxt.text.toString()
        val password = passwordTxt.text.toString()
        val name = nameTxt.text.toString()

        mPresenter.register(email, password, name)


    }

    override fun correctRegister() {
        startActivity(Intent(this, TimelineActivity::class.java))
    }

    override fun incorrectRegister(mensaje:String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}
