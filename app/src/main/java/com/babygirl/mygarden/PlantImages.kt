package com.babygirl.mygarden

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_plant.*
import kotlinx.android.synthetic.main.activity_plant_images.*
import android.graphics.BitmapFactory

import java.io.ByteArrayOutputStream



class PlantImages : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_images)
        imageView7.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image1)
            startActivity(i)
        }
        imageView3.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image2)
            startActivity(i)
        }
        imageView.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image3)
            startActivity(i)
        }
        imageView2.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image4)
            startActivity(i)

        }
        imageView12.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image15)
            startActivity(i)
        }
        imageView15.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId", R.drawable.image5)
            startActivity(i)
        }
        imageView8.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image11)
            startActivity(i)

        }
        imageView13.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image6)
            startActivity(i)

        }
        imageView16.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image12)
            startActivity(i)

        }
        imageView9.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image9)
            startActivity(i)

        }
        imageView14.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image8)
            startActivity(i)

        }
        imageView18.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image7)
            startActivity(i)

        }
        imageView11.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image13)
            startActivity(i)

        }
        imageView20.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image14)
            startActivity(i)

        }
        imageView19.setOnClickListener {
            val i = Intent(this, AddPlant::class.java)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            i.putExtra("resId",R.drawable.image10)
            startActivity(i)

        }
    }
    }

