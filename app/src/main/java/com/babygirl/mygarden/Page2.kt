package com.babygirl.mygarden

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.android.synthetic.main.activity_page2.*
import kotlinx.android.synthetic.main.fragment_garden.*
import kotlinx.android.synthetic.main.fragment_info.*
import java.io.File
import java.io.FileInputStream
import android.content.DialogInterface
import android.widget.AdapterView
import android.widget.AdapterView.OnItemLongClickListener
import android.R.array



fun getListPlants(ctx:Context): MutableList<Plant>{
    val x = mutableListOf<Plant>()

    val path = ctx.getExternalFilesDir(null)
    val letDirectory = File(path, "PLANTS_Folder")
    letDirectory.mkdirs()
    val file = File(letDirectory, "Plants.txt")
    var fileExists = file.exists()

    if(!fileExists){
        return mutableListOf<Plant>()
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

        x.add(Plant(words.get(0), words.get(1), words.get(2).toInt(), words.get(3).toInt()))
    }


    return x
}



class Info : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_info, container, false)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imageView21.setOnClickListener{
            val intent = Intent(super.getContext(), Cactus::class.java)
            startActivity(intent)
        }
        imageView6.setOnClickListener{
            val intent = Intent(super.getContext(), AloeVera::class.java)
            startActivity(intent)
        }
        imageView22.setOnClickListener{
            val intent = Intent(super.getContext(), Spider::class.java)
            startActivity(intent)
        }
        imageView4.setOnClickListener{
            val intent = Intent(super.getContext(), Snake::class.java)
            startActivity(intent)
        }
    }

    }

class Garden(ctx: Activity) : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("babyyoda","Hello from onCreateView")

        return inflater.inflate(R.layout.fragment_garden, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val list_names = mutableListOf<String>()
        val list_types = mutableListOf<String>()
        val list_images = mutableListOf<Int>()
        var count_me = 0
        for(item in plant_list){
            list_names.add(item.name)
            list_types.add(item.type)
            list_images.add(item.image)
            //Log.d("babyyoda", count_me.toString())
            count_me+=1
        }

        for(item in list_names) Log.d("babyyoda","---"+item)

        val arr1 = list_names.filterNotNull().toTypedArray()
        val arr2 = list_types.filterNotNull().toTypedArray()
        val arr3 = list_images.filterNotNull().toTypedArray()

       // Log.d("babyyoda", "...." + arr1.size+"," + arr2.size + "," + arr3.size)


        val listView11 = listViewXL

        val myListAdapter = MyListAdapter(activity!!,arr1,arr2,arr3)
        listView11.adapter = myListAdapter


        listView11.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)

            val intent = Intent(super.getContext(), PlantDetails::class.java)
            intent.putExtra("name", plant_list.get(id.toInt()).name)
            intent.putExtra("type", plant_list.get(id.toInt()).type)
            intent.putExtra("image", plant_list.get(id.toInt()).image)
            intent.putExtra("id", plant_list.get(id.toInt()).id)
            startActivity(intent)
        }


        button4.setOnClickListener {

            val intent = Intent(super.getContext(), AddPlant::class.java)
            startActivity(intent)

        }



    }

}

var plant_list  = mutableListOf<Plant>()
class Page2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)
        bottom_navigation.setSelectedItemId(R.id.nav_home)
        setSupportActionBar(toolbar)


        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.nav_home -> {
                    finish()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_garden -> {
                    plant_list= getListPlants(this)
                    val fragment = Garden(this)
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_info -> {
                    val fragment = Info()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val bundle=getIntent().getExtras()
        if(bundle!=null)
        {
            val id=bundle.getInt("id")
            if(id==14){
                bottom_navigation.setSelectedItemId(R.id.nav_garden)
                plant_list = getListPlants(this)
                val fragment = Garden(this)
                supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return
            }

        }

    }
}
