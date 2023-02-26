package com.example.mynotes.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mynotes.Models.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Update notes Set title =:title, description =:descr WHERE id = :id")
    suspend fun update(id: Int?, title: String?, descr: String?)

    @Query("Select * from notes order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}