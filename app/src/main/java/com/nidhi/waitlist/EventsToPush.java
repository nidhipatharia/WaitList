package com.nidhi.waitlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class EventsToPush extends AppCompatActivity {

    ListView listView;
    EventAdapter pushToServerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_to_push);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        toolbar.showOverflowMenu();
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        listView = (ListView) findViewById(R.id.lvEventList);
        BackgroundTaskEvent backgroundTaskEvent = new BackgroundTaskEvent(this);
        //backgroundTaskEvent.execute("getEventList");
        pushToServerAdapter = backgroundTaskEvent.populateAdapter("pushToServer");
        listView.setAdapter(pushToServerAdapter);
    }
    public void pushToServer(View view){
        ArrayList<Event> Events= new ArrayList<Event>();
        CheckBox cbPushToServer;
        TextView tvEventId, tvEventName, tvEventLocation, tvEventStartDate, tvEventEndDate;
        int i=0;
        for(int eventIndex=0; eventIndex < pushToServerAdapter.getCount(); eventIndex++){
           try {
               cbPushToServer = (CheckBox)listView.getChildAt(eventIndex).findViewById(R.id.pustToServer);
               if(cbPushToServer.isChecked()){
                   tvEventId = (TextView) listView.getChildAt(eventIndex).findViewById(R.id.tvEventId);
                   tvEventName = (TextView) listView.getChildAt(eventIndex).findViewById(R.id.tvEventName);
                   tvEventLocation = (TextView) listView.getChildAt(eventIndex).findViewById(R.id.tvEventLocation);
                   tvEventStartDate = (TextView) listView.getChildAt(eventIndex).findViewById(R.id.tvEventStartDate);
                   tvEventEndDate = (TextView) listView.getChildAt(eventIndex).findViewById(R.id.tvEventEndDate);
                   Event event = new Event();
                   event.setEvent_Id(tvEventId.getText().toString());
                   event.setEvent_Name(tvEventName.getText().toString());
                   event.setEvent_Location(tvEventLocation.getText().toString());
                   event.setEvent_Start_Date(tvEventStartDate.getText().toString());
                   event.setEvent_End_Date(tvEventEndDate.getText().toString());
                   Events.add(event);
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
        String jsonObject = new Gson().toJson(Events);
        //PushToServer.execute(jsonObject);
    }
}
