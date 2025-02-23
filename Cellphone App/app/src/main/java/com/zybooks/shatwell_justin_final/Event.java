package com.zybooks.shatwell_justin_final;

import java.util.Calendar;

public class Event {

    private String eventTitle;
    private int eventDay;
    private int eventHour;
    private int eventMinute;
    private int eventMonth;
    private int eventYear;
    private long eventId;

    private long expirationDate;

    private String fullDateTime;

    public Event(String title, int month, int day, int year, int hour, int minute) {
        eventTitle = title;
        eventMonth = month;
        eventDay = day;
        eventYear = year;
        eventHour = hour;
        eventMinute = minute;
        eventId = -1; //Default value
        setDateTimeStrings();
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public int getEventMonth() {
        return eventMonth;
    }

    public int getEventDay() {
        return eventDay;
    }

    public int getEventMinute() {
        return eventMinute;
    }

    public int getEventHour() {
        return eventHour;
    }

    public int getEventYear() {
        return eventYear;
    }

    public long getEventId() {
        return eventId;
    }

    public String getFullDateTime() {
        return fullDateTime;
    }

    public void setEventDay(int eventDay) {
        this.eventDay = eventDay;
        setDateTimeStrings();
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
        setDateTimeStrings();
    }

    public void setEventHour(int eventHour) {
        this.eventHour = eventHour;
        setDateTimeStrings();
    }

    public void setEventMinute(int eventMinute) {
        this.eventMinute = eventMinute;
        setDateTimeStrings();
    }

    public void setEventMonth(int eventMonth) {
        this.eventMonth = eventMonth;
        setDateTimeStrings();
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setEventYear(int eventYear) {
        this.eventYear = eventYear;
        setDateTimeStrings();
    }

    public void calcExpirationDate() {
        Calendar date = Calendar.getInstance();
        date.set(eventYear, eventMonth, eventDay);
        expirationDate = date.getTimeInMillis() + 86400000; //Date plus one day
    }

    public void setExpirationDate(long milliseconds) {
        this.expirationDate = milliseconds;
    }

    public long getExpirationDate() {
        return this.expirationDate;
    }

    private void setDateTimeStrings() {
        fullDateTime = String.valueOf(eventYear) + "-"
                + String.valueOf(eventMonth) + "-"
                + String.valueOf(eventDay) + " "
                + String.valueOf(eventHour) + ":"
                + String.valueOf(eventMinute) + ":"
                + "00:000";

    }
}