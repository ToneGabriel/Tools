package com.example.tools;

import static java.lang.Math.cos;
import static java.lang.Math.log;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Standard extends UtilityTools {
    // Standard Calculator class

    TextView resultField, progressField;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonDot, buttonC, buttonDEL, buttonEqual;
    Button buttonMod, buttonPlus, buttonMinus, buttonPlusMinus, buttonMulti, buttonDiv, buttonPow, buttonSin, buttonCos, buttonTan, buttonLn, buttonRad;
    Button buttonPi, buttonE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        findViewsById();
        setFunctionality();
    }

    // Button SETTERS

    // Change sign
    public void setPlusMinusButton(Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                StringBuilder sb = new StringBuilder(rf);
                if(rf.contains("-"))
                    sb.deleteCharAt(0);
                else
                    sb.insert(0,"-");
                resultField.setText(sb);
            }
        });
    }


    //================Binary Operators (and Equal button)================

    // Plus
    public void setPlusButton(Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                if(rf.length()>0) {
                    num1 = Double.parseDouble(rf);
                    operator="+";
                    progressField.setText(String.format("%s + ", formatDecimals(num1,"#.########")));
                    resultField.setText("0");
                }
            }
        });
    }

    // Minus
    public void setMinusButton(Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                if(rf.length()>0) {
                    num1 = Double.parseDouble(rf);
                    operator="-";
                    progressField.setText(String.format("%s - ", formatDecimals(num1,"#.########")));
                    resultField.setText("0");
                }
            }
        });
    }

    // Division
    public void setDivButton(Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                if(rf.length()>0) {
                    num1 = Double.parseDouble(rf);
                    operator="/";
                    progressField.setText(String.format("%s / ", formatDecimals(num1,"#.########")));
                    resultField.setText("0");
                }
            }
        });
    }

    // Multiplication
    public void setMultiButton(Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                if(rf.length()>0) {
                    num1 = Double.parseDouble(rf);
                    operator="x";
                    progressField.setText(String.format("%s x ", formatDecimals(num1,"#.########")));
                    resultField.setText("0");
                }
            }
        });
    }

    // Modulo
    public void setModButton(Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                if(rf.length()>0) {
                    num1 = Double.parseDouble(rf);
                    operator="%";
                    progressField.setText(String.format("%s mod ", formatDecimals(num1,"#.########")));
                    resultField.setText("0");
                }
            }
        });
    }

    // Power
    public void setPowButton(Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                if(rf.length()>0) {
                    num1 = Double.parseDouble(rf);
                    operator="^";
                    progressField.setText(String.format("%s ^ ", formatDecimals(num1,"#.########")));
                    resultField.setText("0");
                }
            }
        });
    }

    // Equal
    public void setEqualButton(Button b){
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!operator.isEmpty()) {
                    String rf = resultField.getText().toString();
                    num2 = Double.parseDouble(rf);
                    progressField.append(rf + " =");
                    resultField.setText(operation());
                    operator="";
                }
            }
        });
    }


    //================Functions================

    // Sine
    public void setSinButton(Button b){
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                num1 = Double.parseDouble(rf);
                progressField.setText(String.format("sin(%s)=", rf));
                resultField.setText(formatDecimals(sin(num1),"#.########"));
                operator="";
            }
        });
    }

    // Cosine
    public void setCosButton(Button b){
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                num1 = Double.parseDouble(rf);
                progressField.setText(String.format("cos(%s)=", rf));
                resultField.setText(formatDecimals(cos(num1),"#.########"));
                operator="";
            }
        });
    }

    // Tangent
    public void setTanButton(Button b){
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                num1 = Double.parseDouble(rf);
                progressField.setText(String.format("tan(%s)=", rf));
                resultField.setText(formatDecimals(tan(num1),"#.########"));
                operator="";
            }
        });
    }

    // Natural Logarithm
    public void setLnButton(Button b){
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                num1 = Double.parseDouble(rf);
                progressField.setText(String.format("ln(%s)=", rf));
                resultField.setText(formatDecimals(log(num1),"#.########"));
                operator="";
            }
        });
    }

    // Square Root
    public void setRadButton(Button b){
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String rf = resultField.getText().toString();
                num1 = Double.parseDouble(rf);
                progressField.setText(String.format("sqrt(%s)=", rf));
                resultField.setText(formatDecimals(sqrt(num1),"#.########"));
                operator="";
            }
        });
    }


    //================Delete================

    // Delete Last
    public void setDELButton(Button b){
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String rf = resultField.getText().toString();
                if(rf.length()>0){
                    StringBuilder sb= new StringBuilder(rf);
                    sb.deleteCharAt(sb.length()-1);
                    if(sb.length()==0 || sb.toString().equals("-"))
                        resultField.setText("0");
                    else
                        resultField.setText(sb);
                }
            }
        });
    }

    @Override
    protected void findViewsById() {
        // Find elements in layout
        resultField = findViewById(R.id.resultField);
        progressField = findViewById(R.id.progressField);

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

        buttonDEL = findViewById(R.id.buttonDEL);
        buttonC = findViewById(R.id.buttonC);
        buttonDot = findViewById(R.id.buttonDot);
        buttonEqual = findViewById(R.id.buttonEqual);

        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlusMinus = findViewById(R.id.buttonPlusMinus);
        buttonMulti = findViewById(R.id.buttonMulti);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonMod = findViewById(R.id.buttonMod);
        buttonPow = findViewById(R.id.buttonPow);

        buttonSin = findViewById(R.id.buttonSin);
        buttonCos = findViewById(R.id.buttonCos);
        buttonTan = findViewById(R.id.buttonTan);

        buttonLn = findViewById(R.id.buttonLn);
        buttonRad = findViewById(R.id.buttonRad);

        buttonPi = findViewById(R.id.buttonPi);
        buttonE = findViewById(R.id.buttonE);
    }

    @Override
    protected void setFunctionality() {
        // Set functionality
        //================Numbers================
        setDigitButton(button0,resultField);
        setDigitButton(button1,resultField);
        setDigitButton(button2,resultField);
        setDigitButton(button3,resultField);
        setDigitButton(button4,resultField);
        setDigitButton(button5,resultField);
        setDigitButton(button6,resultField);
        setDigitButton(button7,resultField);
        setDigitButton(button8,resultField);
        setDigitButton(button9,resultField);
        setPiButton(buttonPi,resultField);
        setEButton(buttonE,resultField);
        setDotButton(buttonDot,resultField);
        setPlusMinusButton(buttonPlusMinus);

        //================Binary Operators (and Equal button)================
        setPlusButton(buttonPlus);
        setMinusButton(buttonMinus);
        setDivButton(buttonDiv);
        setMultiButton(buttonMulti);
        setModButton(buttonMod);
        setPowButton(buttonPow);
        setEqualButton(buttonEqual);

        //================Functions================
        setSinButton(buttonSin);
        setCosButton(buttonCos);
        setTanButton(buttonTan);
        setRadButton(buttonRad);
        setLnButton(buttonLn);

        //================Delete================
        setCButton(buttonC,resultField,progressField);
        setDELButton(buttonDEL);
    }

}