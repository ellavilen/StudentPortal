package com.example.studentportal

import Portal
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_add.view.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.item_portal.*

const val ADD_PORTAL_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {

    private val portals = arrayListOf<Portal>()
    private val portalsAdapter = PortalAdapter(portals) { portalItem: Portal -> portalItemClicked(portalItem)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { startAddActivity()}

        portals.add(Portal(titleText = "Hva", linkText = "https://www.hva.nl/"))

        initViews()
    }

    private fun portalItemClicked(portalItem: Portal) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary))
        customTabsIntent.launchUrl(this, Uri.parse(portalItem.linkText))
    }

    private fun initViews() {

        // Initialize the recycler view with a linear layout manager, adapter
        rvPortals.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvPortals.adapter = portalsAdapter
        rvPortals.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
    }

    private fun startAddActivity(){
        val intent = Intent(this, AddActivity::class.java)
        startActivityForResult(intent, ADD_PORTAL_REQUEST_CODE)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ADD_PORTAL_REQUEST_CODE -> {
                    val portal = data!!.getParcelableExtra<Portal>(EXTRA_PORTAL)
                    portals.add(portal)
                    portalsAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}
