package com.gmail.sv.marinova.prayerjournal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.facebook.CallbackManager
import com.facebook.login.widget.LoginButton
import com.gmail.sv.marinova.prayerjournal.databinding.FragmentFirst2Binding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*
import com.facebook.FacebookException

import com.facebook.login.LoginResult

import com.facebook.FacebookCallback




/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class First2Fragment : Fragment() {

    private var _binding: FragmentFirst2Binding? = null
    private lateinit var prayerLogViewModel: PrayerLogViewModel
    private val EMAIL = "email"


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
                .setPositiveButton("Save") { _, _ ->
                    prayerLogViewModel.deleteAll()
                }
                .setNegativeButton("Cancel") { _, _ ->
                    // Respond to positive button press
                }
                .show()
        }
        var callbackManager = CallbackManager.Factory.create();
        var loginButton = view.findViewById<LoginButton>(R.id.login_button)
        loginButton?.setReadPermissions(Arrays.asList(EMAIL));
        loginButton?.setFragment(this);
        loginButton!!.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onCancel() {
                // App code
            }

            override fun onError(error: FacebookException) {
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage(error.message)
                    .setPositiveButton(R.string.textOk) { _, _ ->
                    }
                    .show()
                // App code
            }

            override fun onSuccess(result: LoginResult?) {
                //TODO("Not yet implemented")
                MaterialAlertDialogBuilder(requireContext())
                    .setMessage(getString(R.string.messageFacebookLoginSuccess))
                    .setPositiveButton("OK") { _, _ ->
                    }
                    .show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}