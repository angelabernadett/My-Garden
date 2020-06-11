package com.babygirl.mygarden

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import java.io.File
import java.io.FileInputStream
import java.io.PrintWriter

class Plant (val new_name : String, val new_type : String, val new_image : Int, val new_id : Int) {
    var name : String = new_name
        get() = field
        set(value) {
            field = value
        }
    var type : String = new_type
        get() = field
        set(value) {
            field = value
        }
    var image : Int = new_image
        get() = field
        set(value) {
            field = value
        }
    var id : Int = new_id
        get() = field
        set(value) {
            field = value
        }

    fun getLastId(ctx: Context):Int{
        try{
            val path = ctx.getExternalFilesDir(null)
            val letDirectory = File(path, "PLANTS_Folder")
            letDirectory.mkdirs()
            val file = File(letDirectory, "Plants.txt")
            val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }

            val lastWord = inputAsString.substring(inputAsString.lastIndexOf(",") + 1)

            return lastWord.toInt()
        }
        catch(e: Exception){
            e.printStackTrace()
            return 0
        }
    }
    fun writeFile (ctx: Context)
    {
            //val path = ctx.getFilesDir()
            val path = ctx.getExternalFilesDir(null)
            val letDirectory = File(path, "PLANTS_Folder")
            letDirectory.mkdirs()

            val file = File(letDirectory, "Plants.txt")
            file.appendText( "\n" + name + "," + type + "," + image+ "," + (1+getLastId(ctx)).toString())
    }




}

