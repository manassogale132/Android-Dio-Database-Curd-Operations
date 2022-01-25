package com.example.roomdatabaseexample

import android.R.attr
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import android.widget.Toast

import android.R.attr.data




class NoteAdapter(private val noteData: MutableList<Note>) : RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    //Create view holder = convers xml item view file into java object
    //inflating use inflator class
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val view = inflater.inflate(R.layout.single_row_note_design, parent, false);
        return MyViewHolder(view);
    }

    //binds the data with the item view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = noteData[position].title;
        holder.description.text = noteData[position].description
        holder.deleteIconButton.setOnClickListener {
            val db = Room.databaseBuilder(holder.title.context, AppDatabase::class.java, "note_record_database").allowMainThreadQueries().build()    // create database with its name //allowing to run database operation on main ui thread
            val noteDao = db.noteDao()  //create object of interface
            noteDao.delete(noteData[position].uid) //delete the record from room database
            noteData.removeAt(position)  //delete the record from list/recycler view

            notifyDataSetChanged() // update the fresh list of recycler view
            Toast.makeText(
                holder.title.context, "Note deleted!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //Gives the size of items in our recycler view
    override fun getItemCount(): Int {
        return noteData.size;
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.titleTV);
        var description = itemView.findViewById<TextView>(R.id.descriptionTV);
        var deleteIconButton = itemView.findViewById<ImageView>(R.id.deleteIconButton);
    }
}