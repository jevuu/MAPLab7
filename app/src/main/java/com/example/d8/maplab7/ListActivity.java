package com.example.d8.maplab7;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor c;
    ListView leftList;
    ListView rightList;
    ArrayList<Employee> employeeA = new ArrayList<>();
    ArrayList<Employee> employeeB = new ArrayList<>();
    ArrayList<String> employeeANames = new ArrayList<>();
    ArrayList<String> employeeBNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Open database and query
        db = openOrCreateDatabase("Staff", 0, null);

        //*******************************************
        //Query EmployeeA table to populate left list
        //*******************************************
        c = db.rawQuery("SELECT * FROM EmployeeA", null);
        int n = c.getColumnIndex("Name");
        int d = c.getColumnIndex("Dept");
        int y = c.getColumnIndex("Year");
        c.moveToFirst();

        if(c != null && c.getCount() > 0){
            do{
                String eN = c.getString(n);
                String eD = c.getString(d);
                String eY = c.getString(y);

                Log.d("A Name", eN);
                Log.d("A Dept", eD);
                Log.d("A Year", eY);

                Employee e = new Employee(eN, eD, eY);
                employeeA.add(e);
                employeeANames.add(eN);
            }while(c.moveToNext());
        }

        while(employeeANames.size() < 15){
            employeeANames.add("");
        }

        leftList = findViewById(R.id.list_left);
        ArrayAdapter<String> leftList_Adapter =
                new ArrayAdapter<>(this, R.layout.layout_list, employeeANames);
        leftList.setAdapter(leftList_Adapter);
        leftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position < employeeA.size()) {
                    openDetails(employeeA.get(position), "A");
                }
            }
        });

        //********************************************
        //Query EmployeeB table to populate right list
        //********************************************
        c = db.rawQuery("SELECT * FROM EmployeeB", null);
        n = c.getColumnIndex("Name");
        d = c.getColumnIndex("Dept");
        y = c.getColumnIndex("Year");
        c.moveToFirst();

        if(c != null && c.getCount() > 0){
            do{
                String eN = c.getString(n);
                String eD = c.getString(d);
                String eY = c.getString(y);

                Log.d("B Name", eN);
                Log.d("B Dept", eD);
                Log.d("B Year", eY);

                Employee e = new Employee(eN, eD, eY);
                employeeB.add(e);
                employeeBNames.add(eN);
            }while(c.moveToNext());
        }

        while(employeeBNames.size() < 15){
            employeeBNames.add("");
        }

        rightList = findViewById(R.id.list_right);
        ArrayAdapter<String> rightList_Adapter =
                new ArrayAdapter<>(this, R.layout.layout_list, employeeBNames);
        rightList.setAdapter(rightList_Adapter);
        rightList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position < employeeB.size()) {
                    openDetails(employeeB.get(position), "B");
                }
            }
        });
    }

    private void openDetails(Employee e, String l){
        Intent intent = new Intent(getApplicationContext(), Details.class);
        intent.putExtra("empName", e.getName());
        intent.putExtra("empDept", e.getDept());
        intent.putExtra("empYear", e.getYear());
        intent.putExtra("listSide", l);
        startActivity(intent);
    }
}
