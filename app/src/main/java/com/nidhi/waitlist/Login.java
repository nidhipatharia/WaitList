package com.nidhi.waitlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        initializeControls();
    }
    private void initializeControls(){
      etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
    }

    public void login(View v){
        if(etUsername.getText().toString().equals("admin") && etPassword.getText().toString().equals("admin")){
            Intent myIntent = new Intent(Login.this, MainPage.class);
            myIntent.putExtra("loggedIn", "true"); //Optional parameters
            Login.this.startActivity(myIntent);
        }
        else
            Toast.makeText(this,R.string.loginError,Toast.LENGTH_SHORT).show();

    }

}
