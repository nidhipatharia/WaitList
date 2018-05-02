package com.nidhi.waitlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.UUID;

/**
 * Created by nidhipatharia on 7/29/16.
 */
public class WaitListDbHelper extends SQLiteOpenHelper {


    public WaitListDbHelper(Context context){
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createStudentInfoTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        dropTables(sqLiteDatabase);
        sqLiteDatabase.execSQL(DatabaseContract.EventUserMappingInfo.SQL_CREATE_ENTRIES);
        createStudentInfoTable(sqLiteDatabase);
    }

    private void createStudentInfoTable(SQLiteDatabase db){
        db.execSQL(DatabaseContract.EventInfo.SQL_CREATE_ENTRIES);
        db.execSQL(DatabaseContract.EventUserMappingInfo.SQL_CREATE_ENTRIES);
    }

    private void dropTables(SQLiteDatabase db){
        db.execSQL(DatabaseContract.EventInfo.SQL_DELETE_ENTRIES);
        db.execSQL(DatabaseContract.EventUserMappingInfo.SQL_DELETE_ENTRIES);
    }


    public void addEvent(SQLiteDatabase db, String Event_Name, String Event_Location,
                         String Event_Start_Date, String Event_End_Date){
        long newRowId = 0;
        int PushedToServer = 0;
        ContentValues cv = new ContentValues();
        //Event_Id is the GUID
        String Event_Id = UUID.randomUUID().toString();

        cv.put(DatabaseContract.EventInfo.COLUMN_ID, Event_Id);
        cv.put(DatabaseContract.EventInfo.COLUMN_NAME, Event_Name);
        cv.put(DatabaseContract.EventInfo.COLUMN_LOCATION, Event_Location);
        cv.put(DatabaseContract.EventInfo.COLUMN_START_DATE, Event_Start_Date);
        cv.put(DatabaseContract.EventInfo.COLUMN_END_DATE, Event_End_Date);
        cv.put(DatabaseContract.EventUserMappingInfo.COLUMN_PUSHED_TO_SERVER, PushedToServer);
        try {
            newRowId = db.insert(DatabaseContract.EventInfo.TABLE_NAME,
                                      null,cv);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Cursor getEventList(SQLiteDatabase db){

        Cursor cursor = db.query(DatabaseContract.EventInfo.TABLE_NAME,null,null,null,null,null,null,null);
        return cursor;
    }

    public void addWaitingUser(SQLiteDatabase db, String Event_Id, String Email, String Name, String Phone, int Processed ){
        ContentValues cv = new ContentValues();
        int PushedToServer=0;
        cv.put(DatabaseContract.EventUserMappingInfo.COLUMN_EVENT_ID, Event_Id);
        cv.put(DatabaseContract.EventUserMappingInfo.COLUMN_EMAIL, Email);
        cv.put(DatabaseContract.EventUserMappingInfo.COLUMN_NAME, Name);
        cv.put(DatabaseContract.EventUserMappingInfo.COLUMN_PHONE, Phone);
        cv.put(DatabaseContract.EventUserMappingInfo.COLUMN_PROCESSED, Processed);
        cv.put(DatabaseContract.EventUserMappingInfo.COLUMN_PUSHED_TO_SERVER, PushedToServer);
        long newRowId = 0;
        try {
            newRowId = db.insert(DatabaseContract.EventUserMappingInfo.TABLE_NAME,
                    null,cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cursor getWaitingList(SQLiteDatabase db, String Event_Id){
        String Processed="0";

        Cursor cursor = db.query(DatabaseContract.EventUserMappingInfo.TABLE_NAME,null,
                                DatabaseContract.EventUserMappingInfo.COLUMN_EVENT_ID +"=?" + " and " +
                                        DatabaseContract.EventUserMappingInfo.COLUMN_PROCESSED + "=?",
                            new String[] {Event_Id,Processed},null,null,null);
        //Cursor cursor = db.execSQL("select * from mapping where event_id= " + Event_Id + " and processed = 0");
        return cursor;
    }

    public long updateWaitingUserProcessedStatus(SQLiteDatabase db, String Event_Id, String Name){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.EventUserMappingInfo.COLUMN_PROCESSED, 1);
        String WHERE_EQUALS = DatabaseContract.EventUserMappingInfo.COLUMN_EVENT_ID + "=?" + " and " +
                                DatabaseContract.EventUserMappingInfo.COLUMN_NAME + "=?";
        long result=0;
        try {
            result = db.update(DatabaseContract.EventUserMappingInfo.TABLE_NAME, cv,
                    WHERE_EQUALS, new String[] {Event_Id, Name});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public long updateEventPushedToServerStatus(SQLiteDatabase db, String Event_Id, String Status){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.EventInfo.COLUMN_PUSHED_TO_SERVER, 1);
        String WHERE_EQUALS = DatabaseContract.EventUserMappingInfo.COLUMN_EVENT_ID + "=?" + " and " +
                DatabaseContract.EventInfo.COLUMN_PUSHED_TO_SERVER + "=?";
        long result=0;
        try {
            result = db.update(DatabaseContract.EventUserMappingInfo.TABLE_NAME, cv,
                    WHERE_EQUALS, new String[] {Event_Id, Status});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public long updateWaitingUserPushedToServerStatus(SQLiteDatabase db, String Event_Id, String Email, String Status){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseContract.EventUserMappingInfo.COLUMN_PROCESSED, 1);
        String WHERE_EQUALS = DatabaseContract.EventUserMappingInfo.COLUMN_EVENT_ID + "=?" + " and " +
                DatabaseContract.EventUserMappingInfo.COLUMN_EMAIL + "=?" + " and " +
                DatabaseContract.EventInfo.COLUMN_PUSHED_TO_SERVER + "=?";
        long result=0;
        try {
            result = db.update(DatabaseContract.EventUserMappingInfo.TABLE_NAME, cv,
                    WHERE_EQUALS, new String[] {Event_Id, Email, Status});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
