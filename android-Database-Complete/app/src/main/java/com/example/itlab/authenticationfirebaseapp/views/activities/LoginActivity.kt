package com.example.itlab.authenticationfirebaseapp.views.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.itlab.authenticationfirebaseapp.R
import com.example.itlab.authenticationfirebaseapp.views.presenters.LoginPresenter
import com.example.itlab.authenticationfirebaseapp.views.presenters.LoginPresenter.LoginDelegate
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), LoginDelegate {

    val mPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPresenter.currentUser()

        loginBtn.setOnClickListener {
            progressBar.visibility = View.VISIBLE;
            loginBtn.text = ""
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
        mPresenter.login(email,password)
    }


    override fun incorrectLogin(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        progressBar.visibility = View.GONE
        loginBtn.text = getString(R.string.login_text_button)
    }

    override fun correctLogin() {
        emailTxt.setText("")
        passwordTxt.setText("")
        val intent = Intent(this, TimelineActivity::class.java)
        startActivity(intent)
        progressBar.visibility = View.GONE
        loginBtn.text = getString(R.string.login_text_button)
    }
}
