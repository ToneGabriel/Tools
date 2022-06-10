package com.example.tools;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class Currency extends UtilityTools {
    // Currency Converter class

    LinearLayout numbersLayout, currencyLayout;
    TextView currencyDropdown, convertField;

    Button buttonRON, buttonEUR, buttonUSD, buttonGBP;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonDot, buttonC, buttonOK;

    TextView numberRON, numberEUR, numberUSD, numberGBP;
    Hashtable<String, UnitStruct> currencyStructTable = new Hashtable<String, UnitStruct>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        findViewsById();
        populateStructTable();
        setFunctionality();
    }

    // SETTERS and other functions

    //====================Main View====================

    // Shows currency list view
    public void setOnClickDropdown(TextView dropdown){
        dropdown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currencyLayout.setVisibility(LinearLayout.VISIBLE);
            }
        });
    }

    // Shows numbers view
    public void setOnClickConvert(TextView convert){
        convert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                numbersLayout.setVisibility(LinearLayout.VISIBLE);
            }
        });
    }

    //====================Currency View====================

    // Select currency (calls "convert()")
    public void setCurrencyButton(Button currency){
        currency.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                currencyDropdown.setText(currency.getText().toString());
                currencyLayout.setVisibility(LinearLayout.INVISIBLE);
                convert();
            }
        });
    }

    //====================Numbers View====================

    // OK (calls "convert()")
    public void setOKButton(Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                numbersLayout.setVisibility(LinearLayout.INVISIBLE);
                convert();
            }
        });
    }

    // Executes conversion
    // Called in "setOKButton" and "setCurrencyButton"
    public void convert() {

        // Creates thread to avoid crashing if interrogation fails or no internet connection
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    // Check connection
                    if(isInternetConnected(currencyLayout.getContext()))
                    {
                        double number = 0;
                        double number_RON = 0;
                        String unit_choice = currencyDropdown.getText().toString().substring(2,5);

                        Document doc = Jsoup.connect("https://www.bnr.ro/curs_mobil.aspx").get();   // IOException
                        Elements tds = doc.select("td");

                        // Update default currency unit
                        for (Element td : tds)
                            for(Map.Entry<String,UnitStruct> entry: currencyStructTable.entrySet())
                                if(td.text().contains(entry.getKey()))
                                {
                                    entry.getValue().DefaultUnit = Double.parseDouble(td.nextElementSibling().text());
                                    break;
                                }

                        // Take the chosen currency and transforms it in RON
                        if (convertField.getText().toString().length() > 0)
                            number = Double.parseDouble(convertField.getText().toString());
                        number_RON = number / currencyStructTable.get(unit_choice).DefaultUnit;

                        // Set values in each currency TextView based on the value in RON
                        for(Map.Entry<String,UnitStruct> entry: currencyStructTable.entrySet())
                        {
                            String aux = formatDecimals(entry.getValue().DefaultUnit * number_RON,"#.########");
                            entry.getValue().Placeholder.setText(aux);
                        }
                    }
                    else
                        throw new Exception();
                }
                catch(IOException e) {
                    Snackbar.make(currencyLayout, "Service unavailable!", Snackbar.LENGTH_LONG).show();
                }
                catch(Exception e) {
                    Snackbar.make(currencyLayout, "You are offline!", Snackbar.LENGTH_LONG).show();
                }
            }
        }).start();
    }

    private void findViewsById() {
        // Find elements in layout
        //====================Main View====================
        numbersLayout = findViewById(R.id.numbersLinearLayout);
        currencyLayout = findViewById(R.id.currencyLinearLayout);

        currencyDropdown = findViewById(R.id.currencyDropdown);
        convertField = findViewById(R.id.convertField);

        numberRON = findViewById(R.id.numberRON);
        numberEUR = findViewById(R.id.numberEUR);
        numberUSD = findViewById(R.id.numberUSD);
        numberGBP = findViewById(R.id.numberGBP);

        //====================Currency View====================
        buttonRON = findViewById(R.id.buttonRON);
        buttonEUR = findViewById(R.id.buttonEUR);
        buttonUSD = findViewById(R.id.buttonUSD);
        buttonGBP = findViewById(R.id.buttonGBP);

        //====================Numbers View====================
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonDot = findViewById(R.id.buttonDot);
        buttonC = findViewById(R.id.buttonC);
        buttonOK = findViewById(R.id.buttonOK);
    }

    private void setFunctionality() {
        // Set functionality
        //====================Main View====================
        setOnClickDropdown(currencyDropdown);
        setOnClickConvert(convertField);

        //====================Currency View====================
        setCurrencyButton(buttonRON);
        setCurrencyButton(buttonEUR);
        setCurrencyButton(buttonUSD);
        setCurrencyButton(buttonGBP);

        //====================Numbers View====================
        setDigitButton(button0,convertField);
        setDigitButton(button1,convertField);
        setDigitButton(button2,convertField);
        setDigitButton(button3,convertField);
        setDigitButton(button4,convertField);
        setDigitButton(button5,convertField);
        setDigitButton(button6,convertField);
        setDigitButton(button7,convertField);
        setDigitButton(button8,convertField);
        setDigitButton(button9,convertField);

        setCButton(buttonC,convertField);
        setDotButton(buttonDot,convertField);
        setOKButton(buttonOK);
    }

    private void populateStructTable() {
        currencyStructTable.put(buttonRON.getText().toString().substring(2,5),new UnitStruct(1,numberRON));
        currencyStructTable.put(buttonEUR.getText().toString().substring(2,5),new UnitStruct(1,numberEUR));
        currencyStructTable.put(buttonUSD.getText().toString().substring(2,5),new UnitStruct(1,numberUSD));
        currencyStructTable.put(buttonGBP.getText().toString().substring(2,5),new UnitStruct(1,numberGBP));
    }
}
