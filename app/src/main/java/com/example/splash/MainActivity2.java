package com.example.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    DatabaseHelper myDB;

    EditText name,ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        myDB=new DatabaseHelper(this);

        name=(EditText)findViewById(R.id.inputName);
        ID=(EditText)findViewById(R.id.inputID);

        Button insert=(Button)findViewById(R.id.bttnInsert);
        Button goTo2=(Button)findViewById(R.id.bttnFromActivity1to2);
        Button goTo3=(Button)findViewById(R.id.bttnFromActivity1to3);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(myDB.addData(Integer.parseInt(ID.getText().toString()),name.getText().toString())==false)
                   Toast.makeText(MainActivity2.this,"Data was not entered into the table \nPlease check your input!",Toast.LENGTH_LONG).show();
               else
                   Toast.makeText(MainActivity2.this,"Data was successfully entered into the table",Toast.LENGTH_LONG).show();






            }
        });

        goTo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this,MainActivity3.class));
            }
        });

        goTo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this,MainActivity4.class));
            }
        });


    }
}