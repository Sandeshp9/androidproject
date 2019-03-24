package com.example.whatstheweather;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        String loc="";


        if(getIntent().hasExtra("Location")) {
            TextView tv = (TextView) findViewById(R.id.daily);
            loc = Objects.requireNonNull(getIntent().getExtras()).getString("Location");
            tv.setText(loc);
        }

        String URL = "http://api.openweathermap.org/data/2.5/weather?q="+loc+",in&APPID=d0ab0b374135800cf14795258913d357&units=metric";


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
                        String str3 = "";
                        String str4 = "";

                        try {
                            str1 =response.getJSONObject("main").getString("temp");
                            str2 =response.getJSONObject("main").getString("humidity");
                            str3 =response.getJSONObject("wind").getString("speed");
                            str4 =response.getJSONObject("main").getString("pressure");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        TextView tv1 = (TextView) findViewById(R.id.temp);
                        tv1.setText("Temperature(C):"+str1);
                        TextView tv2 = (TextView) findViewById(R.id.humidity);
                        tv2.setText("Humidity(%):"+str2);
                        TextView tv3 = (TextView) findViewById(R.id.wind);
                        tv3.setText("Wind Speed(m/s):"+str3);
                        TextView tv4 = (TextView) findViewById(R.id.pres);
                        tv4.setText("Pressure(Pa):"+str4);
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
