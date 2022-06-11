package com.example.tools;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Hashtable;
import java.util.Map;

public class Length extends UtilityTools{
    // Length Converter class

    LinearLayout numbersLayout, unitsLayout;
    TextView unitsDropdown, convertField;

    Button buttonMeter, buttonKilometer, buttonCentimeter, buttonMillimeter, buttonMile, buttonNMile, buttonYard, buttonFoot, buttonInch;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonDot, buttonC, buttonOK;

    TextView numberMeter, numberKilometer, numberCentimeter, numberMillimeter, numberMile, numberNMile, numberYard, numberFoot, numberInch;
    Hashtable<String, UnitStruct> lengthStructTable = new Hashtable<String, UnitStruct>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length);

        findViewsById();
        populateStructTable();
        setFunctionality();
    }

    // SETTERS and other functions

    //====================Main View====================

    // Shows units list view
    public void setOnClickDropdown(TextView dropdown){
        dropdown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                unitsLayout.setVisibility(LinearLayout.VISIBLE);
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

    //====================Units View====================

    // Select unit (calls convert)
    public void setUnitButton(Button unit){
        unit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                unitsDropdown.setText(unit.getText().toString());
                unitsLayout.setVisibility(LinearLayout.INVISIBLE);
                convert();
            }
        });
    }

    //====================Numbers View====================

    // OK (calls convert)
    public void setOKButton(Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numbersLayout.setVisibility(LinearLayout.INVISIBLE);
                convert();
            }
        });
    }

    // Called in "setOKButton" and "setUnitButton"
    // Executes conversion
    public void convert() {

        double number = 0;
        double number_meter = 0;
        String unit_choice = unitsDropdown.getText().toString();

        // Take the chosen unit and transforms it in meters
        if(convertField.getText().toString().length()>0)
            number = Double.parseDouble(convertField.getText().toString());
        number_meter = number / lengthStructTable.get(unit_choice).DefaultUnit;

        // Set values in each unit TextView based on the value in meters
        for(Map.Entry<String,UnitStruct> entry: lengthStructTable.entrySet())
        {
            String aux = formatDecimals(entry.getValue().DefaultUnit * number_meter,"#.########");
            entry.getValue().Placeholder.setText(aux);
        }
    }

    @Override
    protected void findViewsById(){
        // Find elements in layout
        //====================Main View====================
        numbersLayout = findViewById(R.id.numbersLinearLayout);
        unitsLayout = findViewById(R.id.unitsLinearLayout);

        unitsDropdown = findViewById(R.id.unitDropdown);
        convertField = findViewById(R.id.convertField);

        numberMeter = findViewById(R.id.numberMeter);
        numberKilometer = findViewById(R.id.numberKilometer);
        numberCentimeter = findViewById(R.id.numberCentimeter);
        numberMillimeter = findViewById(R.id.numberMillimeter);
        numberMile = findViewById(R.id.numberMile);
        numberNMile = findViewById(R.id.numberNMile);
        numberYard = findViewById(R.id.numberYard);
        numberFoot = findViewById(R.id.numberFoot);
        numberInch = findViewById(R.id.numberInch);

        //====================Units View====================
        buttonMeter = findViewById(R.id.buttonMeter);
        buttonKilometer = findViewById(R.id.buttonKiloMeter);
        buttonCentimeter = findViewById(R.id.buttonCentiMeter);
        buttonMillimeter = findViewById(R.id.buttonMiliMeter);
        buttonMile = findViewById(R.id.buttonMile);
        buttonNMile = findViewById(R.id.buttonNMile);
        buttonYard = findViewById(R.id.buttonYard);
        buttonFoot = findViewById(R.id.buttonFoot);
        buttonInch = findViewById(R.id.buttonInch);

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

    @Override
    protected void setFunctionality(){
        // Set functionality
        //====================Main View====================
        setOnClickDropdown(unitsDropdown);
        setOnClickConvert(convertField);

        //====================Units View=====================
        setUnitButton(buttonMeter);
        setUnitButton(buttonKilometer);
        setUnitButton(buttonCentimeter);
        setUnitButton(buttonMillimeter);
        setUnitButton(buttonMile);
        setUnitButton(buttonNMile);
        setUnitButton(buttonYard);
        setUnitButton(buttonFoot);
        setUnitButton(buttonInch);

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

    private void populateStructTable(){
        lengthStructTable.put(buttonMeter.getText().toString(),new UnitStruct(1,numberMeter));
        lengthStructTable.put(buttonKilometer.getText().toString(),new UnitStruct(0.001,numberKilometer));
        lengthStructTable.put(buttonCentimeter.getText().toString(),new UnitStruct(100,numberCentimeter));
        lengthStructTable.put(buttonMillimeter.getText().toString(),new UnitStruct(1000,numberMillimeter));
        lengthStructTable.put(buttonMile.getText().toString(),new UnitStruct(0.000621,numberMile));
        lengthStructTable.put(buttonNMile.getText().toString(),new UnitStruct(0.00054,numberNMile));
        lengthStructTable.put(buttonYard.getText().toString(),new UnitStruct(1.09361,numberYard));
        lengthStructTable.put(buttonFoot.getText().toString(),new UnitStruct(3.28084,numberFoot));
        lengthStructTable.put(buttonInch.getText().toString(),new UnitStruct(39.3701,numberInch));
    }

}
