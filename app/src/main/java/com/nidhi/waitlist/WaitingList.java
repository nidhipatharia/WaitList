package com.nidhi.waitlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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

public class WaitingList extends AppCompatActivity {

    String Event_Id;
    String MyPREFERENCES = "My Prefs";
    SharedPreferences sharedPreferences;
    WaitingListAdapter waitingListAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        toolbar.showOverflowMenu();
        createList();
    }
    private void createList(){
        final WaitListDbHelper waitListDbHelper = new WaitListDbHelper(this);
        listView = (ListView) findViewById(R.id.lvWaitingUserList);

        //backgroundTaskUserWaiting.execute("getWaitingList");
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Event_Id = sharedPreferences.getString("Event_Id", "");
        BackgroundTaskUserWaiting backgroundTaskUserWaiting = new BackgroundTaskUserWaiting(this);
        waitingListAdapter = backgroundTaskUserWaiting.populateAdapter("getWaitingList", Event_Id);

        try {
            listView.setAdapter(waitingListAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvName = (TextView) view.findViewById(R.id.tvName);
                String Name = tvName.getText().toString();
                SQLiteDatabase db = waitListDbHelper.getWritableDatabase();
                long result = waitListDbHelper.updateWaitingUserProcessedStatus(db, Event_Id,Name);
                
            }
        });
    }

    public void addUser(View v){
        startActivity(new Intent(this,AddUser.class));
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
                startActivity(new Intent(WaitingList.this,EventList.class));
                return true;
            case R.id.waitingList:
                startActivity(new Intent(WaitingList.this,EventList.class));
                return true;
            case R.id.addUser:
                startActivity(new Intent(WaitingList.this,EventList.class));
                return true;
            case R.id.setupEvent:
                startActivity(new Intent(WaitingList.this,SetupEvent.class));
                return true;
            case R.id.eventList:
                startActivity(new Intent(WaitingList.this,EventList.class));
                return true;
            case R.id.pustToServer:
                startActivity(new Intent(WaitingList.this,EventsToPush.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
