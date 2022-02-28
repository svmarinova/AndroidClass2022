package com.gmail.sv.marinova.prayerjournal

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query





@Dao
interface PrayerLogDao {
    @get:Query("SELECT * FROM prayerlogs")
    val allPrayerLogs: LiveData<List<PrayerLog>>

    @Query("DELETE FROM prayerlogs")
    fun delete()

    @Insert
    fun insert(prayerlog: PrayerLog)
}