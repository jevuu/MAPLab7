package com.example.d8.maplab7;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            + "(Name CHAR(7), Dept CHAR(7), Year CHAR(4));");
        db.execSQL("CREATE TABLE IF NOT EXISTS 'EmployeeB'"
            + "(Name CHAR(7), Dept CHAR(7), Year CHAR(4));");

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
                addEmployee("EmployeeA", n, d, y, empName, empDept, empYear);
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
                addEmployee("EmployeeB", n, d, y, empName, empDept, empYear);
            }
        });

        //Go to list
        btn_view = findViewById(R.id.btn_view);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent list = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(list);
            }
        });
    }

    //Add employee function
    private void addEmployee(String table, String name, String dept, String year, EditText empName, EditText empDept, EditText empYear){
        Log.d("Name", name);
        Log.d("Dept", dept);
        Log.d("Year", year);

        if(name.matches("") || dept.matches("") || year.matches("")) {
            Toast.makeText(this, "Incomplete", Toast.LENGTH_SHORT).show();
        }else {
            //CompiledStatement used to prevent SQL injection.
            //There is also PreparedStatement, which is used to online databases
            SQLiteStatement stmt = db.compileStatement("INSERT INTO "
                        + table
                        + " (Name, Dept, Year) VALUES(?, ?, ?)");
            stmt.bindString(1, name);
            stmt.bindString(2, dept);
            stmt.bindString(3, year);
            stmt.execute();

            empName.setText("");
            empDept.setText("");
            empYear.setText("");
        }
    }
}
