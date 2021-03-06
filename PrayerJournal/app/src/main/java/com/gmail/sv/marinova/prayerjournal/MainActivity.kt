package com.gmail.sv.marinova.prayerjournal

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ListView
import com.gmail.sv.marinova.prayerjournal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
        val Praises = intent.getStringExtra(PRAYER_PRAISES)
        val Thanks = intent.getStringExtra(PRAYER_THANKS)
        val Confess = intent.getStringExtra(PRAYER_CONFESSION)
        val Petition = intent.getStringExtra(PRAYER_PETITION)
        val Others = intent.getStringExtra(PRAYER_OTHERS)
        var editPraise = findViewById<EditText>(R.id.editPraise)
        editPraise.setText(Praises)
        var editThanks = findViewById<EditText>(R.id.editThanks)
        editThanks.setText(Thanks)
        var editConfess = findViewById<EditText>(R.id.editConfess)
        editConfess.setText(Confess)
        var editPetition = findViewById<EditText>(R.id.editPetition)
        editPetition.setText(Petition)
        var editOthers = findViewById<EditText>(R.id.editOthers)
        editOthers.setText(Others)

    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}