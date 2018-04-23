package com.example.rohit.astute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoList extends AppCompatActivity
{
    public int k=0;
    ListView listView;
    JSONArray jsonArray;
    JSONObject jsonObject;
    public String[] urL;//=new String[jsonArray.length()];
    public String[] namE;//=new String[jsonArray.length()];
    public String[] sD;//=new String[jsonArray.length()];
    public String[] lD;//=new String[jsonArray.length()];
    public String[] iP;//=new String[jsonArray.length()];

    //ImageView imageView1;//,imageView2;//,imageView3,imageView4,imageView5,imageView6;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        listView=findViewById(R.id.listView);


        //imageView2=findViewById(R.id.imageView2);
        /*imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);*/


        fetchData();
        //fetchImage();

        //CustomList customList=new CustomList();
        //listView.setAdapter(customList);
    }

    private void fetchData()
    {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        final String dataUrl="http://45.126.170.217:9000/upload/FindAll\n";
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
                        //JSONObject jsonObject=jsonArray.getJSONObject(i);


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
                    }
                    k=iP.length;
                    Log.d("k:=>",""+k);
                    CustomList customList=new CustomList();
                    listView.setAdapter(customList);
                    Log.d("k=>",""+k);

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
        //CustomList customList=new CustomList();
        //listView.setAdapter(customList);
    }

    class CustomList extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            //VideoList videoList=new VideoList();
            Log.d("I am in Custom list",""+k);
            return k;
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent)
        {
            convertView=getLayoutInflater().inflate(R.layout.customl,null);

            ImageView imageView = convertView.findViewById(R.id.imageView);
            Picasso.get().load(iP[i]).into(imageView);
            Log.d("ImageAddress",iP[i]);

            TextView title = convertView.findViewById(R.id.ttl);
            title.setText(namE[i]);

            TextView description = convertView.findViewById(R.id.description);
            description.setText(sD[i]);


            return convertView;
        }
    }
}
