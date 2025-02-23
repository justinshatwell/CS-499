/*This class will build the start-up screen when the app
is initially opened. When complete, it will show a logo for
a brief time, then move to the event grid. The delay is here
to allow the app to purge old listings and begin the download
of new listings.
 */

package com.zybooks.shatwell_justin_final;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Cap_Main extends AppCompatActivity {
    private Button createEvent;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        Context context = this;

        //Construct layout of page

        //Base layout and parameters
        ConstraintLayout layout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);


        ///////////////////////////////////////////////////////////////////////////////////////////
        //Create Elements
        ///////////////////////////////////////////////////////////////////////////////////////////
        //--Event Grid Button
        Button eventGridButton = new Button(this);
        eventGridButton.setText("Event Grid");
        eventGridButton.setId(R.id.main_to_event_button);

        //--Create Event Button
        Button createEventButton = new Button(this);
        createEventButton.setText("Create Event");
        createEventButton.setId(R.id.main_to_create_button);

        //--Create Image View
        ImageView mainImage = new ImageView(this);
        mainImage.setImageResource(R.drawable.family_storytime_250);

        //--Create Purge Button
        Button purgeEventButton = new Button(this);
        purgeEventButton.setId(R.id.purge_button);
        purgeEventButton.setText("Purge Events");


        ///////////////////////////////////////////////////////////////////////////////////////////
        //Set Layout Parameters
        ///////////////////////////////////////////////////////////////////////////////////////////
        //--Event Grid Button Parameters
        ConstraintLayout.LayoutParams eventButtonLayout = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        eventButtonLayout.rightToLeft = R.id.main_to_create_button;
        eventButtonLayout.leftToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        eventButtonLayout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        eventButtonLayout.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        eventButtonLayout.leftMargin = 20;
        eventButtonLayout.width = 500;

        //--Create Event Button Parameters
        ConstraintLayout.LayoutParams createButtonLayout = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        createButtonLayout.leftToRight = R.id.main_to_event_button;
        createButtonLayout.rightToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        createButtonLayout.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        createButtonLayout.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
        createButtonLayout.leftMargin = 20;
        createButtonLayout.width = 500;

        //--Create Image Parameters
        ConstraintLayout.LayoutParams imageLayout = new ConstraintLayout.LayoutParams (
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        imageLayout.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        imageLayout.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        imageLayout.bottomToTop = R.id.main_to_event_button;
        imageLayout.bottomMargin = 40;

        //--Create Purge Button Parameters
        ConstraintLayout.LayoutParams purgeLayout = new ConstraintLayout.LayoutParams (
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        purgeLayout.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        purgeLayout.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        purgeLayout.topToBottom = R.id.main_to_create_button;
        imageLayout.topMargin = 40;

        //--Assign Layout Parameters
        eventGridButton.setLayoutParams(eventButtonLayout);
        createEventButton.setLayoutParams(createButtonLayout);
        mainImage.setLayoutParams(imageLayout);
        purgeEventButton.setLayoutParams(purgeLayout);

        ////////////////////////////////////////////////////////////////////////////////////////////
        //Create and Assign Button Functions
        ////////////////////////////////////////////////////////////////////////////////////////////
        //--Event Grid Button
        eventGridButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(context, Cap_Event_Grid.class);
            startActivity(intent);
            }
        });

        //--Create Event Button
        createEventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Create_Event.class);
                startActivity(intent);
            }
        });

        purgeEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventDatabase db = new EventDatabase(context);
                db.purgeEvents();
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////
        //Add elements to layout
        ///////////////////////////////////////////////////////////////////////////////////////////
        layout.addView(eventGridButton);
        layout.addView(createEventButton);
        layout.addView(mainImage);
        layout.addView(purgeEventButton);

        setContentView(layout);

    }

}
