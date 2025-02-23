package com.zybooks.shatwell_justin_final;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Create_Event extends AppCompatActivity {

    private EditText titleEdit;
    private Button createButton;
    private DatePicker eventDate;
    private TimePicker eventTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_event);

        titleEdit = findViewById(R.id.eventTitleEdit);
        createButton = findViewById(R.id.createEventButton);
        eventDate = findViewById(R.id.eventDatePicker);
        eventTime = findViewById(R.id.eventTimePicker);
        //eventDate.setMinDate(System.currentTimeMillis() - 1000);
        eventTime.setHour(12);
        eventTime.setMinute(0);
    }

    public void onCreateEventButtonClick(View view) {
        Event newEvent = new Event(titleEdit.getText().toString(),
                eventDate.getMonth(),
                eventDate.getDayOfMonth(),
                eventDate.getYear(),
                eventTime.getHour(),
                eventTime.getMinute());
        EventDatabase db = new EventDatabase(this);
        db.createEvent(newEvent);

        Intent intent = new Intent(this, Cap_Event_Grid.class);
        startActivity(intent);


    }


}