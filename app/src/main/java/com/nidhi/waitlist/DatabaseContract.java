package com.nidhi.waitlist;

import android.provider.BaseColumns;

/**
 * Created by nidhipatharia on 7/29/16.
 */
public final class DatabaseContract {
    public DatabaseContract(){}

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "WaitList.db";

    //Schema for Events Table
    public static abstract class EventInfo implements BaseColumns{

        public static final String TABLE_NAME = "events";
        public static final String COLUMN_ID = "event_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_START_DATE = "start_date";
        public static final String COLUMN_END_DATE = "end_date";

        //COLUMN_PUSHED_TO_SERVER = 0 IF DATA NOT PUSHED TO 0 ELSE 1
        public static final String COLUMN_PUSHED_TO_SERVER = "pushed_to_server";

        private static final String TEXT_TYPE = " TEXT";
        private static final String NUMERIC_TYPE = " NUMERIC";
        private static final String COMMA_SEP = ",";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_ID + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_LOCATION + TEXT_TYPE + COMMA_SEP +
                        COLUMN_START_DATE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_END_DATE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_PUSHED_TO_SERVER + NUMERIC_TYPE + ")";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    //Schema for Mapping Table
    public static abstract class EventUserMappingInfo implements BaseColumns{
        public static final String TABLE_NAME = "mapping";
        public static final String COLUMN_EVENT_ID = "event_id";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";

        //COLUMN_PROCESSED = 0 IF USER NOT PROCESSED: 0 ELSE 1
        public static final String COLUMN_PROCESSED = "processed";

        //COLUMN_PUSHED_TO_SERVER = 0 IF DATA NOT PUSHED TO 0 ELSE 1
        public static final String COLUMN_PUSHED_TO_SERVER = "pushed_to_server";

        private static final String TEXT_TYPE = " TEXT";
        private static final String NUMERIC_TYPE = " NUMERIC";
        private static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_EVENT_ID + TEXT_TYPE + COMMA_SEP +
                        COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP +
                        COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                        COLUMN_PHONE + TEXT_TYPE + COMMA_SEP +
                        COLUMN_PROCESSED + NUMERIC_TYPE + COMMA_SEP +
                        COLUMN_PUSHED_TO_SERVER + NUMERIC_TYPE +")";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
