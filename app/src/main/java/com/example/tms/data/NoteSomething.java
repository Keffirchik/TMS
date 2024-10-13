package com.example.tms.data;

import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tms.R;

public class NoteSomething extends AppCompatActivity {

    private static final String PREFS_NAME = "NotePrefs";
    private static final String KEY_NOTE_COUNT = "NoteCount";

    private LinearLayout noteContainer;



    private void saveNote() {
        EditText titleEditText = findViewById(R.id.titleEditText);
    }
}
