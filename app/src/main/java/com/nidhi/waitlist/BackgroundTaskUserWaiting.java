package com.nidhi.waitlist;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by nidhipatharia on 8/6/16.
 */
public class BackgroundTaskUserWaiting extends AsyncTask<String,UserWaiting,String> {

    Context context;
    WaitingListAdapter waitingListAdapter;
    Activity activity;
    BackgroundTaskUserWaiting(Context context){

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
        String Event_Id = params[1];
        WaitListDbHelper waitListDbHelper = new WaitListDbHelper(context);
        if(method.equals("addWaitingUser")){
            String Name = params[2];
            String Email = params[3];
            String Phone = params[4];
            int Processed = 0;
            SQLiteDatabase sqLiteDatabase = waitListDbHelper.getWritableDatabase();
            waitListDbHelper.addWaitingUser(sqLiteDatabase,Event_Id, Email, Name, Phone, Processed);
            return "User Added to the Waiting List";
        }
        else if(method.equals("getWaitingList")){
            SQLiteDatabase sqLiteDatabase = waitListDbHelper.getReadableDatabase();


            Cursor cursor = waitListDbHelper.getWaitingList(sqLiteDatabase, Event_Id);
            waitingListAdapter = new WaitingListAdapter(context, R.layout.display_waiting_list_row);
            String Name, Email, Phone;
            int Processed;
            if(cursor!=null){
                if(cursor.moveToFirst()){
                    do{
                        Name = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventUserMappingInfo.COLUMN_NAME));
                        Email = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventUserMappingInfo.COLUMN_EMAIL));
                        Phone = cursor.getString(cursor.getColumnIndex(DatabaseContract.EventUserMappingInfo.COLUMN_PHONE));
                        Processed = cursor.getInt(cursor.getColumnIndex(DatabaseContract.EventUserMappingInfo.COLUMN_PROCESSED));
                        UserWaiting userWaiting = new UserWaiting(Event_Id, Name, Email, Processed);
                        publishProgress(userWaiting);
                    }while(cursor.moveToNext());
                }
                return "getWaitingList";

            }
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(UserWaiting... values) {
        waitingListAdapter.add(values[0]);
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("getWaitingList")){
            Toast.makeText(context,result,Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
            //Intent intent = new Intent(context,WaitingList.class);
            //activity.startActivity(intent);
        }

    }

    public WaitingListAdapter populateAdapter(String method, String Event_Id){
        doInBackground(method,Event_Id);
        return waitingListAdapter;
    }
}
