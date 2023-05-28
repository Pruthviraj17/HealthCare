package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    private EditText ed1,ed2;
    private Button btn;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        ed1=findViewById(R.id.editTextLoginUsername);
        ed2=findViewById(R.id.editTextLoginPassword);
        btn=findViewById(R.id.loginButton);
        txt=findViewById(R.id.textViewLoginNewUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username, password;
                username = ed1.getText().toString();
                password = ed2.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if (username.length()==0 || password.length()==0)
                    Toast.makeText(LoginPage.this, "Please fill username or password correctly", Toast.LENGTH_SHORT).show();
                else {
                    if(db.login(username,password)==1) {
                        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("username",username);
                        // to save our data with key value
                        editor.apply();
                        String welcomeusername=sharedPreferences.getString("username","").toString();
                        Toast.makeText(LoginPage.this, "Welcome "+welcomeusername, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginPage.this,HomeActivity.class));
                    }
                    else
                        Toast.makeText(LoginPage.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this,RegisterPage.class));
            }
        });
    }

}