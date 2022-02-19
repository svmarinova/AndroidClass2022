package com.gmail.sv.marinova.prayerjournal

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel

class PrayerLogViewModel(application: Application): AndroidViewModel(application) {
     private val prayerLogDao: PrayerLogDao
    init {
        val prayerLogDb = PrayerLogRoomDatabase.getDatabase(application)
        prayerLogDao = prayerLogDb!!.PrayerLogDao()
    }

    fun insert(prayerLog: PrayerLog) {
        InsertAsyncTask(prayerLogDao).execute(prayerLog)
    }
companion object {
    private class InsertAsyncTask(private val prayerLogDao: PrayerLogDao) : AsyncTask<PrayerLog, Void, Void>() {
        override fun doInBackground(vararg prayerLogs: PrayerLog): Void? {
            prayerLogDao.insert(prayerLogs[0])
            return null
        }
    }
}
}