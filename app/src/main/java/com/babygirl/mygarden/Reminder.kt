package com.babygirl.mygarden

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileInputStream

class Reminder (val new_day : String, val new_frequency : String, val new_id2 : Int, val new_idrem: Int) {
    var day : String = new_day
        get() = field
        set(value) {
            field = value
        }
    var frequency : String = new_frequency
        get() = field
        set(value) {
            field = value
        }
    var id2 : Int = new_id2
        get() = field
        set(value) {
            field = value
        }
    var idrem : Int = new_idrem
        get() = field
        set(value) {
            field = value
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

    fun writeFile2 (ctx: Context)
    {
        //val path = ctx.getFilesDir()
        val path = ctx.getExternalFilesDir(null)
        val letDirectory = File(path, "PLANTS_Folder")
        letDirectory.mkdirs()

        val file = File(letDirectory, "Reminder.txt")
        file.appendText( "\n" + day + "," + frequency + "," + id2.toString()+ "," +(1+getLastId2(ctx)).toString())
    }

}