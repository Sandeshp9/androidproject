package com.example.whatstheweather;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String loc= "";
        if(getIntent().hasExtra("Location")){
            TextView tv =(TextView) findViewById(R.id.location);
            loc = Objects.requireNonNull(getIntent().getExtras()).getString("Location");
            tv.setText(loc);
        }
        String URL = "https://api.openweathermap.org/data/2.5/forecast?q="+loc+",in&APPID=d0ab0b374135800cf14795258913d357&units=metric";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest=new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest Response",response.toString());
                        String str1 = "";
                        String str2 = "";
                        TextView tv1 = (TextView) findViewById(R.id.day1);
                        TextView tv2 = (TextView) findViewById(R.id.day2);
                        TextView tv3 = (TextView) findViewById(R.id.day3);
                        TextView tv4 = (TextView) findViewById(R.id.day4);
                        TextView tv5 = (TextView) findViewById(R.id.day5);

                        try {
                            JSONArray jsonArray = response.getJSONArray("list");

                            JSONObject list = jsonArray.getJSONObject(0);
                            str1 = list.getJSONObject("main").getString("temp");
                            str2 = list.getJSONObject("main").getString("humidity");
                            tv1.setText("Day 1:Temperature(C):"+str1+" and Humidity(%):"+str2);
                            JSONObject list2 = jsonArray.getJSONObject(1);
                            str1 = list2.getJSONObject("main").getString("temp");
                            str2 = list2.getJSONObject("main").getString("humidity");
                            tv2.setText("+3 hours:Temperature(C):"+str1+" and Humidity(%):"+str2);
                            JSONObject list3 = jsonArray.getJSONObject(2);
                            str1 = list3.getJSONObject("main").getString("temp");
                            str2 = list3.getJSONObject("main").getString("humidity");
                            tv3.setText("+3 hours:Temperature(C):"+str1+" and Humidity(%):"+str2);
                            JSONObject list4 = jsonArray.getJSONObject(3);
                            str1 = list4.getJSONObject("main").getString("temp");
                            str2 = list4.getJSONObject("main").getString("humidity");
                            tv4.setText("+3 hours:Temperature(C):"+str1+" and Humidity(%):"+str2);
                            JSONObject list5 = jsonArray.getJSONObject(4);
                            str1 = list5.getJSONObject("main").getString("temp");
                            str2 = list5.getJSONObject("main").getString("humidity");
                            tv5.setText("+3 hours:Temperature(C):"+str1+" and Humidity(%):"+str2);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response",error.toString());

                    }
                }
        );

        requestQueue.add(objectRequest);
    }
}
