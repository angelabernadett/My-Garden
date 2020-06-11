package com.babygirl.mygarden

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_add_reminder.*
import java.util.*
import android.app.AlarmManager
import androidx.core.content.ContextCompat.getSystemService
import android.app.PendingIntent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ArrayAdapter
import android.widget.Spinner

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.AdapterView
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.io.File
import java.io.FileInputStream





class AddReminder : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)

        val frec = arrayOf("Every day", "Every week", "Every two weeks")
        val day =
            arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        val adapter = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            frec // Array
        )
        val adapter2 = ArrayAdapter(
            this, // Context
            android.R.layout.simple_spinner_item, // Layout
            day // Array
        )

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        time_spinner.adapter = adapter
        day_spinner.adapter = adapter2
        time_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Display the selected item text on text view
                // text_view.text = "Spinner selected : ${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }
        day_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Display the selected item text on text view
                // text_view.text = "Spinner selected : ${parent.getItemAtPosition(position).toString()}"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }


        val day_spinner_val = day_spinner.selectedItem.toString()
        val time_spinner_val= time_spinner.selectedItem.toString()


        val bundle = getIntent().getExtras()
        if (bundle != null) {
            val name_plant = bundle.getString("name")
            val image_plant = bundle.getInt("image")
            val id_plant = bundle.getInt("id")
            imageView24.setImageResource(image_plant)
            textView23.setText(name_plant)
        }

        val id_plant = bundle.getInt("id")
        Log.d("babyyoda", "id plant is" +id_plant)

        button3.setOnClickListener {



            val x = Reminder(day_spinner.selectedItem.toString(), time_spinner.selectedItem.toString(), id_plant, getLastId2(this) )
            x.writeFile2(this)

            val i = Intent(this, Page2::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("id",14)
            startActivity(i)

        }

    }



}
fun getLastId2(ctx: Context):Int{
    try{
        val path = ctx.getExternalFilesDir(null)
        val letDirectory = File(path, "PLANTS_Folder")
        letDirectory.mkdirs()
        val file = File(letDirectory, "Reminder.txt")
        val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }

        val lastWord = inputAsString.substring(inputAsString.lastIndexOf(",") + 1)

        return lastWord.toInt()
    }
    catch(e: Exception){
        e.printStackTrace()
        return 0
    }
}

