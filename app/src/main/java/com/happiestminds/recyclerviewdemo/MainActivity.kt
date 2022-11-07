package com.happiestminds.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {

    lateinit var rView : RecyclerView

    val listOfStudents = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rView =  findViewById(R.id.rView)
        setUpData()

        //layoutManager - positioning of items
        //linear layout is needed, vertically - positioning is done

       // rView.layoutManager = GridLayoutManager(this,1)
       rView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


        //adapter - data binding we create adapter
        rView.adapter = studentAdapter(listOfStudents){std, pos ->

            //show popup menu

            val vHolder = rView.findViewHolderForAdapterPosition(pos)
            val view = rView.findContainingItemView(vHolder?.itemView!!)
           val pMenu =  PopupMenu(this,view)
            pMenu.menu.add("Delete")
            pMenu.menu.add("Edit")
            pMenu.show()
            //to get delete or edit of the menu item the user pics up
            pMenu.setOnMenuItemClickListener {
                when(it.title) {
                    "Delete" -> {
                        deleteStudent(std, pos)
                        true
                    }
                    "Edit" -> {
                        editStudent(std,pos)
                        true
                }
                    else-> false

                }
            }

        }

    }

    private fun editStudent(std: Student, pos: Int) {
       val updatedName =  std.name.uppercase()
        listOfStudents[pos].name = updatedName
        rView.adapter?.notifyItemChanged(pos)

    }
    //student data is needed create a data class , new kotlin data class

    private fun setUpData() {
        listOfStudents.add(Student(1,"priya",99))
        listOfStudents.add(Student(2,"Bhoomi",50))
        listOfStudents.add(Student(3,"John",60))
        listOfStudents.add(Student(4,"Pooja",55))
        listOfStudents.add(Student(5,"Sushma",78))
        listOfStudents.add(Student(6,"Adam",91))
        listOfStudents.add(Student(7,"Steves",92))
        listOfStudents.add(Student(8,"Smith",80))
        listOfStudents.add(Student(9,"merry",44))
        listOfStudents.add(Student(10,"james",20))

    }

    private fun deleteStudent(std:Student,position: Int){
        listOfStudents.remove(std)
        Log.d("MainActivity","Student Deleted ${std.name}")
        //rView.adapter?.notifyDataSetChanged()
        rView.adapter?.notifyItemRemoved(position)
    }

}