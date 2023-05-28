package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class buyMedicineBookActivity extends AppCompatActivity {

    EditText edname,edaddress,edcontact,edpincode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname=findViewById(R.id.editTextBMBFullName);
        edaddress=findViewById(R.id.editTextBMBAddress);
        edcontact=findViewById(R.id.editTextBMBContact);
        edpincode=findViewById(R.id.editTextBMBPinCode);
        btnBooking=findViewById(R.id.buttonBMBBooking);


        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edname.getText().toString(),edaddress.getText().toString(),edcontact.getText().toString(),Integer.parseInt(edpincode.getText().toString()),date.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(buyMedicineBookActivity.this, "Your Booking is Done Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(buyMedicineBookActivity.this,HomeActivity.class));
            }
        });
    }
}