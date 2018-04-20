package com.example.rohit.astute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoList extends AppCompatActivity
{
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);


        fetchData();

    }

    private void fetchData()
    {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        final String dataUrl="http://192.168.0.110:9000/upload/FindAll\n";
        JsonObjectRequest jsonObjectRequest;

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, dataUrl, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {
                    jsonArray=response.getJSONArray("doc");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        String _id=jsonObject.getString("_id");
                        String url=jsonObject.getString("url");
                        String name=jsonObject.getString("name");
                        String shortDescription=jsonObject.getString("shortDescription");
                        String longDescription=jsonObject.getString("longDescription");
                        String imagePath=jsonObject.getString("imagePath");

                        Log.d("_id",_id);
                        Log.d("url",url);
                        Log.d("name",name);
                        Log.d("shortDescription",shortDescription);
                        Log.d("longDescription",longDescription);
                        Log.d("imagePath",imagePath);

                        Log.d("jsonArrayData:=>",""+jsonArray);
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d("Error:=>",""+error);
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
