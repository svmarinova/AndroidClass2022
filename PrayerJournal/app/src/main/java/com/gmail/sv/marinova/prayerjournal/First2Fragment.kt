package com.gmail.sv.marinova.prayerjournal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.room.Database
import com.gmail.sv.marinova.prayerjournal.databinding.FragmentFirst2Binding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class First2Fragment : Fragment() {

    private var _binding: FragmentFirst2Binding? = null
    private lateinit var prayerLogViewModel: PrayerLogViewModel


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirst2Binding.inflate(inflater, container, false)
        prayerLogViewModel = ViewModelProvider(this).get(PrayerLogViewModel::class.java)

        return binding.root

    }
    fun Fragment.emptyDatabase() {
        prayerLogViewModel.deleteAll()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAdd.setOnClickListener {
            val activityIntent = Intent(activity,MainActivity::class.java)
            startActivity(activityIntent)
        }
        binding.btnDeleteAll.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(getString(R.string.messageConfirmDeleteAll))
                .setPositiveButton("Save") { dialog, which ->
                    prayerLogViewModel.deleteAll()
                }
                .setNegativeButton("Cancel") { dialog, which ->
                    // Respond to positive button press
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}