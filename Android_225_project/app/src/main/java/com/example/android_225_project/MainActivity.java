package com.example.android_225_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String PREFER_NAME = "Reg";
    UserSession session;

    private SharedPreferences sharedPreferences;
    EditText username,password;
    Button login;
    TextView new_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new UserSession(getApplicationContext());
        sharedPreferences = getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

        login=findViewById(R.id.LOGIN);

        new_user=findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtusername = username.getText().toString();
                String txtpassword = password.getText().toString();

                if (txtusername.trim().length() > 0 && txtpassword.trim().length() > 0) {
                    String uName = null;
                    String uPassword = null;

                    if (sharedPreferences.contains("Name")) {
                        uName = sharedPreferences.getString("Name", "");

                    }

                    if (sharedPreferences.contains("txtPassword")) {
                        uPassword = sharedPreferences.getString("txtPassword", "");

                    }

                    // Object uName = null;
                    // Object uEmail = null;
                    if (txtusername.equals(uName) && txtpassword.equals(uPassword)) {

                        session.createUserLoginSession(uName,
                                uPassword);

                        // Starting MainActivity
                        Intent i = new Intent(getApplicationContext(), HomePage.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        finish();

                    } else {

                        // username / password doesn't match&
                        Toast.makeText(getApplicationContext(),
                                "Username/Password is incorrect",
                                Toast.LENGTH_LONG).show();

                    }
                } else {

                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(),
                            "Please enter username and password",
                            Toast.LENGTH_LONG).show();

                }
            }


        });

        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Signup.class);
                startActivity(i);
            }
        });

    }
}
