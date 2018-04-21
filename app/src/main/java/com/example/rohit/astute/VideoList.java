package com.example.rohit.astute;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoList extends AppCompatActivity
{
    JSONArray jsonArray;
    int k;
    String[] urL;//=new String[jsonArray.length()];
    String[] namE;//=new String[jsonArray.length()];
    String[] sD;//=new String[jsonArray.length()];
    String[] lD;//=new String[jsonArray.length()];
    String[] iP;//=new String[jsonArray.length()];

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        imageView1=findViewById(R.id.imageView1);
        /*imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);*/


        fetchData();
        //fetchImage();

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
                        /*String _id=jsonObject.getString("_id");
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
                        Log.d("imagePath",imagePath);*/

                        urL=new String[jsonArray.length()];
                        namE=new String[jsonArray.length()];
                        sD=new String[jsonArray.length()];
                        lD=new String[jsonArray.length()];
                        iP=new String[jsonArray.length()];

                        for(int j=0;j<jsonArray.length();j++)
                        {
                            jsonObject=jsonArray.getJSONObject(j);
                            urL[j]=jsonObject.getString("url");
                            namE[j]=jsonObject.getString("name");
                            sD[j]=jsonObject.getString("shortDescription");
                            lD[j]=jsonObject.getString("longDescription");
                            iP[j]=jsonObject.getString("imagePath");
                            Log.d("Url Data "+j,urL[j]);
                            Log.d("Name Data "+j,namE[j]);
                            Log.d("Short D "+j,sD[j]);
                            Log.d("Long D "+j,lD[j]);
                            Log.d("Image path "+j,iP[j]);
                        }
                        fetchImage();

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

    private void fetchImage()
    {

            ImageRequest imageRequest=new ImageRequest(iP[k], new Response.Listener<Bitmap>()
            {
                @Override
                public void onResponse(Bitmap response)
                {
                    Log.d("Image","Response");
                    imageView1.setImageBitmap(response);

                    /*else if(k==1)
                    {
                        imageView2.setImageBitmap(response);
                    }
                    else if(k==2)
                    {
                        imageView3.setImageBitmap(response);
                    }
                    else if(k==3)
                    {
                        imageView4.setImageBitmap(response);
                    }
                    else if(k==4)
                    {
                        imageView5.setImageBitmap(response);
                    }
                    else if(k==5)
                    {
                        imageView6.setImageBitmap(response);
                    }*/
                }
            }, 0, 0, null, new Response.ErrorListener()
            {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(getApplicationContext(),"someting went wrong",Toast.LENGTH_SHORT).show();
                    Log.d("ImageError",""+error);
                }
            });
            RequestQueue requestQueue=Volley.newRequestQueue(this);
            requestQueue.add(imageRequest);


    }
}
