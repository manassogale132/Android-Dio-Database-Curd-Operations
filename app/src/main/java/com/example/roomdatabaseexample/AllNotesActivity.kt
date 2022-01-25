package com.example.roomdatabaseexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_all_notes.*

class AllNotesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_notes)

        getRoomData()
    }

    private fun getRoomData() {
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "note_record_database").allowMainThreadQueries().build()    // create database with its name //allowing to run database operation on main ui thread
        val noteDao = db.noteDao()  //create object of interface
        val notesRecords: List<Note> = noteDao.getAll() //calling the getAll() method which returns List<Note>

        recyclerViewID.layoutManager = LinearLayoutManager(this);
        recyclerViewID.adapter = NoteAdapter(notesRecords as MutableList<Note>);

        //ui check if list is empty the show custom text message
        if(notesRecords.isEmpty()){
            recyclerViewID.visibility = View.GONE;
            empty_view.visibility = View.VISIBLE
        }else {
            recyclerViewID.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.GONE);
        }

    }
}