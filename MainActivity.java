package com.example.funds_transfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import data.DatabaseHandler;
import model.Person;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> personString;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> emailString;
    private ArrayList<String> fundString;
    private ArrayList<String> idString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db=new DatabaseHandler(MainActivity.this);
        listView=findViewById(R.id.listview);
        personString=new ArrayList<>();
        emailString=new ArrayList<>();
        fundString=new ArrayList<>();
        idString=new ArrayList<>();



        List<Person> detailList= db.getAllDetails();
        for (Person person : detailList) {
            personString.add(person.getName());
            emailString.add(person.getEmail());
            fundString.add(String.valueOf(person.getFunds()));
            idString.add(String.valueOf(person.getId()));
        }

        if (detailList.size()<10) {
            db.addDetails(new Person("Kartik Bhasin","kartikbhasin@gmail.com",10000));
            db.addDetails(new Person("Aditi Sharma","aditisharma@gmail.com",10000));
            db.addDetails(new Person("Mayank Uppal","mayankuppal@gmail.com",10000));
            db.addDetails(new Person("Prerna Gulati","prernagulati@gmail.com",10000));
            db.addDetails(new Person("Nipun Seth","nipunseth@gmail.com",10000));
            db.addDetails(new Person("Arpit Saluja","arpitsaluja@gmail.com",10000));
            db.addDetails(new Person("Nikhil Kumar","nikhilkumar@gmail.com",10000));
            db.addDetails(new Person("Mohit Kumar","mohitkumar@gmail.com",10000));
            db.addDetails(new Person("Mohd. Arsh","mohdarsh2235@gmail.com",10000));
            db.addDetails(new Person("Shubh Sharma","shub@gmail.com",10000));
        }

        arrayAdapter=new ArrayAdapter<>(
                 this,
                 android.R.layout.simple_list_item_1,
                 personString
         );

         listView.setAdapter(arrayAdapter);

     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Intent intent= new Intent(MainActivity.this,ShowDetails.class);
             intent.putExtra("name",personString.get(position));
             intent.putExtra("email",emailString.get(position));
             intent.putExtra("funds",fundString.get(position));
             intent.putExtra("id",idString.get(position));
             startActivity(intent);
         }
     });
    }
}