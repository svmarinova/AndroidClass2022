package com.gmail.sv.marinova.prayerjournal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.gmail.sv.marinova.prayerjournal.databinding.ActivityPrayerListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.LocalDateTime

const val PRAYER_PRAISES = "EXTRA_PRAYER_PRAISES"
const val PRAYER_THANKS = "EXTRA_PRAYER_THANKS"
const val PRAYER_CONFESSION = "EXTRA_PRAYER_CONFESSION"
const val PRAYER_PETITION = "EXTRA_PRAYER_PETITION"
const val PRAYER_OTHERS = "EXTRA_PRAYER_OTHERS"
const val POSITION_NOT_SET = -1

class PrayerListActivity : AppCompatActivity() {
    private lateinit var prayerLogViewModel: PrayerLogViewModel

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPrayerListBinding
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
            R.id.action_TOC -> {
                showTOCDialog()
                true
            }
            R.id.action_About -> {
                showAboutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showTOCDialog() {
        MaterialAlertDialogBuilder(this@PrayerListActivity)
            .setTitle(getString(R.string.titleTOC))
            .setMessage(R.string.messageTOC)
            .setPositiveButton(getString(R.string.textOk)) { dialog, which ->
            }
            .show()
    }
    private fun showAboutDialog() {
        MaterialAlertDialogBuilder(this@PrayerListActivity)
            .setTitle(getString(R.string.titleAbout))
            .setMessage(R.string.messageAbout)
            .setPositiveButton(getString(R.string.textOk)) { dialog, which ->
            }
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrayerListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_prayer_list)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        prayerLogViewModel = ViewModelProvider(this).get(PrayerLogViewModel::class.java)
        prayerLogViewModel.allPrayerLogs.observe(this, Observer { prayerLog ->
            prayerLog?.let {
                //set here
                var listPrayerLogs = findViewById<ListView>(R.id.listPrayerLogs)
                listPrayerLogs.adapter =
                    PrayerLogAdapter(this,prayerLog.toTypedArray())

                listPrayerLogs.setOnItemClickListener { parent, view, position, id ->
                    val activityIntent = Intent(this, MainActivity::class.java)
                    activityIntent.putExtra(PRAYER_PRAISES, prayerLog[position].Praises)
                    activityIntent.putExtra(PRAYER_THANKS, prayerLog[position].Thanks)
                    activityIntent.putExtra(PRAYER_CONFESSION, prayerLog[position].Confession)
                    activityIntent.putExtra(PRAYER_PETITION, prayerLog[position].Petition)
                    activityIntent.putExtra(PRAYER_OTHERS, prayerLog[position].Others)
                    startActivity(activityIntent)
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_prayer_list)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
