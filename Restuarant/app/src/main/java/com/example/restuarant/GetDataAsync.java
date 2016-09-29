package com.example.restuarant;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Raghu on 3/24/16.
 */
public class GetDataAsync extends AsyncTask<String,Void,ArrayList<Restaurant>>
        {

            IRestaurant activity;
            public GetDataAsync()
            {

            }

            public GetDataAsync(IRestaurant activity) {
                this.activity = activity;
            }

            @Override
            protected ArrayList<Restaurant> doInBackground(String... params)
            {
                try {

                    URL url = null;
                    url = new URL(params[0]);
                    HttpURLConnection con = null;
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.connect();
                    int statusCode = 0;
                    statusCode = con.getResponseCode();
                    if (statusCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = null;
                        try {
                            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        try {
                            line = reader.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        while (line != null) {
                            sb.append(line);
                            try {
                                line = reader.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                            return RestaurantUtil.parseRestaurant(sb.toString());
                    }

                    }catch(MalformedURLException e){
                        e.printStackTrace();
                    }catch(IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;

            }

            @Override
            protected void onPostExecute(ArrayList<Restaurant> restaurants)
            {

                activity.getRestaurants(restaurants);
            }


            static public interface IRestaurant
            {
                public void getRestaurants(ArrayList<Restaurant> RList);
            }
        }
