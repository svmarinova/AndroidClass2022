package com.gmail.sv.marinova.prayerjournal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [PrayerLog::class], version = 2)
abstract class PrayerLogRoomDatabase : RoomDatabase() {
    abstract fun PrayerLogDao(): PrayerLogDao

    companion object {
        private var prayerRoomInstance: PrayerLogRoomDatabase? = null
        fun getDatabase(context: Context): PrayerLogRoomDatabase? {
            if (prayerRoomInstance == null) {
                synchronized(PrayerLogRoomDatabase::class.java) {
                    if (prayerRoomInstance == null) {
                        prayerRoomInstance = databaseBuilder<PrayerLogRoomDatabase>(
                            context.applicationContext,
                            PrayerLogRoomDatabase::class.java,
                            "prayerlog_database"
                        ).build()
                    }
                }
            }
            return prayerRoomInstance
        }
    }
}