package com.gmail.sv.marinova.prayerjournal

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface PrayerLogDao {
    @Insert
    fun insert(prayerlog: PrayerLog)
}