package com.example.itlab.authenticationfirebaseapp.View.Activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.itlab.authenticationfirebaseapp.Models.Contact
import com.example.itlab.authenticationfirebaseapp.R
import com.example.itlab.authenticationfirebaseapp.View.Adapters.ContactAdapter
import com.example.itlab.authenticationfirebaseapp.View.Presenters.TimelineDelegate
import com.example.itlab.authenticationfirebaseapp.View.Presenters.TimelinePresenter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_timeline.*

class Timeline : AppCompatActivity(), TimelineDelegate {

    val mAuth = FirebaseAuth.getInstance()
    val mPresenter = TimelinePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)
        tbTimeline.title = "Timeline"
        setSupportActionBar(tbTimeline)

        val uid = intent.getStringExtra("uid")
        mPresenter.setInitialReference(uid)

        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.signOut) {
            mAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContactsReady(contacts: List<Contact>) {
        recyclerView.adapter = ContactAdapter(contacts, this)
    }

}
