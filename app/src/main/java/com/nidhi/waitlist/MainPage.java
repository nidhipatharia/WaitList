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

/**
 * Created by nidhipatharia on 8/4/16.
 */
public class MainPage extends AppCompatActivity {

    Button btnAdmin, btnWaitingList,btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        //toolbar.setOverflowIcon(R.drawable.quicknavicon);
        toolbar.showOverflowMenu();


        initializeControl();
    }
    private void initializeControl(){
        btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnWaitingList = (Button) findViewById(R.id.btnWaitingList);
        btnSignOut = (Button) findViewById(R.id.btnSignOut);
    }

    public void admin(View v){
        Intent intent = new Intent(this,AdminPage.class);
        startActivity(intent);
    }

    public void waitingList(View v){
        startActivity(new Intent(this, EventList.class));
    }

    public void signOut(View v){
        startActivity(new Intent(MainPage.this,Login.class));
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
                startActivity(new Intent(MainPage.this,EventList.class));
                return true;
            case R.id.waitingList:
                startActivity(new Intent(MainPage.this,EventList.class));
                return true;
            case R.id.addUser:
                startActivity(new Intent(MainPage.this,EventList.class));
                return true;
            case R.id.setupEvent:
                startActivity(new Intent(MainPage.this,SetupEvent.class));
                return true;
            case R.id.eventList:
                startActivity(new Intent(MainPage.this,EventList.class));
                return true;
            case R.id.pustToServer:
                startActivity(new Intent(MainPage.this,EventList.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
