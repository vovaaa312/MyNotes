package com.example.mynotes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.Models.Note
import com.example.mynotes.R

class NotesAdapter(private val context : Context, val listener:NotesClickListener): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private val NotesList=ArrayList<Note>()
    private val FullList=ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = NotesList[position]
        holder.title.text = currentNote.title
        holder.title.isSelected = true

        holder.description.text = currentNote.description
        holder.date.text = currentNote.date
        holder.date.isSelected = true

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(setColor()))

        holder.notes_layout.setOnClickListener{
            listener.onItemClicked(NotesList[holder.adapterPosition])

        }

        holder.notes_layout.setOnLongClickListener{
            listener.onLongItemClicked(NotesList[holder.adapterPosition],holder.notes_layout)
            true
        }
    }

    override fun getItemCount(): Int {
        return NotesList.size
    }

    fun updateList(newList:List<Note>)
    {
        FullList.clear()
        FullList.addAll(newList)
        NotesList.clear()
        NotesList.addAll(FullList)
        notifyDataSetChanged()
    }

    fun filterList(search:String){
        NotesList.clear()
        for (item in FullList){
            if(item.title?.lowercase()?.contains(search.lowercase()) == true ||
                item.description?.lowercase()?.contains(search.lowercase())==true) {
                NotesList.add(item)
            }
        }

        notifyDataSetChanged()
    }


    fun setColor():Int{
        return R.color.noteBackground
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.note_title)
        val description = itemView.findViewById<TextView>(R.id.note_description)
        val date = itemView.findViewById<TextView>(R.id.note_date)
    }

    interface NotesClickListener
    {
        fun onItemClicked(note: Note)
        fun onLongItemClicked(note:Note, cardView: CardView)
    }
}