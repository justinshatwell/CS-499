package com.zybooks.shatwell_justin_final;

import static android.widget.GridLayout.spec;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Grid;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;
import androidx.core.widget.NestedScrollView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class Cap_Event_Grid extends AppCompatActivity {

    private EventDatabase db;
    private List<Event> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        Context context = this;

        //Initialize db
        db = new EventDatabase(this);
        //Get all events from database
        eventList = db.getAllEvents();

        /////////////////////////////////////////////////////////
        //On start up, contact server for new events
        //Purge outdated events
        ////////////////////////////////////////////////////////

        //Request events from web server as JSON[]
        String url = "http://10.0.2.2:3000/api/events";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        Log.d("Button Press:" ,"Refresh Button Pressed");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray> (){
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        Log.d("Refresh:", "Response Received");
                        //Check for duplicates
                        ////Make list of names of current events
                        List<String> checkList = new ArrayList<>();
                        if (eventList.size() > 0) {
                            for (int i = 0; i < eventList.size(); i++) {
                                checkList.add(eventList.get(i).getEventTitle());
                            }
                        }
                        Boolean checkListNotEmpty = !checkList.isEmpty();
                        EventDatabase db = new EventDatabase(context);

                        //Process JSON responses
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject currObject = (JSONObject)response.get(i);
                                String currName = currObject.getString("name");
                                if (checkList.isEmpty() || checkListNotEmpty && !checkList.contains(currName)){
                                    Event newEvent = new Event(
                                            currName,
                                            currObject.getInt("month"),
                                            currObject.getInt("day"),
                                            currObject.getInt("year"),
                                            currObject.getInt("hour"),
                                            currObject.getInt("minute")
                                    );
                                    db.createEvent(newEvent);
                                    eventList.add(newEvent);
                                }
                            }
                            catch (JSONException e) {
                                Log.d("Error", e.toString());
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Refresh:", error.toString());

                    }
                }

        );
        requestQueue.add(jsonArrayRequest);




        ScrollView scrollView = new ScrollView(this);
        ScrollView.LayoutParams scrollParams = new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.WRAP_CONTENT
        );
        scrollView.setLayoutParams(scrollParams);


        ConstraintLayout mainLayout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams mainParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        mainParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        mainParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        mainLayout.setId(R.id.event_grid);
        mainLayout.setLayoutParams(mainParams);

        for (int i = 0; i < eventList.size(); i++) {

            Event event = eventList.get(i);
            //////////////////////////////////////////////////////////////////////////
            //Create Sub Layout
            //////////////////////////////////////////////////////////////////////////

            ConstraintLayout entry = new ConstraintLayout(this);
            ConstraintLayout.LayoutParams entryParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

            entryParams.leftToLeft = R.id.event_grid;
            entryParams.rightToRight = R.id.event_grid;
            entryParams.topMargin = 50;

            entry.setLayoutParams(entryParams);

            entry.setId(i);

            //--Generate an id to be used in ordering
            if (i > 0) {
                entryParams.topToBottom = i -1;
            }

            entry.setBackgroundColor(R.color.black);

            ///////////////////////////////////////////////////////////////////////////
            //Construct Elements
            //////////////////////////////////////////////////////////////////////////

            //--Image
            ImageView image = new ImageView(this);
            //This will change to a dynamic assignment when database has bitmaps
            image.setImageResource(R.drawable.family_storytime_250);
            image.setId(R.id.listing_image);

            //--Title
            TextView title = new TextView(this);
            title.setText(event.getEventTitle());
            title.setId(R.id.listing_title);

            //--Dates
            TextView date = new TextView(this);
            date.setText(event.getFullDateTime());
            date.setId(R.id.listing_date);



            ////////////////////////////////////////////////////////////////////////////
            //Create and Assign Params
            ///////////////////////////////////////////////////////////////////////////

            //--Image Params
            ConstraintLayout.LayoutParams imageParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

            imageParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            imageParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
            imageParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
            imageParams.topMargin = 20;
            imageParams.bottomMargin = 20;
            imageParams.leftMargin = 10;
            imageParams.rightMargin = 10;


            //--Title Params
            ConstraintLayout.LayoutParams titleParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

            titleParams.topToBottom = R.id.listing_image;
            titleParams.leftToLeft = R.id.listing_image;
            titleParams.leftMargin = 15;

            //--Date Params
            ConstraintLayout.LayoutParams dateParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

            dateParams.topToBottom = R.id.listing_title;
            dateParams.leftToLeft = R.id.listing_title;
            dateParams.leftMargin = 25;

            //--Assign Params
            image.setLayoutParams(imageParams);
            title.setLayoutParams(titleParams);
            date.setLayoutParams(dateParams);

            ////////////////////////////////////////////////////////////////////////////////////
            //Add elements to layout and then grid
            ////////////////////////////////////////////////////////////////////////////////////
            entry.addView(image);
            entry.addView(title);
            entry.addView(date);

            mainLayout.addView(entry);

        }

        scrollView.addView(mainLayout);
        setContentView(scrollView);



    }
}