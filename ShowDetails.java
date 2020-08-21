package com.example.funds_transfer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShowDetails extends AppCompatActivity {
private TextView textViewName;
private TextView Email;
private TextView Funds;
private Button TransferButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        textViewName=findViewById(R.id.textviewName);
        Email=findViewById(R.id.editTextTextEmailAddress);
        Funds=findViewById(R.id.editTextNumber);
        TransferButton=findViewById(R.id.button);

        String nameValue=getIntent().getStringExtra("name");
        String emailValue=getIntent().getStringExtra("email");
        String fundsValue=getIntent().getStringExtra("funds");
        final String idValue=getIntent().getStringExtra("id");
        Log.d("kartik", "onCreate: "+idValue);

        textViewName.setText(nameValue);
        Email.setText(emailValue);
        Funds.setText(fundsValue);

        TransferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ShowDetails.this,Transfer.class);
                intent.putExtra("id value",idValue);
                startActivity(intent);
            }
        });

    }
}