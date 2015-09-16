package com.example.peter_pc.h3_group_12;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Activity3 extends ListActivity {

    TextView notes_Id;
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        btnClose = (Button)findViewById(R.id.btnClose);

        NoteRepo repo = new NoteRepo(this);

        ArrayList<HashMap<String, String>> noteList =  repo.getNoteList();
        if(noteList.size()!=0) {
            ListView lv = getListView();
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    notes_Id = (TextView) view.findViewById(R.id.note_Id);
                    String noteId = notes_Id.getText().toString();
                    Intent objIndent = new Intent(getApplicationContext(), Activity2.class);
                    objIndent.putExtra("note_Id", Integer.parseInt(noteId));
                    startActivity(objIndent);
                }
            });
            ListAdapter adapter = new SimpleAdapter( Activity3.this,noteList, R.layout.view_note_entry, new String[] { "id","name"}, new int[] {R.id.note_Id, R.id.note_name});
            setListAdapter(adapter);
        }else{
            Toast.makeText(this, "No notes avaiable!", Toast.LENGTH_SHORT).show();
        }
    }

    public void closeList(View view)
    {
        finish();
    }

}
