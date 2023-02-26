package com.example.mynotes.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)    val id:Int?,
    @ColumnInfo(name = "title")         val title: String?,
    @ColumnInfo(name = "description")   val description  : String?,
    @ColumnInfo(name = "date")          val date : String?

):java.io.Serializable