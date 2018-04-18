package com.example.rohit.astute;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity
{
    //ImageView imageView;
    TextView textView,tagLine;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //imageView=findViewById(R.id.imageView);
        textView=findViewById(R.id.textView);
        tagLine=findViewById(R.id.tagLine);
        tagLine.animate().translationX(1000).setDuration(0);

        //Code for Main Text view
        Thread thread=new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(500);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    //imageView.animate().alpha(1f).setDuration(2500);
                    textView.animate().alpha(1f).setDuration(2000);
                    tagLine.animate().translationX(0).setDuration(1000);
                }
            }
        };
        thread.start();

        //Code for Splash screen
        Handler handler=new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }

}
