package com.gmail.sv.marinova.prayerjournal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gmail.sv.marinova.prayerjournal.databinding.FragmentFirstBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.LocalDateTime

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var prayerLogViewModel: PrayerLogViewModel

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        prayerLogViewModel = ViewModelProvider(this).get(PrayerLogViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            MaterialAlertDialogBuilder(requireContext())
                .setMessage(getString(R.string.messageConfirmSave))
                .setPositiveButton("Save") { dialog, which ->
                 var editPraise = view.findViewById<EditText?>(R.id.editPraise)
                    var editThanks = view.findViewById<EditText?>(R.id.editThanks)
                    var editConsfess = view.findViewById<EditText?>(R.id.editConfess)
                    var editPetition = view.findViewById<EditText?>(R.id.editPetition)
                    var editOthers = view.findViewById<EditText?>(R.id.editOthers)
                    val prayer = PrayerLog(LocalDateTime.now().toString(),editPraise.text.toString(), editThanks.toString(), editConsfess.toString(), editPetition.toString(), editOthers.toString()  )
                    prayerLogViewModel.insert(prayer)
                    Toast.makeText(requireContext(), getString(R.string.messageSaved), Toast.LENGTH_LONG).show()
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