package com.gmail.sv.marinova.prayerjournal

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class PrayerLogViewModel(application: Application): AndroidViewModel(application) {
    val allPrayerLogs: LiveData<List<PrayerLog>>
    private val prayerLogDao: PrayerLogDao
    init {
        val prayerLogDb = PrayerLogRoomDatabase.getDatabase(application)
        prayerLogDao = prayerLogDb!!.PrayerLogDao()
        allPrayerLogs = prayerLogDao.allPrayerLogs
    }

    fun insert(prayerLog: PrayerLog) {
        InsertAsyncTask(prayerLogDao).execute(prayerLog)
    }
    fun deleteAll() {
        DeleteAllAsyncTask(prayerLogDao).execute()
    }
companion object {
    private class InsertAsyncTask(private val prayerLogDao: PrayerLogDao) : AsyncTask<PrayerLog, Void, Void>() {
        override fun doInBackground(vararg prayerLogs: PrayerLog): Void? {
            prayerLogDao.insert(prayerLogs[0])
            return null
        }
    }
    private class DeleteAllAsyncTask(private val prayerLogDao: PrayerLogDao) : AsyncTask<PrayerLog, Void, Void>() {
        override fun doInBackground(vararg prayerLogs: PrayerLog): Void? {
            prayerLogDao.delete()
            return null
        }
    }
}
}