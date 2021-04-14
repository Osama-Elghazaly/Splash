package com.example.splash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText findName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        myDB=new DatabaseHelper(this);

        findName=(EditText)findViewById(R.id.inputNameToSearch);

        Button search=(Button)findViewById(R.id.bttnSearch);

        Button goTo1=(Button)findViewById(R.id.bttnFromActivity2to1);

        goTo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this,MainActivity2.class));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (findName.getText().toString().equals("")) {
                    Toast.makeText(MainActivity3.this, "No input !!", Toast.LENGTH_LONG).show();
                    Log.d("Osama", "No input to search");
                }

                else {
                    Log.d("Osama","There is input and its searching");

                    Cursor cur;
                    StringBuffer buffer = new StringBuffer();

                    cur = myDB.getSpecificResult(findName.getText().toString());

                    while (cur.moveToNext()) {
                        for (int i = 0; i < cur.getColumnCount(); i++) {
                            buffer.append(cur.getColumnName(i) + ": " + cur.getString(i) + "\n");
                        }
                        buffer.append("\n");

                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                    builder.setCancelable(true);
                    builder.setTitle("Results");
                    builder.setMessage(buffer.toString());
                    builder.show();

                    buffer.delete(0, buffer.capacity());

                }
            }
        });


    }
}