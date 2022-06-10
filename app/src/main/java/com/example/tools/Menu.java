package com.example.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {
    // Menu class with 3 buttons leading to Standard/Length/Currency pages

    Button standard, length, currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        standard = findViewById(R.id.buttonStandard);
        length = findViewById(R.id.buttonLength);
        currency = findViewById(R.id.buttonCurrency);

        // Button to Standard Calculator page
        standard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent switchStandard = new Intent(Menu.this, Standard.class);
                startActivity(switchStandard);
            }
        });

        // Button to Length Converter page
        length.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent switchStandard = new Intent(Menu.this, Length.class);
                startActivity(switchStandard);
            }
        });

        // Button to Currency Converter page
        currency.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent switchStandard = new Intent(Menu.this, Currency.class);
                startActivity(switchStandard);
            }
        });
    }
}