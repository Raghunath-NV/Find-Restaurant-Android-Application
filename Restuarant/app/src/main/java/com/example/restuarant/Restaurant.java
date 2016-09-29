package com.example.restuarant;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Raghu on 3/24/16.
 */
public class Restaurant
{
    String name,address,hours,category;
    int[] typeimg;
    float ratings;

    public Restaurant()
    {
        category="";
        typeimg = new int[2];
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", hours='" + hours + '\'' +
                ", category='" + category + '\'' +
                ", ratings=" + ratings +
                '}';
    }

    public static Restaurant createRestaurant(JSONObject restaurantJSON) throws JSONException {

        Restaurant res=new Restaurant();
        String m=null;
        String[] category_temp;
        char a='"';
        res.setName(restaurantJSON.getString("name"));

        if(!restaurantJSON.isNull("address_extended"))          //checking whether address extended tag exists or not
        {
            m= ","+restaurantJSON.getString("address_extended");
        }
            res.setAddress(restaurantJSON.getString("address") + m);
        res.setHours(restaurantJSON.getString("hours_display"));

        m = restaurantJSON.getString("category_labels").replace("[","").replaceAll("]","").replaceAll(String.valueOf(a), "");
        category_temp = m.split(",");
        res.setCategory(category_temp);
        res.setRatings(Float.parseFloat(restaurantJSON.getString("rating")));
        return res;
    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getCategory()
    {
        return category.substring(0,category.length()-1);
    }

    public void setCategory(String[] categorys)
    {
        Set<String> list = new HashSet<String>(Arrays.asList(categorys));
        int count=-1;


        for(String s:list)
        {

            category =category + s +",";

            if(s.equals("Mexican") && count<1)
            {

                count++;
                typeimg[count] = R.drawable.mexico;

            }
            else if(s.equals("Italian") && count<1)
            {

                count++;
                typeimg[count] = R.drawable.italy;

            }
            else if(s.equals("American") && count<1)
            {

                count++;
                typeimg[count] = R.drawable.america;

            }
            else if(s.equals("Japanese") && count<1)
            {

                count++;
                typeimg[count] = R.drawable.japan;

            }
            else if(s.equals("Chinese") && count<1)
            {

                count++;
                typeimg[count] = R.drawable.chinese;

            }
        }
    }

    public float getRatings()
    {

        return ratings;
    }

    public void setRatings(float ratings)
    {
        this.ratings = ratings;
    }


}
