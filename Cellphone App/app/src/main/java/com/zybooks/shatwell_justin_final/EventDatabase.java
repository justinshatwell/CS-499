package com.zybooks.shatwell_justin_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventDatabase  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "events.db";
    private static final int VERSION = 1;

    public EventDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    private static final class EventTable implements BaseColumns{
        private static final String TABLE = "events";
        private static final String COL_EVENT_NAME = "eventName";
        private static final String COL_MONTH = "month";
        private static final String COL_DAY = "day";
        private static final String COL_YEAR = "year";
        private static final String COL_HOUR = "hour";
        private static final String COL_MINUTE = "minute";

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + EventTable.TABLE + " (" +
                EventTable._ID + "integer primary key, " +
                EventTable.COL_EVENT_NAME + " text, " +
                EventTable.COL_MONTH+ " text, " +
                EventTable.COL_DAY+ " text, " +
                EventTable.COL_YEAR+ " text, " +
                EventTable.COL_HOUR+ " text, " +
                EventTable.COL_MINUTE + " text" + ")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + EventTable.TABLE);
        onCreate(db);
    }

    public long createEvent(Event event) {
        ContentValues values = new ContentValues();
        values.put(EventTable.COL_MONTH, String.valueOf(event.getEventMonth()));
        values.put(EventTable.COL_DAY, String.valueOf(event.getEventDay()));
        values.put(EventTable.COL_YEAR, String.valueOf(event.getEventYear()));
        values.put(EventTable.COL_HOUR, String.valueOf(event.getEventHour()));
        values.put(EventTable.COL_MINUTE, String.valueOf(event.getEventMinute()));
        values.put(EventTable.COL_EVENT_NAME, String.valueOf(event.getEventTitle()));

        SQLiteDatabase db = getWritableDatabase();

        long eventId = db.insert(EventTable.TABLE, null, values);
        db.close();

        return eventId;
    }

    public int deleteEvent(String eventName) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(EventTable.TABLE, EventTable.COL_EVENT_NAME + " =? ",
                new String[] {eventName});
        return rowsDeleted;
    }

    //Rather than update individual fields, this program will delete the existing event in the
    //database, then create a new event with the updated information.
    public long updateEvent(Event event) {
        deleteEvent(event.getEventTitle());
        return createEvent(event);

    }

    public Event getEvent(String eventName) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "select * from " + EventTable.TABLE + " where eventName = ?";
        Cursor cursor = db.rawQuery(sql, new String[] {eventName});

        cursor.moveToFirst();

        Event event = new Event(cursor.getString(1), cursor.getInt(2),
                cursor.getInt(3), cursor.getInt(4),
                cursor.getInt(5), cursor.getInt(6)
        );
        return event;
    }

    //Get Events from database and turn them into a list of Event java objects
    public List<Event> getAllEvents() {

        //Declare list
        List<Event> eventList = new ArrayList<>();

        //Get Cursor
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + EventTable.TABLE;
        Cursor cursor = db.rawQuery(sql, new String[]{});

        //Cycle through cursor and assign column values to object
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event(cursor.getString(1), cursor.getInt(2),
                        cursor.getInt(3), cursor.getInt(4),
                        cursor.getInt(5), cursor.getInt(6)
                        );
                event.setEventId(cursor.getInt(0));
                eventList.add(event);
                System.out.println("Event added to list");
            } while(cursor.moveToNext());
        }
        cursor.close();
        return eventList;

    }

    public int purgeEvents() {

        //Get event list
        List<Event> eventList = getAllEvents();

        //Get current time
        Date now = new Date();

        int count = 0;

        //For each event, calculate expiration date, then check against now
        for (Event event : eventList) {

            event.calcExpirationDate();

            //Compare dates and delete as needed
            if (now.getTime() > event.getExpirationDate()) {
                deleteEvent(event.getEventTitle());
                ++count;
            }

        }
        return count;
    }

}

