package com.example.peter_pc.h3_group_12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends Activity {

    Button btnSave, btnDelete, btnClose;
    EditText editTextName, editTextEmail, editTextAge;
    private int _id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextAge = (EditText) findViewById(R.id.editTextAge);

        _id =0;
        Intent intent = getIntent();
        _id =intent.getIntExtra("note_Id", 0);
        NoteRepo repo = new NoteRepo(this);
        Note note = new Note();
        note = repo.getNoteById(_id);

        editTextAge.setText(String.valueOf(note.age));
        editTextName.setText(note.name);
        editTextEmail.setText(note.email);
    }

    public void onDelete(View view)
    {
        NoteRepo repo = new NoteRepo(this);
        repo.delete(_id);
        Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onSave(View view)
    {
        Intent i = new Intent (this, MainActivity.class);
        i.putExtra("name", editTextName.getText().toString());
        i.putExtra("email", editTextEmail.getText().toString());
        i.putExtra("age", editTextAge.getText().toString());
        Toast.makeText(this, "Your information are recorded", Toast.LENGTH_SHORT).show();
        startActivity(i);
        finish();
    }

    public void onCancel(View view)
    {
        finish();
    }
}