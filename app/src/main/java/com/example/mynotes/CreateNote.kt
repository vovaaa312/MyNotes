package com.example.mynotes

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mynotes.Models.Note
import com.example.mynotes.databinding.CreateNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class CreateNote : AppCompatActivity() {

    private lateinit var binding : CreateNoteBinding

    private lateinit var note: Note
    private lateinit var old_note:Note
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            old_note = intent.getSerializableExtra("currentNote") as Note
            binding.etName.setText(old_note.title)
            binding.etText.setText(old_note.description)
            isUpdate= true
        }catch (e : Exception){
            e.printStackTrace()
        }

        binding.imgCheck.setOnClickListener{
            val title = binding.etName.text.toString()
            val noteDescr = binding.etText.text.toString()

            if(title.isNotEmpty()||noteDescr.isNotEmpty()){
                val formatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

                if(isUpdate){
                    note = Note(
                        old_note.id, title, noteDescr, formatter.format(Date())
                    )
                }else{
                    note = Note(
                        null,title,noteDescr, formatter.format(Date())
                    )
                }
                val intent = Intent()
                intent.putExtra("note", note)
                setResult(RESULT_OK,intent)
                finish()

            }else{
                Toast.makeText(this@CreateNote, "Please enter some data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
        binding.imgBack.setOnClickListener{
            onBackPressed()
        }
    }
}