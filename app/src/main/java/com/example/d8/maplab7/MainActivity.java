package com.example.d8.maplab7;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText empName;
    EditText empDept;
    EditText empYear;
    Button btn_aAdd;
    Button btn_bAdd;
    Button btn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create database and tables
        db = openOrCreateDatabase("Staff", 0,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS 'EmployeeA'"
            + "(Name CHAR(7), Dept CHAR(7), YEAR CHAR(4));");
        db.execSQL("CREATE TABLE IF NOT EXISTS 'EmployeeB'"
                + "(Name CHAR(7), Dept CHAR(7), YEAR CHAR(4));");

        //Add employee in left form
        btn_aAdd = findViewById(R.id.btn_aAdd);
        btn_aAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empName = findViewById(R.id.aName);
                empDept = findViewById(R.id.aDept);
                empYear = findViewById(R.id.aYear);

                String n = empName.getText().toString();
                String d = empDept.getText().toString();
                String y = empYear.getText().toString();
                addEmployee("EmployeeA", n, d, y);
            }
        });

        //Add employee in right form
        btn_bAdd = findViewById(R.id.btn_bAdd);
        btn_bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empName = findViewById(R.id.bName);
                empDept = findViewById(R.id.bDept);
                empYear = findViewById(R.id.bYear);

                String n = empName.getText().toString();
                String d = empDept.getText().toString();
                String y = empYear.getText().toString();
                addEmployee("EmployeeB", n, d, y);
            }
        });

        //Go to list
        btn_view = findViewById(R.id.btn_view);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent list;
            }
        });
    }

    //Add employee function
    private void addEmployee(String table, String name, String dept, String year){
        db.execSQL("INSERT INTO "
            + table
            + " (Name, Dept, Year) VALUES('"
            + name + "','"
            + dept + "','"
            + year + "');");
    }
}
