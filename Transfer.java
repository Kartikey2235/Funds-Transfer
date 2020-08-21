package com.example.funds_transfer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import data.DatabaseHandler;
import model.Person;

public class Transfer extends AppCompatActivity  {
    private ListView listView1;
    private ArrayList<String> personString1;
    private ArrayAdapter<String> arrayAdapter1;
    private ArrayList<String> emailString1;
    private ArrayList<String> fundString1;
    private ArrayList<String> idString1;
    private DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        db=new DatabaseHandler(Transfer.this);


        final String getAmount=getIntent().getStringExtra("id value");

        listView1=findViewById(R.id.transferlistview);
        personString1=new ArrayList<>();
        emailString1=new ArrayList<>();
        fundString1=new ArrayList<>();
        idString1=new ArrayList<>();
        List<Person> detailList= db.getAllDetails();

        for(Person person: detailList){
            personString1.add(person.getName());
            emailString1.add(person.getEmail());
            fundString1.add(String.valueOf(person.getFunds()));
            idString1.add(String.valueOf(person.getId()));
        }

        arrayAdapter1=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                personString1
        );

        listView1.setAdapter(arrayAdapter1);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int i1=Integer.parseInt(idString1.get(position));
                int i2=Integer.parseInt(getAmount);
                if((i1 == i2)){
                    Toast.makeText(Transfer.this,"Money can not be transfered to the same user",Toast.LENGTH_LONG).show();
                }else{
                Intent intent= new Intent(Transfer.this,popup.class);
                intent.putExtra("id value 1",idString1.get(position));
                intent.putExtra("id value 2",getAmount);
                startActivity(intent);
            }
            }
        });
    }

}