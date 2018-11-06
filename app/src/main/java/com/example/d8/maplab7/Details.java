package com.example.d8.maplab7;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    Intent intent;
    TextView empName;
    TextView empDept;
    TextView empYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        intent = getIntent();
        String n = intent.getStringExtra("empName");
        String d = intent.getStringExtra("empDept");
        String y = intent.getStringExtra("empYear");
        String b = intent.getStringExtra("listSide");

        if(b.matches("A")){
            getWindow().getDecorView().setBackgroundColor(0xffff4444);
        }else{
            getWindow().getDecorView().setBackgroundColor(0xffffbb33);
        }

        empName = findViewById(R.id.name);
        empDept = findViewById(R.id.dept);
        empYear = findViewById(R.id.year);

        empName.setText(n);
        empDept.setText(d);
        empYear.setText(y);
    }
}
