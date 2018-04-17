package com.example.rohit.astute;

import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    EditText name,age,email;
    Spinner spinner;
    Button button;
    String[] gender={"male","female","other"};

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        email=findViewById(R.id.email);
        spinner=findViewById(R.id.spinner);
        button=findViewById(R.id.signUp);

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,gender);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        //spinner.setDrawingCacheBackgroundColor(getColor(R.color.colorPrimary));
        //spinner.setBackgroundColor(getColor(R.color.colorPrimary));
        //String gender= (String) spinner.getSelectedItem();
        //Log.d("Selected item is",gender);


        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                apiCalling();
            }
        });

    }

    private void apiCalling()
    {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest;

        final String apiAdd="http://192.168.0.110:9000/user/insert\n";
        final String authKey="";

        final String userName=name.getText().toString().trim();
        final String ag=age.getText().toString().trim();
        final String emailid=email.getText().toString().trim();
        final String gender= (String) spinner.getSelectedItem();
        final String macaddress= Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        stringRequest=new StringRequest(Request.Method.POST, apiAdd, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                Toast.makeText(getApplicationContext(),"Sign up successfull",Toast.LENGTH_LONG).show();
                Log.d("Response is:=>",response);
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                Log.d("Error is:=>",""+error);
            }
        })
        {
            @Override
            protected Map<String,String>getParams()
            {
                Map<String,String> map=new HashMap<String, String>();
                map.put("name",userName);
                map.put("age",ag);
                map.put("gender",gender);
                map.put("emailId",emailid);
                map.put("macAddress",macaddress);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String gender= (String) spinner.getSelectedItem();
        Log.d("Selected item is",gender);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {
        Toast.makeText(getApplicationContext(),"Gender is not selected",Toast.LENGTH_LONG);
    }
}
