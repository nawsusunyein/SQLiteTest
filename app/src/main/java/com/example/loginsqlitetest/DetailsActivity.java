package com.example.loginsqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        DbHandler dbHandler = new DbHandler(DetailsActivity.this);
        ArrayList<HashMap<String,String>> userList = dbHandler.getUsers();
        ListView listview = (ListView) findViewById(R.id.userlist);
        ListAdapter adapter = new SimpleAdapter(DetailsActivity.this,userList,R.layout.list_row,new String[] {"name","designation","location"},new int[] {R.id.name,R.id.designation,R.id.location});
        listview.setAdapter(adapter);

        Button back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetailsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
