package com.example.mynotes.Adapter

import androidx.cardview.widget.CardView
import com.example.mynotes.Models.Note

interface NotesClickListener {
    fun onItemClicked(note:Note)
    fun onLongItemClicked(note: Note, cardView: CardView)
}