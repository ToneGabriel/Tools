package com.example.tools;

import static java.lang.Math.pow;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class UtilityTools extends AppCompatActivity {
    // Utility Class

    double num1 = 0, num2 = 0;
    String operator="";

    // Digits
    public void setDigitButton(Button b, TextView resultField){
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String digit = b.getText().toString();
                if(digit.equals("0")) {
                    if (!resultField.getText().toString().equals("0") && !resultField.getText().toString().equals("-0"))
                        resultField.append(digit);
                }
                else {
                    if(resultField.getText().toString().equals("0") || resultField.getText().toString().equals("-0"))
                        resultField.setText("");
                    resultField.append(digit);
                }
            }
        });
    }

    // Pi
    public void setPiButton(Button b, TextView resultField) {
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resultField.setText(R.string.pi);       //TODO: check values
            }
        });
    }

    // E
    public void setEButton(Button b, TextView resultField){
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resultField.setText(R.string.e);
            }
        });
    }

    // Dot
    public void setDotButton(Button b, TextView resultField){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(!resultField.getText().toString().contains("."))
                    resultField.append(".");
            }
        });
    }

    // Delete ALL
    public void setCButton(Button b, TextView resultField, TextView progressField) {
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resultField.setText("0");
                progressField.setText("");
                num1 = 0;
                num2 = 0;
                operator = "";
            }
        });
    }

    // Delete ALL variant
    public void setCButton(Button b, TextView resultField){
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resultField.setText("0");
            }
        });
    }

    // Called in "Equal" Button Setter
    // Executes binary operation
    public String operation(){
        double result;

        switch (operator)
        {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "x":
                result = num1 * num2;
                break;
            case "%":
                result = num1 % num2;
                break;
            case "^":
                result = pow(num1, num2);
                break;
            default:
                result = 0;
        }
        return formatDecimals(result,"#.########");
    }

    // Format numbers
    public String formatDecimals(double number, String pattern) {
        Locale locale = new Locale("en", "US");
        DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        df.applyPattern(pattern);
        return df.format(number);
    }

    // Check internet connection
    // Called in "convert()"
    public boolean isInternetConnected(Context context) {
        boolean connected;
        try {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());  // need ACCESS_NETWORK_STATE permission
            connected = capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
        }catch(Exception e){
            return false;
        }
        return connected;
    }

    protected void findViewsById() {}

    protected void setFunctionality() {}

    protected class UnitStruct {

        public double DefaultUnit;
        public TextView Placeholder;

        public UnitStruct(double defaultUnit, TextView placeholder)
        {
            this.DefaultUnit = defaultUnit;
            this.Placeholder = placeholder;
        }
    }

}
