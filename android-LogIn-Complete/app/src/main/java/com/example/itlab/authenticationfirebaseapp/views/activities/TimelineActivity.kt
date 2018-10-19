package com.example.itlab.authenticationfirebaseapp.views.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.itlab.authenticationfirebaseapp.models.Contact
import com.example.itlab.authenticationfirebaseapp.R
import com.example.itlab.authenticationfirebaseapp.views.adapters.ContactAdapter
import com.example.itlab.authenticationfirebaseapp.views.presenters.TimelineDelegate
import com.example.itlab.authenticationfirebaseapp.views.presenters.TimelinePresenter
import kotlinx.android.synthetic.main.activity_timeline.*

class TimelineActivity : AppCompatActivity(), TimelineDelegate {

    //private val mAuth = FirebaseAuth.getInstance()!!
    private val mPresenter = TimelinePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)
        tbTimeline.title = "TimelineActivity"
        setSupportActionBar(tbTimeline)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mPresenter.setInitialReference()
        addButton.setOnClickListener { addContact() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_logout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun addContact() {
        startActivity(Intent(this, FormActivity::class.java))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.signOut -> {
                /*mAuth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show()*/
            }
            R.id.camera ->{
                startActivity(Intent(this, MLActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContactsReady(contacts: List<Contact>) {
        recyclerView.adapter = ContactAdapter(contacts, this)
    }


}
