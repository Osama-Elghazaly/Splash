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

public class MainActivity4 extends AppCompatActivity {

    DatabaseHelper myDB;

    EditText dltName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        myDB=new DatabaseHelper(this);

        dltName=(EditText)findViewById(R.id.inputNameToDlt);

        Button viewAll=(Button)findViewById(R.id.bttnGetAllData);
        Button dlt=(Button)findViewById(R.id.bttnDelete);
        Button goTo1=(Button)findViewById(R.id.bttnFromActivity3to1);

        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Cursor cur;
                    StringBuffer buffer = new StringBuffer();

                    cur = myDB.getListContents();

                    while (cur.moveToNext()) {
                        for (int i = 0; i < cur.getColumnCount(); i++) {
                            buffer.append(cur.getColumnName(i) + ": " + cur.getString(i) + "\n");
                        }
                        buffer.append("\n");

                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity4.this);
                    builder.setCancelable(true);
                    builder.setTitle("Results");
                    builder.setMessage(buffer.toString());
                    builder.show();

                    buffer.delete(0, buffer.capacity());


                }

        });

        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Osama","Inside delete mehtod");

                int result;


                if (dltName.getText().toString().equals("")) {
                    Toast.makeText(MainActivity4.this, "No input !!", Toast.LENGTH_LONG).show();
                    Log.d("Osama", "No input to delete");
                    result=-1;
                }
                else if(dltName.getText().toString().equals("a")){
                    Log.d("Osama", "Delete inputs with _a_");
                    result=myDB.dltRow(dltName.getText().toString());

                }
                else{
                    Log.d("Osama", "Delete input");
                    result=myDB.dltRow(dltName.getText().toString()); //returns number of deleted rows
                }

                if(result>=1)
                    Toast.makeText(MainActivity4.this,+result+"Row(s) were susscessfully deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity4.this,"No rows were deleted \nPlease try again",Toast.LENGTH_LONG).show();

            }
        });

        goTo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity4.this,MainActivity2.class));
            }
        });
    }
}