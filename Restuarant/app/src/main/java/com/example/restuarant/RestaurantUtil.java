package com.example.restuarant;



import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


/**
 * Created by Raghu on 3/24/16.
 */
public class RestaurantUtil
{
    public static ArrayList<Restaurant> parseRestaurant(String result) throws JSONException {

        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        JSONObject root = new JSONObject(result);
        JSONObject root1 = root.getJSONObject("response");
        JSONArray restJSONArray = root1.getJSONArray("data");

     for (int i=0;i<restJSONArray.length();i++)
        {
            JSONObject resJSON = (JSONObject) restJSONArray.get(i);
            Restaurant res = Restaurant.createRestaurant(resJSON);
            restaurantList.add(res);
        }

        return restaurantList;
    }

}
