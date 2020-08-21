package com.example.funds_transfer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import data.DatabaseHandler;
import model.Person;

public class popup extends AppCompatActivity {
    EditText amount;
    Button saveButton;
    DatabaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);
        db=new DatabaseHandler(popup.this);

        amount=findViewById(R.id.item);
        saveButton=findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!amount.getText().toString().isEmpty()){
                    saveItem(view);
                }else{
                    Toast.makeText(popup.this,"Enter Amount",Toast.LENGTH_LONG).show();
                }
            }
        });
}
 private void saveItem(View view) {
        String getAmount1=getIntent().getStringExtra("id value 1");
        String getAmount2=getIntent().getStringExtra("id value 2");

        Person person1=db.getDetails(Integer.parseInt(getAmount1));
        Person person2=db.getDetails(Integer.parseInt(getAmount2));

        int person1Amount=person1.getFunds();
        int person2Amount=person2.getFunds();
        int size=Integer.parseInt(amount.getText().toString().trim());

        if(person2Amount>=size) {
            person2.setFunds(person2Amount - size);
            person1.setFunds(person1Amount+size);

            db.updateDetails(person1);
            db.updateDetails(person2);
            Toast.makeText(popup.this,"Sucess",Toast.LENGTH_LONG).show();

            Intent intent=new Intent(popup.this,MainActivity2.class);
            intent.putExtra("balance",getAmount2);
            startActivity(intent);

        }else {
            Toast.makeText(popup.this,"Enter Amount Less Than "+person2Amount,Toast.LENGTH_LONG).show();
        }
      }
}