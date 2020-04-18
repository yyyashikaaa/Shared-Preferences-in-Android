package com.example.android_225_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText s_username,s_password,s_email;
    Button signup;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        s_username=findViewById(R.id.s_username);
        s_password=findViewById(R.id.s_password);
        s_email=findViewById(R.id.s_email);

        signup=findViewById(R.id.s_signup);

        sharedPreferences = getApplicationContext().getSharedPreferences("Reg", 0);
        editor = sharedPreferences.edit();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = s_username.getText().toString();
                String email = s_email.getText().toString();
                String pass = s_password.getText().toString();

                if(s_username.getText().length()<=0){
                    Toast.makeText(Signup.this, "Enter name", Toast.LENGTH_SHORT).show();
                }
                else if( s_email.getText().length()<=0){
                    Toast.makeText(Signup.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
                else if( s_password.getText().length()<=0){
                    Toast.makeText(Signup.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                else{
                    editor.putString("Name", name);
                    editor.putString("Email",email);
                    editor.putString("txtPassword",pass);
                    editor.commit();}   // commit the values

                Intent i=new Intent(Signup.this,MainActivity.class);
                startActivity(i);

            }
        });
    }
}
