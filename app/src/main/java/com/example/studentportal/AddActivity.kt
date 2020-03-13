package com.example.studentportal

import Portal
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.content_add.*

const val EXTRA_PORTAL = "EXTRA_REMINDER"

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initView()
    }

    private fun initView(){
        fab.setOnClickListener { onSaveClick() }
    }

    private fun onSaveClick(){
        if (etAddTitle.text.toString().isNotBlank()){
            val title = Portal(etAddTitle.text.toString(), etAddLink.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL, title)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }else{
            Toast.makeText(this, "The title cannot be empty!" , Toast.LENGTH_SHORT).show()
        }

        if(etAddLink.text.toString().isNotBlank()) {
            val link = Portal(etAddLink.text.toString(), etAddLink.text.toString())
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PORTAL, link)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, "The link cannot be empty!" , Toast.LENGTH_SHORT).show()
        }
    }
}


