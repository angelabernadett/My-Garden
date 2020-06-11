package com.babygirl.mygarden

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_add_reminder.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_plant_details.*
import kotlinx.android.synthetic.main.fragment_garden.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.android.synthetic.main.listview_item2.*
import java.io.*
import java.nio.file.Files.delete
import java.util.*


fun getListReminders(ctx: Context): MutableList<Reminder>{
    val x = mutableListOf<Reminder>()

    val path = ctx.getExternalFilesDir(null)
    val letDirectory = File(path, "PLANTS_Folder")
    letDirectory.mkdirs()
    val file = File(letDirectory, "Reminder.txt")
    var fileExists = file.exists()

    if(!fileExists){
        return mutableListOf<Reminder>()
    }
    val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }

    var db = false // debouncer

    val parts = inputAsString.split("\n")
    for (item in parts) {
        val words = item.split(",")


        if(db==false){ // skip the first line
            db=true
            continue
        }

        x.add(Reminder(words.get(0), words.get(1), words.get(2).toInt(),words.get(3).toInt()))
    }


    return x
}

var reminder_list  = mutableListOf<Reminder>()
class PlantDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_details)

        //reminder_list = getListReminders(this)

        val bundle=getIntent().getExtras()
        if(bundle!=null) {

            val name_plant = bundle.getString("name")
            val type_plant = bundle.getString("type")
            val image_plant = bundle.getInt("image")


            imageView30.setImageResource(image_plant)
            textView31.setText(name_plant)
            textView24.setText(type_plant)

        }


        reminder_list= getListReminders(this)

        button_back3.setOnClickListener {//add reminder

            val intent = Intent(this, AddReminder::class.java)
            intent.putExtra("image", bundle.getInt("image"))
            intent.putExtra("name", bundle.getString("name"))
            intent.putExtra("id", bundle.getInt("id"))
            startActivity(intent)

        }


        val id_plant= bundle.getInt("id")
        val global_i_list=  mutableListOf<Int>()
        val list_day = mutableListOf<String>()
        val list_frequency = mutableListOf<String>()
        var count_me = 0
        for( item in reminder_list) {
            if (item.id2 == id_plant) {
                global_i_list.add(item.idrem)
                list_day.add(item.day)
                list_frequency.add(item.frequency)
                //Log.d("babyyoda", count_me.toString())
                count_me += 1
            }
        }

        //for(item in list_day) Log.d("babyyoda","---"+item)

        val arr1 = list_day.filterNotNull().toTypedArray()
        val arr2 = list_frequency.filterNotNull().toTypedArray()

        val listView12 = listViewXL2

        val myListAdapter2 = MyListAdapter2(this,arr1,arr2)
        listView12.adapter = myListAdapter2

        listView12.setOnItemClickListener() { adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            deleteReminder(this, global_i_list.get(id.toInt()))


            finish()
            overridePendingTransition(0, 0)
            startActivity(getIntent())
            overridePendingTransition(0, 0)

        }

        button_back.setOnClickListener{//delete plant

            deletePlant(this, id_plant)
            deleteReminder2(this, id_plant)
            val i = Intent(this, Page2::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("id",14)
            startActivity(i)

        }



    }

}
fun deletePlant(ctx:Context, id_remover: Int){
    val path = ctx.getExternalFilesDir(null)
    val letDirectory = File(path, "PLANTS_Folder")
    letDirectory.mkdirs()
    val file = File(letDirectory, "Plants.txt")
    var fileExists = file.exists()

    val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }

    var db = false // debouncer

    val parts: MutableList<String> = inputAsString.split("\n") as MutableList<String>
    val iterator = parts.iterator()
    while (iterator.hasNext())
    {
        val value = iterator.next()
        val words = value.split(",")
        if(db==false){ // skip the first line
            db=true
            continue
        }

        if(words.get(3).toInt()==id_remover)
            {
                iterator.remove()
            }

    }
    val writer = PrintWriter(file)
    for(item in parts)
    {
        if(("\n"+item).length>=3) {
            writer.print("\n" + item)
        }
    }
    writer.close()

}

fun deleteReminder(ctx:Context, id_remover: Int){
    val path = ctx.getExternalFilesDir(null)
    val letDirectory = File(path, "PLANTS_Folder")
    letDirectory.mkdirs()
    val file = File(letDirectory, "Reminder.txt")
    var fileExists = file.exists()

    val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }

    var db = false // debouncer

    val parts: MutableList<String> = inputAsString.split("\n") as MutableList<String>
    val iterator = parts.iterator()

    while (iterator.hasNext())
    {
        val value = iterator.next()
        val words = value.split(",")
        if(db==false){ // skip the first line
            db=true
            continue
        }

        if(words.get(3).toInt()==id_remover)
        {
            iterator.remove()
        }


    }
    val writer = PrintWriter(file)
    for(item in parts)
    {
        if(("\n"+item).length>=3) {
            writer.print("\n" + item)
        }
    }
    writer.close()


}

fun deleteReminder2(ctx:Context, id_remover: Int){
    val path = ctx.getExternalFilesDir(null)
    val letDirectory = File(path, "PLANTS_Folder")
    letDirectory.mkdirs()
    val file = File(letDirectory, "Reminder.txt")
    var fileExists = file.exists()

    val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }

    var db = false // debouncer

    val parts: MutableList<String> = inputAsString.split("\n") as MutableList<String>
    val iterator = parts.iterator()

    while (iterator.hasNext())
    {
        val value = iterator.next()
        val words = value.split(",")
        if(db==false){ // skip the first line
            db=true
            continue
        }

        if(words.get(2).toInt()==id_remover)
        {
            iterator.remove()
        }


    }
    val writer = PrintWriter(file)
    for(item in parts)
    {
        if(("\n"+item).length>=3) {
            writer.print("\n" + item)
        }
    }
    writer.close()


}