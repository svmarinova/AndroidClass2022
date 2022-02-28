package com.gmail.sv.marinova.prayerjournal

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.gmail.sv.marinova.prayerjournal.databinding.ActivityPrayerListBinding

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
