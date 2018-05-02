package com.nidhi.waitlist;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by nidhipatharia on 8/6/16.
 */
public class BackgroundTaskEvent extends AsyncTask<String,Event,String> {

    Context context;
    EventAdapter eventAdapter;
    Activity activity;
    String adapter;
    String MyPREFERENCES = "My Prefs";
    SharedPreferences sharedPreferences;
    BackgroundTaskEvent(Context context){

        this.context = context;
        activity = (Activity) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        WaitListDbHelper waitListDbHelper = new WaitListDbHelper(context);
        sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(method.equals("addEvent")){
            String Event_Name = params[1];
            String Event_Location = params[2];
            String Event_Start_Date = params[3];
            String Event_End_Date = params[4];
            SQLiteDatabase sqLiteDatabase = waitListDbHelper.getWritableDatabase();
            waitListDbHelper.addEvent(sqLiteDatabase,Event_Name,Event_Location, Event_Start_Date, Event_End_Date);
            return "Event Created";
        }
        else if(method.equals("eventAdapter")) {
            adapter="eventAdapter";
            editor.putString("Adapter", adapter);
            editor.commit();
            SQLiteDatabase sqLiteDatabase = waitListDbHelper.getReadableDatabase();
            Cursor cursor = waitListDbHelper.getEventList(sqLiteDatabase);
            eventAdapter = new EventAdapter(context, R.layout.display_event_row);
            String Event_Id, Event_Name, Event_Location, Event_Start_Date, Event_End_Date;
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Event_Id = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventInfo.COLUMN_ID));
                        Event_Name = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventInfo.COLUMN_NAME));
                        Event_Location = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventInfo.COLUMN_LOCATION));
                        Event_Start_Date = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventInfo.COLUMN_START_DATE));
                        Event_End_Date = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventInfo.COLUMN_END_DATE));
                        Event event = new Event(Event_Id, Event_Name, Event_Location, Event_Start_Date, Event_End_Date);
                        publishProgress(event);
                    } while (cursor.moveToNext());
                }
                return "eventAdapter";
            }
        }
        else if(method.equals("pushToServer")) {
            adapter = "pushToServerAdapter";
            editor.putString("Adapter", adapter);
            editor.commit();
            SQLiteDatabase sqLiteDatabase = waitListDbHelper.getReadableDatabase();
            Cursor cursor = waitListDbHelper.getEventList(sqLiteDatabase);
            eventAdapter = new EventAdapter(context, R.layout.display_event_row);
            String Event_Id, Event_Name, Event_Location, Event_Start_Date, Event_End_Date;
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Event_Id = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventInfo.COLUMN_ID));
                        Event_Name = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventInfo.COLUMN_NAME));
                        Event_Location = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventInfo.COLUMN_LOCATION));
                        Event_Start_Date = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventInfo.COLUMN_START_DATE));
                        Event_End_Date = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventInfo.COLUMN_END_DATE));
                        Event event = new Event(Event_Id, Event_Name, Event_Location, Event_Start_Date, Event_End_Date);
                        publishProgress(event);
                    } while (cursor.moveToNext());
                }
                return "pushToServer";
            }
        }


        return null;
    }

    @Override
    protected void onProgressUpdate(Event... values) {

            eventAdapter.add(values[0]);

    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("getEventList")){
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();;
        }
        else
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();


    }

    public EventAdapter populateAdapter(String whichAdapter){
        doInBackground(whichAdapter);

            return eventAdapter;

    }

}
