package com.example.jeffyang.badmintoncornercaller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button start;
    Spinner numberOfCallSpinner, callIntervalSpinner;
    CornerCaller cornerCaller;
    CheckBox corner1, corner2, corner3, corner4, corner5, corner6;
    Context context = this; // this will be used by the toast if the user did not select any corner


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cornerCaller = new CornerCaller();

        numberOfCallSpinner = (Spinner) findViewById(R.id.numberOfCallSpinner);
        numberOfCallSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                int numCall = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                cornerCaller.setNumberOfCalls(numCall);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        callIntervalSpinner = (Spinner) findViewById(R.id.callIntervalSpinner);
        callIntervalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);  // this line of code centers the text inside the spinner
                double callInterval = Double.parseDouble(adapterView.getItemAtPosition(i).toString());
                cornerCaller.setCallInterval(callInterval);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        corner1 = (CheckBox) findViewById(R.id.corner1);
        corner2 = (CheckBox) findViewById(R.id.corner2);
        corner3 = (CheckBox) findViewById(R.id.corner3);
        corner4 = (CheckBox) findViewById(R.id.corner4);
        corner5 = (CheckBox) findViewById(R.id.corner5);
        corner6 = (CheckBox) findViewById(R.id.corner6);

        start = (Button) findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean cornerSelectedFlag = false;

                if (corner1.isChecked()) {
                    cornerCaller.selectCorner1();
                    cornerSelectedFlag = true;
                } else {
                    cornerCaller.deselectCorner1();
                }

                if (corner2.isChecked()) {
                    cornerCaller.selectCorner2();
                    cornerSelectedFlag = true;
                } else {
                    cornerCaller.deselectCorner2();
                }

                if (corner3.isChecked()) {
                    cornerCaller.selectCorner3();
                    cornerSelectedFlag = true;
                } else {
                    cornerCaller.deselectCorner3();
                }

                if (corner4.isChecked()) {
                    cornerCaller.selectCorner4();
                    cornerSelectedFlag = true;
                } else {
                    cornerCaller.deselectCorner4();
                }

                if (corner5.isChecked()) {
                    cornerCaller.selectCorner5();
                    cornerSelectedFlag = true;
                } else {
                    cornerCaller.deselectCorner5();
                }

                if (corner6.isChecked()) {
                    cornerCaller.selectCorner6();
                    cornerSelectedFlag = true;
                }else {
                    cornerCaller.deselectCorner6();
                }

                if (cornerSelectedFlag == true){
                Intent in = new Intent(MainActivity.this, Main2Activity.class);
                in.putExtra("Caller_object", cornerCaller);
                startActivity(in);
                } else {
                    Toast.makeText(context, "Please select one corner", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
