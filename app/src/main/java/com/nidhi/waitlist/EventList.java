package com.nidhi.waitlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class EventList extends AppCompatActivity {

    ListView listView;
    String MyPREFERENCES = "My Prefs";
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);
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
        EventAdapter eventAdapter = backgroundTaskEvent.populateAdapter("eventAdapter");
        listView.setAdapter(eventAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvEventId = ((TextView) view.findViewById(R.id.tvEventId));
                String Event_Id = tvEventId.getText().toString();
                sharedpreferences = getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Event_Id", Event_Id);
                editor.commit();
                String result = sharedpreferences.getString("Event_Id", "");
                startActivity(new Intent(EventList.this, WaitingList.class));
                //Toast.makeText(EventList.this, Event_Id, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        //MenuItem refresh = menu.findItem()
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.chooseEvent:
                startActivity(new Intent(EventList.this,EventList.class));
                return true;
            case R.id.waitingList:
                startActivity(new Intent(EventList.this,EventList.class));
                return true;
            case R.id.addUser:
                startActivity(new Intent(EventList.this,EventList.class));
                return true;
            case R.id.setupEvent:
                startActivity(new Intent(EventList.this,SetupEvent.class));
                return true;
            case R.id.eventList:
                startActivity(new Intent(EventList.this,EventList.class));
                return true;
            case R.id.pustToServer:
                startActivity(new Intent(EventList.this,EventList.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
