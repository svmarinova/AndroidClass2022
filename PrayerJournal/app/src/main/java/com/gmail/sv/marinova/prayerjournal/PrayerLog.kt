package com.gmail.sv.marinova.prayerjournal

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "prayerlogs")
class PrayerLog(@PrimaryKey
    val timestamp: String,
    val Praises: String,
    val Thanks: String,
    val Confession: String,
    val Petition: String,
    val Others: String) {
}