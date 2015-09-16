package com.example.peter_pc.h3_group_12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity{

    Button btnAddNote,btnGetAllNotes;
    private int _id = 0;
    String Name, Email, Age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAddNote = (Button) findViewById(R.id.btnAdd);
        btnGetAllNotes = (Button) findViewById(R.id.btnGetAllNotes);

        Intent intent = getIntent();
        Name = intent.getStringExtra("name");
        Email = intent.getStringExtra("email");
        Age = intent.getStringExtra("age");

    }

    public void addClicked(View view)
    {
        Intent intent = new Intent(this,Activity2.class);
        intent.putExtra("note_Id",0);
        startActivity(intent);
    }


    public void onSave1(View view)
    {

        if (Age == null && Name == null && Email == null)
            {
                Toast.makeText(this, "Please add information before saving",Toast.LENGTH_SHORT).show();
            }
        else {
                NoteRepo repo = new NoteRepo(this);
                Note note = new Note();
                note.age = Integer.parseInt(Age);
                note.email = Email;
                note.name = Name;
                note.note_ID = _id;

            if (_id == 0) {
                _id = repo.insert(note);

                Toast.makeText(this, "The recorded note has been saved", Toast.LENGTH_SHORT).show();
            } else
                {
                    repo.update(note);
                    Toast.makeText(this, "The note was updated", Toast.LENGTH_SHORT).show();
                }
            }
    }

    public void showList(View view)
    {
        Intent i = new Intent(this, Activity3.class);
        startActivity(i);
    }
}