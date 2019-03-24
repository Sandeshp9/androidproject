package com.example.whatstheweather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button daily = (Button) findViewById(R.id.daily);
        Button activity2 = (Button) findViewById(R.id.Activity2);
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startdaily = new Intent(getApplicationContext(),Main3Activity.class);
                TextView search2 = (TextView) findViewById(R.id.search);
                String location = search2.getText().toString();
                startdaily.putExtra("Location", location);
                startActivity(startdaily);
            }
        });
        activity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startintent = new Intent(getApplicationContext(), Main2Activity.class);
                TextView search = (TextView) findViewById(R.id.search);
                String location = search.getText().toString();
                startintent.putExtra("Location", location);
                startActivity(startintent);
            }
        });

    }
}
