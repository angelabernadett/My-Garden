package com.babygirl.mygarden

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_plant.*
import android.graphics.BitmapFactory
import android.text.Html
import android.text.Html.fromHtml
import android.util.Log

import android.widget.ImageView
import java.io.File
import java.io.FileInputStream


@Suppress("DEPRECATION")
class AddPlant : AppCompatActivity() {

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_plant)
        image.setOnClickListener{
            val intent = Intent(this, PlantImages::class.java)
            startActivity(intent)
        }

        val bundle=getIntent().getExtras()
        if(bundle!=null)
        {
            val resid=bundle.getInt("resId")
            image.setImageResource(resid)
        }

        button2.setOnClickListener{
            if (name.getText().toString().equals("")) {
                name.setError(Html.fromHtml("<font color=#FFAAAA>please choose a name</font>"))
            }
            if (type.getText().toString().equals("")) {
                type.setError(Html.fromHtml("<font color=#FFAAAA>please choose a type</font>"))
            }
            if (type.getText().toString()!=""&& type.getText().toString()!="")  {
                if(bundle!=null){
                    val x = Plant(name.text.toString(),type.text.toString(),bundle.getInt("resId"), getLastId(this))
                    x.writeFile(this)
                }else{
                    val x = Plant(name.text.toString(),type.text.toString(),R.drawable.image1, getLastId(this))
                    x.writeFile(this)
                }

                val i = Intent(this, Page2::class.java)
                i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                i.putExtra("id",14)
                startActivity(i)
            }

        }
    }

}
