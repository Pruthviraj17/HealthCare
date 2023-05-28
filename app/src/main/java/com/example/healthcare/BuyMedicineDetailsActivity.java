package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnBack,btnAddTocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName=findViewById(R.id.textViewHADTitle);
        tvTotalCost=findViewById(R.id.textViewBMCartTotalCost);
        edDetails=findViewById(R.id.editTextBMDMultiLine);
        edDetails.setKeyListener(null);
        btnBack=findViewById(R.id.buttonBMDBack);
        btnAddTocart=findViewById(R.id.buttonBMDAddToCart);

        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost: "+intent.getStringExtra("text3")+"/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this,buyMedicineActivity.class));
            }
        });

        btnAddTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=tvPackageName.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Record Inserted to Cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,buyMedicineActivity.class));
                }
            }
        });
    }
}