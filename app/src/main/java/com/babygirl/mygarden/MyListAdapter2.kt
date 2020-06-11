package com.babygirl.mygarden

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
class MyListAdapter2(private val context: Activity, private val day: Array<String>, private val frequency: Array<String>)
    : ArrayAdapter<String>(context, R.layout.listview_item2, day) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.listview_item2, null, true)

        val titleText = rowView.findViewById(R.id.day) as TextView
        val subtitleText = rowView.findViewById(R.id.frequency) as TextView


        titleText.text = day[position]
        subtitleText.text = frequency[position]




        return rowView
    }
}
