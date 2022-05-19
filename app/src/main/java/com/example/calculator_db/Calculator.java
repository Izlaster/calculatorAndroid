package com.example.calculator_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.mariuszgromada.math.mxparser.*;

public class Calculator extends AppCompatActivity {

    public String process = "";
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        reference = FirebaseDatabase.getInstance().getReference().child("User/" + FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    private void updateText(int btnId, String strId) {
        final TextView changingText = (TextView) findViewById(R.id.textRezView);
        process = process + strId;
        changingText.setText(process);
    }

    public void zeroClick(View view) {
        Button changeText = (Button) findViewById(R.id.zero);
        int id = changeText.getId();
        updateText(id, "0");
    }

    public void oneClick(View view) {
        Button changeText = (Button) findViewById(R.id.one);
        int id = changeText.getId();
        updateText(id, "1");
    }

    public void twoClick(View view) {
        Button changeText = (Button) findViewById(R.id.two);
        int id = changeText.getId();
        updateText(id, "2");
    }

    public void threeClick(View view) {
        Button changeText = (Button) findViewById(R.id.three);
        int id = changeText.getId();
        updateText(id, "3");
    }

    public void fourClick(View view) {
        Button changeText = (Button) findViewById(R.id.four);
        int id = changeText.getId();
        updateText(id, "4");
    }

    public void fiveClick(View view) {
        Button changeText = (Button) findViewById(R.id.five);
        int id = changeText.getId();
        updateText(id, "5");
    }

    public void sixClick(View view) {
        Button changeText = (Button) findViewById(R.id.six);
        int id = changeText.getId();
        updateText(id, "6");
    }

    public void sevenClick(View view) {
        Button changeText = (Button) findViewById(R.id.seven);
        int id = changeText.getId();
        updateText(id, "7");
    }

    public void eightClick(View view) {
        Button changeText = (Button) findViewById(R.id.eight);
        int id = changeText.getId();
        updateText(id, "8");
    }


    public void nineClick(View view) {
        Button changeText = (Button) findViewById(R.id.nine);
        int id = changeText.getId();
        updateText(id, "9");
    }

    public void dotClick(View view) {
        if (process != "") {
            Button changeText = (Button) findViewById(R.id.dot);
            int id = changeText.getId();
            updateText(id, ".");
        }
    }

    public void clearClick(View view) {
        final TextView changingText = (TextView) findViewById(R.id.textRezView);
        process = "0";
        changingText.setText(process);
        process = "";
    }

    public void plusClick(View view) {
        if (process != "" && process.indexOf("+", process.length() - 1) == -1 && process.indexOf("-", process.length() - 1) == -1 &&
                process.indexOf("*", process.length() - 1) == -1 && process.indexOf("/", process.length() - 1) == -1 && process.indexOf("^", process.length() - 1) == -1) {
            Button changeText = (Button) findViewById(R.id.plus);
            int id = changeText.getId();
            updateText(id, "+");
        }
    }

    public void minusClick(View view) {
        if (process.indexOf("+", process.length() - 1) == -1 && process.indexOf("-", process.length() - 1) == -1 &&
                process.indexOf("*", process.length() - 1) == -1 && process.indexOf("/", process.length() - 1) == -1 && process.indexOf("^", process.length() - 1) == -1) {
            Button changeText = (Button) findViewById(R.id.minus);
            int id = changeText.getId();
            updateText(id, "-");
        }
    }

    public void multiplyClick(View view) {
        if (process != "" && process.indexOf("+", process.length() - 1) == -1 && process.indexOf("-", process.length() - 1) == -1 &&
                process.indexOf("*", process.length() - 1) == -1 && process.indexOf("/", process.length() - 1) == -1 && process.indexOf("^", process.length() - 1) == -1) {
            Button changeText = (Button) findViewById(R.id.multiply);
            int id = changeText.getId();
            updateText(id, "*");
        }
    }

    public void divideClick(View view) {
        if (process != "" && process.indexOf("+", process.length() - 1) == -1 && process.indexOf("-", process.length() - 1) == -1 &&
                process.indexOf("*", process.length() - 1) == -1 && process.indexOf("/", process.length() - 1) == -1 && process.indexOf("^", process.length() - 1) == -1) {
            Button changeText = (Button) findViewById(R.id.divide);
            int id = changeText.getId();
            updateText(id, "/");
        }
    }

    public void powClick(View view) {
        if (process != "" && process.indexOf("+", process.length() - 1) == -1 && process.indexOf("-", process.length() - 1) == -1 &&
                process.indexOf("*", process.length() - 1) == -1 && process.indexOf("/", process.length() - 1) == -1 && process.indexOf("^", process.length() - 1) == -1) {
            Button changeText = (Button) findViewById(R.id.divide);
            int id = changeText.getId();
            updateText(id, "^");
        }
    }

    public void resultClick(View view) {
        final TextView transferText = (TextView) findViewById(R.id.textWorkView);
        final TextView changingText = (TextView) findViewById(R.id.textRezView);
        transferText.setText(process);
        process = process.replaceAll("÷", "/");
        process = process.replaceAll("×", "*");
        Expression exp = new Expression(process);
        String result = String.valueOf(exp.calculate());
        changingText.setText(result);
        DAOUser dao = new DAOUser(FirebaseAuth.getInstance().getCurrentUser());
        dao.addPart(uploadHistory(process, result));
        changingText.setText("0");
        process = "";

    }

    public void storyClick(View view) {
        startActivity(new Intent(Calculator.this, History.class));
    }

    public Part uploadHistory(String process, String result) {
        Part part = new Part(process, result);
        return part;
    }
}