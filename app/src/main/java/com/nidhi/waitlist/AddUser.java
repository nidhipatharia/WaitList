package com.nidhi.waitlist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddUser extends AppCompatActivity {

    String Event_Id;
    String MyPREFERENCES = "My Prefs";
    SharedPreferences sharedPreferences;
    EditText etName, etEmail, etPhone;
    String Name, Email, Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        toolbar.showOverflowMenu();
        initializeControls();


    }

    private void initializeControls() {
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhone = (EditText) findViewById(R.id.etPhone);
    }

    public void addUser(View v){

        Name = etName.getText().toString();
        Email = etEmail.getText().toString();
        Phone = etPhone.getText().toString();
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Event_Id = sharedPreferences.getString("Event_Id", "");
        if(fieldsValidate()) {
            BackgroundTaskUserWaiting backgroundTaskUserWaiting = new BackgroundTaskUserWaiting(this);
            backgroundTaskUserWaiting.execute("addWaitingUser", Event_Id, Name, Email, Phone);
        }

    }
    private boolean fieldsValidate(){
        if(Name.equals("")){
            etName.setError(getText(R.string.mandatoryField));
            return false;
        }

        else if(!Email.equals("") && !Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            etEmail.setError(getText(R.string.emailError));
            return false;
        }

        else if(!Phone.equals("") && !Patterns.PHONE.matcher(Phone).matches()){
            etEmail.setError(getText(R.string.phoneError));
            return false;
        }
        else
            return true;

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
                startActivity(new Intent(AddUser.this,EventList.class));
                return true;
            case R.id.waitingList:
                startActivity(new Intent(AddUser.this,EventList.class));
                return true;
            case R.id.addUser:
                startActivity(new Intent(AddUser.this,EventList.class));
                return true;
            case R.id.setupEvent:
                startActivity(new Intent(AddUser.this,SetupEvent.class));
                return true;
            case R.id.eventList:
                startActivity(new Intent(AddUser.this,EventList.class));
                return true;
            case R.id.pustToServer:
                startActivity(new Intent(AddUser.this,EventList.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
