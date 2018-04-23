package com.example.rohit.astute;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity
{

    EditText emId,password;
    TextView newUser;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emId=findViewById(R.id.emID);
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
                Log.e("User name is",""+emId.getText());
                Log.e("Password is",""+password.getText());
                /*if(emId.length()==0||password.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Enter valid credentials",Toast.LENGTH_LONG).show();
                }*/

                apiForLogIn();
            }
        });

    }

    public void apiForLogIn()
    {
        final String apiAdd="http://45.126.170.217:9000/UserLogin\n";

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest stringRequest;

        final String mailId=emId.getText().toString().trim();
        final String pass=password.getText().toString().trim();

        stringRequest=new StringRequest(Request.Method.POST, apiAdd, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("Response:=>",response);

                //Log.d("token:=>",response.)
                Boolean code=response.contains("200");
                if(code==true)
                {
                    Toast.makeText(getApplicationContext(),"Sign In successfull",Toast.LENGTH_SHORT).show();
                    Log.d("Response:=>","true");
                    Intent intent=new Intent(LogIn.this,Start.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Insert right credential",Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getApplicationContext(),"network error",Toast.LENGTH_SHORT).show();
                Log.d("Error:=>",""+error);
                Log.d("mailId\n pass",mailId+"\n"+pass);
            }
        })
        {
            @Override
            protected Map<String,String>getParams()
            {
                Map<String,String> map=new HashMap<>();
                map.put("emailId",mailId);
                map.put("password",pass);
                return map;
            }

            /*@Override
            protected Response<String> parseNetworkResponse(NetworkResponse response)
            {
                String responseString="";
                responseString=String.valueOf(response.statusCode);
                Log.d("Token:=>",responseString);
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }*/
        };

        requestQueue.add(stringRequest);
    }
}
