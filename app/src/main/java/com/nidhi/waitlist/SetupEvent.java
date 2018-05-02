package com.nidhi.waitlist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SetupEvent extends AppCompatActivity{

    private EditText etEventName, etEventLocation, etEventStartDate, etEventEndDate;
    private int mYear, mMonth, mDay;
    String EventStartDate, EventEndDate="",Event_Name, Event_Location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        toolbar.showOverflowMenu();
        initializeControls();

        etEventStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(SetupEvent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                EventStartDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                etEventStartDate.setText(EventStartDate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        etEventEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(SetupEvent.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                EventEndDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                etEventEndDate.setText(EventEndDate);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

    }

    private void initializeControls() {
        etEventName = (EditText) findViewById(R.id.etEventName);
        etEventLocation = (EditText) findViewById(R.id.etEventLocation);
        etEventStartDate = (EditText) findViewById(R.id.etEventStartDate);
        etEventEndDate = (EditText) findViewById(R.id.etEventEndDate);

    }

    public void addEvent(View v){

        Event_Name = etEventName.getText().toString();
        Event_Location = etEventLocation.getText().toString();
        EventStartDate = etEventStartDate.getText().toString();
        EventEndDate = etEventEndDate.getText().toString();
        if(fieldsValidate()) {
            BackgroundTaskEvent backgroundTaskEvent = new BackgroundTaskEvent(this);
            backgroundTaskEvent.execute("addEvent", Event_Name, Event_Location, EventStartDate, EventEndDate);
        }
    }
    private boolean fieldsValidate(){
        boolean validFlag = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date startDate=null, endDate=null;

        try {
            startDate = sdf.parse(EventStartDate);
            endDate = sdf.parse(EventEndDate);
        }
        catch (ParseException e) {
            e.printStackTrace();

        }

        if(Event_Name.equals("")){
            etEventName.setError(getText(R.string.mandatoryField));
            return false;
        }
        else if(Event_Location.equals("")){
            etEventLocation.setError(getText(R.string.mandatoryField));
            return false;
        }
        else if(EventStartDate.equals("")){
            etEventStartDate.setError(getText(R.string.mandatoryField));
            return false;
        }
        else if(EventEndDate.equals("")){
            etEventEndDate.setError(getText(R.string.mandatoryField));
            return false;
        }
        else if(!isValidDate(EventStartDate)){
            etEventStartDate.setError(getText(R.string.invalidDate));
            return false;
        }
        else if(!isValidDate(EventEndDate)){
            etEventEndDate.setError(getText(R.string.invalidDate));
            return false;
        }
        else if(endDate.before(startDate)){
            etEventEndDate.setError(getText(R.string.dateError));
            Toast.makeText(SetupEvent.this,R.string.dateError,Toast.LENGTH_LONG).show();
            return false;
        }
        else
            return true;

    }


    public boolean isValidDate(String Date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date dateObject;

        try {
            dateObject = sdf.parse(Date);
            return true;
        }
        catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
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
                startActivity(new Intent(SetupEvent.this,EventList.class));
                return true;
            case R.id.waitingList:
                startActivity(new Intent(SetupEvent.this,EventList.class));
                return true;
            case R.id.addUser:
                startActivity(new Intent(SetupEvent.this,EventList.class));
                return true;
            case R.id.setupEvent:
                startActivity(new Intent(SetupEvent.this,SetupEvent.class));
                return true;
            case R.id.eventList:
                startActivity(new Intent(SetupEvent.this,EventList.class));
                return true;
            case R.id.pustToServer:
                startActivity(new Intent(SetupEvent.this,EventList.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
