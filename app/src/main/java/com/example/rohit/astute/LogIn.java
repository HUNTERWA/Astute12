package com.example.rohit.astute;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogIn extends AppCompatActivity
{

    EditText userName,password;
    TextView newUser;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        newUser=findViewById(R.id.newUser);
        login=findViewById(R.id.logIn);

        newUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e("User name is",""+userName.getText());
                Log.e("Password is",""+password.getText());
                if(userName.length()==0||password.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Enter valid credentials",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
