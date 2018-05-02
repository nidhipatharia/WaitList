package com.nidhi.waitlist;

/**
 * Created by nidhipatharia on 8/6/16.
 */
public class UserWaiting {
    String Event_Id, User_Name, Email;
    int Processed;

    UserWaiting(String Event_Id, String Name, String Email, int Processed){
        this.Event_Id = Event_Id;
        this.User_Name = Name;
        this.Email = Email;
        this.Processed = Processed;
    }

    public String getEvent_Id() {
        return Event_Id;
    }

    public void setEvent_Id(String event_Id) {
        Event_Id = event_Id;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getProcessed() {
        return Processed;
    }

    public void setProcessed(int processed) {
        Processed = processed;
    }
}
