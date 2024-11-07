package com.example.work4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonadd, buttonsubtract, buttondivide, buttonmultiply, buttonclean, buttondegree;
    private TextView result, equal, operation;
    private EditText number1, number2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonadd = (Button) findViewById(R.id.buttonadd);
        buttonsubtract = (Button) findViewById(R.id.buttonsubtract);
        buttondivide = (Button) findViewById(R.id.buttondivide);
        buttonmultiply = (Button) findViewById(R.id.buttonmultiply);
        buttonclean = (Button) findViewById(R.id.buttonclean);
        buttondegree = (Button) findViewById(R.id.buttondegree);
        result = (TextView) findViewById(R.id.result);
        equal = (TextView) findViewById(R.id.equal);
        operation = (TextView) findViewById(R.id.operation);
        number1 = (EditText) findViewById(R.id.number1);
        number2 = (EditText) findViewById(R.id.number2);

        buttonadd.setOnClickListener(this);
        buttonsubtract.setOnClickListener(this);
        buttondivide.setOnClickListener(this);
        buttonmultiply.setOnClickListener(this);
        buttonclean.setOnClickListener(this);
        buttondegree.setOnClickListener(this);
        result.setOnClickListener(this);
        equal.setOnClickListener(this);
        operation.setOnClickListener(this);
        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float num1;
        float num2;
        float res = 0;

        // Проверка, пустые ли поля ввода
        if (number1.getText().toString().isEmpty() || number2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Введите оба числа", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            num1 = Float.parseFloat(number1.getText().toString());
            num2 = Float.parseFloat(number2.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Введите корректные числа", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = v.getId();
        if (id == R.id.buttonadd) {
            operation.setText("+");
            res = num1 + num2;
        } else if (id == R.id.buttonsubtract) {
            operation.setText("-");
            res = num1 - num2;
        } else if (id == R.id.buttondivide) {
            operation.setText("/");
            res = num1 / num2;
        } else if (id == R.id.buttonmultiply) {
            operation.setText("*");
            res = num1 * num2;
        } else if (id == R.id.buttondegree) {
            operation.setText("^");
            res = (float) Math.pow(num1, num2);
        } else if (id == R.id.buttonclean) {
            number1.setText("");
            operation.setText("");
            number2.setText("");
            result.setText("");
        }
        result.setText(res + "");
    }
}