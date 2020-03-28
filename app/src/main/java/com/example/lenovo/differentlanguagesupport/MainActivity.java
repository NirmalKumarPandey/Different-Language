package com.example.lenovo.differentlanguagesupport;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button languageChange;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //change the action bar
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));


        languageChange=(Button)findViewById(R.id.language);
        languageChange.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        if(v==languageChange)
        {
          showDifferentLanuage();
            loadLocale();

        }

    }

    private void showDifferentLanuage()
    {
       final String chooseLanguage[]={"english","hindi","italian","german",};
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(this);
        mBuilder.setTitle("Choose Your Language........");

       mBuilder.setSingleChoiceItems(chooseLanguage, -1, new DialogInterface.OnClickListener()
       {
           @Override
           public void onClick(DialogInterface dialog, int item)
           {
               if(item==0)
               {
                 setLocale("en");
                 recreate();
               }
               if(item==1)
               {
                   setLocale("hi");
                   recreate();
               }
               if(item==2)
               {
                   setLocale("it");
                   recreate();
               }
               if(item==3)
               {
                   setLocale("ge");
                   recreate();
               }

                  dialog.dismiss();
           }
       });
      AlertDialog dialog1= mBuilder.create();

       dialog1.show();

    }

    private void setLocale(String lang)
    {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        //Now save data to sharedPreference.............
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("MyLanguage",lang);
        editor.apply();

    }
    //load Language and saved sharedPreference..........
    public void loadLocale() {
        SharedPreferences pre = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = pre.getString("MyLanguage", "");
        setLocale(language);
    }


}
