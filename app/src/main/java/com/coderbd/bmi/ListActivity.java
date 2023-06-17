package com.coderbd.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import android.widget.AdapterView;

public class ListActivity extends AppCompatActivity {
    ListView listviews;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listviews = findViewById(R.id.listview);
        loadData();

        listviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                db.deleteData(new BmiData(position + 1));
                Toast.makeText(ListActivity.this,
                        "Data " + position + " deleted",
                        Toast.LENGTH_SHORT).show();
                loadData();

            }
        });
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());
        List<BmiData> dataList = db.getAllBmiData();

        Toast.makeText(this, "List size: " + dataList.size(),
                Toast.LENGTH_LONG).show();

        BmiAdapter adapter = new BmiAdapter(this, dataList);
        listviews.setAdapter(adapter);
    }
}