package com.example.restuarant;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Restaurants_List_Activity extends AppCompatActivity implements GetDataAsync.IRestaurant
{

    public ArrayList<Restaurant> RList;
    ListView lv;
    RestaurantAdapter adapter;
    String key="5AAbEf2Ibd8vUnQ7Q4TmsR8noFZXHg8cFHupsaFP";
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants__list_);
        lv = (ListView) findViewById(R.id.listView);
        pd= new ProgressDialog(this);
        pd.setMessage("Loading Data");
        pd.show();
        if(getIntent().getExtras()!=null)
        {
            String city = getIntent().getStringExtra("city").trim();
            city=city.replaceAll(" ","%20");
            new GetDataAsync(this).execute("http://api.v3.factual.com/t/restaurants-us/read?filters=%7B%22rating%22:%7B%22$gte%22:0.0%7D,%22locality%22:%22" + city +"%22%7D&KEY="+ key);
        }
    }

    @Override
    public void getRestaurants(ArrayList<Restaurant> ResList)
    {

        if(ResList.size()==0)
        {
            Toast.makeText(this,"Entered city is not in our database. Please enter another city",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,MainActivity.class);
            finish();
            pd.dismiss();
            startActivity(i);
        }

        RList = ResList;
        adapter = new RestaurantAdapter(this, R.layout.entry_layout, RList);
        lv.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
        pd.dismiss();
    }
}
