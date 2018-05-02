package com.nidhi.waitlist;

/**
 * Created by nidhipatharia on 8/6/16.
 */
public class Event {
    String Event_Id, Event_Name, Event_Location, Event_Start_Date, Event_End_Date;

    public Event(){}
    public Event(String Event_Id, String Event_Name, String Event_Location, String Event_Start_Date, String Event_End_Date){
        this.Event_Id = Event_Id;
        this.Event_Name = Event_Name;
        this.Event_Location = Event_Location;
        this.Event_Start_Date = Event_Start_Date;
        this.Event_End_Date = Event_End_Date;
    }

    public String getEvent_Id() {
        return Event_Id;
    }

    public void setEvent_Id(String event_Id) {
        Event_Id = event_Id;
    }

    public String getEvent_Name() {
        return Event_Name;
    }

    public String getEvent_Location() {
        return Event_Location;
    }

    public void setEvent_Location(String event_Location) {
        Event_Location = event_Location;
    }

    public void setEvent_Name(String event_Name) {
        Event_Name = event_Name;
    }

    public String getEvent_Start_Date() {
        return Event_Start_Date;
    }

    public void setEvent_Start_Date(String event_Start_Date) {
        Event_Start_Date = event_Start_Date;
    }

    public String getEvent_End_Date() {
        return Event_End_Date;
    }

    public void setEvent_End_Date(String event_End_Date) {
        Event_End_Date = event_End_Date;
    }
}
