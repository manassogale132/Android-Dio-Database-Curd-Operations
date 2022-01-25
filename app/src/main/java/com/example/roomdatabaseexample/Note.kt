package com.example.roomdatabaseexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val uid: Int,

    @ColumnInfo(name = "title") val title: String?,

    @ColumnInfo(name = "description") val description: String?
)