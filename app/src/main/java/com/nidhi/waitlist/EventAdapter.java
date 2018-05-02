package com.nidhi.waitlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nidhipatharia on 8/6/16.
 */
public class EventAdapter extends ArrayAdapter {
    Context context;
    String MyPREFERENCES = "My Prefs";
    SharedPreferences sharedPreferences;

        List list = new ArrayList();

        public EventAdapter(Context context, int resource) {

            super(context, resource);
            this.context = context;
        }

        public void add(Event event) {
            list.add(event);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            String Adapter = sharedPreferences.getString("Adapter", "");
            View row = convertView;
            EventHolder eventHolder;
            if (row == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.display_event_row, parent, false);
                eventHolder = new EventHolder();
                eventHolder.cbPushToServer = (CheckBox) row.findViewById(R.id.pustToServer);
                eventHolder.tvEventId = (TextView) row.findViewById(R.id.tvEventId);
                eventHolder.tvEventName = (TextView) row.findViewById(R.id.tvEventName);
                eventHolder.tvEventLocation = (TextView) row.findViewById(R.id.tvEventLocation);
                eventHolder.tvEventStartDate = (TextView) row.findViewById(R.id.tvEventStartDate);
                eventHolder.tvEventEndDate = (TextView) row.findViewById(R.id.tvEventEndDate);
                eventHolder.tvEventDates = (TextView) row.findViewById(R.id.tvEventDates);
                row.setTag(eventHolder);
            } else {
                eventHolder = (EventHolder) row.getTag();
            }

            Event event = (Event) getItem(position);
            if(Adapter.equals("pushToServerAdapter"))
                eventHolder.cbPushToServer.setVisibility(View.VISIBLE);
            eventHolder.tvEventId.setText(event.getEvent_Id());
            eventHolder.tvEventName.setText(event.getEvent_Name());
            eventHolder.tvEventLocation.setText(event.getEvent_Location());
            eventHolder.tvEventStartDate.setText(event.getEvent_Start_Date());
            eventHolder.tvEventEndDate.setText(event.getEvent_End_Date());
            String Event_Date = event.getEvent_Start_Date() + " - " + event.getEvent_End_Date();
            eventHolder.tvEventDates.setText(Event_Date);

            return row;
        }


        static class EventHolder {
            TextView tvEventId, tvEventName, tvEventLocation, tvEventStartDate, tvEventEndDate, tvEventDates;
            CheckBox cbPushToServer;
        }
    }
