package com.example.roomdatabaseexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        insertButton.setOnClickListener {
            insertButtonClicked()
        }

        fetchButton.setOnClickListener {
            startActivity(Intent(applicationContext, AllNotesActivity::class.java));
        }
    }

    private fun insertButtonClicked() {
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "note_record_database").allowMainThreadQueries().build()    // create database with its name //allowing to run database operation on main ui thread
        val noteDao = db.noteDao()  //create object of interface
        val checkIsPresent: Boolean = noteDao.isPreset(Integer.parseInt(uidEditText.text.toString()));  //checking if record is present based on uid
        if (!checkIsPresent) {   // record not present then do this
            noteDao.insert(
                Note(
                    Integer.parseInt(uidEditText.text.toString()),
                    titleEditText.text.toString(),
                    descriptionEditText.text.toString()
                )
            )  //calling interface insert method
            uidEditText.setText("")
            titleEditText.setText("")
            descriptionEditText.setText("")
            resultTV.setText("***Note record inserted into table!***")
        } else {              // record present then do this
            uidEditText.setText("")
            titleEditText.setText("")
            descriptionEditText.setText("")
            resultTV.setText("***Note record already exist!***")
        }
    }
}