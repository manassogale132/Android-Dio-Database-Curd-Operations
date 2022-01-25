package com.example.roomdatabaseexample

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    fun insert(users: Note)

    @Query("SELECT EXISTS(SELECT * FROM Note WHERE uid = :uid)")
    fun isPreset(uid: Int): Boolean

    @Query("SELECT * FROM Note")
    fun getAll(): List<Note>

    @Query("DELETE FROM Note WHERE uid = :uid")
    fun delete(uid: Int)

}