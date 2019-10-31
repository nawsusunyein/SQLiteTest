package com.example.loginsqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,location,designation;
    Button button;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.txt_name);
        location = (EditText) findViewById(R.id.txt_location);
        designation = (EditText) findViewById(R.id.txt_designation);
        button = (Button) findViewById(R.id.btn_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String userLoc = location.getText().toString();
                String userDesignation = designation.getText().toString();
                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetailsInformation(username,userLoc,userDesignation);
                intent = new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);

                Toast.makeText(MainActivity.this,"Details inserted successfully",Toast.LENGTH_LONG).show();
            }
        });

    }
}
