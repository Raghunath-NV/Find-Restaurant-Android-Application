package com.example.restuarant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
    EditText city;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        city = (EditText) findViewById(R.id.editText);

    }

    public void searchPressed(View v)           //Method for search operation
    {
        String s= city.getText().toString();    //Storing entered city name into a variable
        if(s.length()>0)
        {
            Intent i=new Intent(this,Restaurants_List_Activity.class);
            i.putExtra("city",s);
            city.setText("");
            startActivity(i);
        }
        else
        {
            Toast.makeText(this,"Please enter City Name",Toast.LENGTH_SHORT).show();
        }
    }
}
