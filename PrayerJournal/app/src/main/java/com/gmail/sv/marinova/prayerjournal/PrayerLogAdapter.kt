package com.gmail.sv.marinova.prayerjournal

import android.app.Activity
import android.text.format.DateFormat
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PrayerLogAdapter(private val context: Activity, private val prayerLogList: Array<PrayerLog>)
    : BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.list_item,null)
        val textTimeStamp = rowView.findViewById<TextView>(R.id.textTimeStamp)
        val stringDate = prayerLogList[p0].timestamp
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        val dt = LocalDateTime.parse(stringDate);
        dt.format(formatter)
         textTimeStamp.setText("${dt.year}-${dt.month}-${dt.dayOfMonth} ${dt.hour}:${dt.minute}")
        return rowView
    }

    override fun getItem(p0: Int): Any {
        return prayerLogList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return prayerLogList.size
    }

}