package com.example.trab1_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final List<String> OPERATORS = Arrays.asList("+", "-");
    private int op1;
    private int op2;
    private String operator;
    private TextView expression;
    private TextView label;
    private EditText input;
    private Button btn;

    private int round = 1;
    private int pontuation = 0;

    public String getExpression() {
        op1 = getRandomNumber(0, 9);
        op2 = getRandomNumber(0, 9);

        int operationIdx = getRandomNumber(0, 1);

        operator = OPERATORS.get(operationIdx);

        return String.format("%d %s %d", op1, operator, op2);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void checkResult() {
        Integer answer;

        if (operator.equals("+"))
            answer = op1 + op2;
        else
            answer = op1 - op2;

        Integer userInput = Integer.valueOf(input.getText().toString());

        if (answer.equals(userInput)) {
            pontuation += 20;
        }

        round++;
        label.setText(String.format("Round %d -- pontuação: %d", round, pontuation));

        if (round <= 5) {
            expression.setText(getExpression());
            input.setText("0");
        }
        else {
            btn.setEnabled(false);
            showAlert();
        }

    }

    public void showAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Pontuação final");
        alertDialog.setMessage(pontuation + "");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> checkResult());

        expression = findViewById(R.id.expression);
        expression.setText(getExpression());
        input = findViewById(R.id.input);

        label = findViewById(R.id.label);

        label.setText(String.format("Round %d -- pontuação: %d", round, pontuation));
    }

}