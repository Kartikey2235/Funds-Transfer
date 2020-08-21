package com.example.funds_transfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Person;

public class MainActivity2 extends AppCompatActivity {
    TextView balance;
    Button transaction;
    Button end;


    @Override
    public void onBackPressed() {
        Toast.makeText(MainActivity2.this,"You can not go back at this stage",Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        DatabaseHandler db=new DatabaseHandler(MainActivity2.this);
        balance=findViewById(R.id.balance);
        transaction=findViewById(R.id.button4);
        end=findViewById(R.id.button5);

        String getId= getIntent().getStringExtra("balance");
        Person person2=db.getDetails(Integer.parseInt(getId));
        String getBalance= String.valueOf(person2.getFunds());
        balance.setText(getBalance);

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
                finish();
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,StartingActivity.class));
                finish();
            }
        });
    }
}