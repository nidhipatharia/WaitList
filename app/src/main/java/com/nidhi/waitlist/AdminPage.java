package com.nidhi.waitlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AdminPage extends AppCompatActivity {

    Button btnSetupEvent, btnEventList,btnPushToServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        toolbar.showOverflowMenu();

        initializeControl();
    }
    private void initializeControl(){
        btnSetupEvent = (Button) findViewById(R.id.btnSetupEvent);
        btnEventList = (Button) findViewById(R.id.btnEventList);
        btnPushToServer = (Button) findViewById(R.id.btnPushToServer);
    }

    public void setupEvent(View v){
        Intent intent = new Intent(this, SetupEvent.class);
        startActivity(intent);
    }

    public void eventList(View v){
        Intent intent = new Intent(this,EventList.class);
        startActivity(intent);
    }

    public void pushToServer(View v){
        startActivity(new Intent(AdminPage.this, EventsToPush.class));
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
                startActivity(new Intent(AdminPage.this,EventList.class));
                return true;
            case R.id.waitingList:
                startActivity(new Intent(AdminPage.this,EventList.class));
                return true;
            case R.id.addUser:
                startActivity(new Intent(AdminPage.this,EventList.class));
                return true;
            case R.id.setupEvent:
                startActivity(new Intent(AdminPage.this,SetupEvent.class));
                return true;
            case R.id.eventList:
                startActivity(new Intent(AdminPage.this,EventList.class));
                return true;
            case R.id.pustToServer:
                startActivity(new Intent(AdminPage.this,EventList.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
