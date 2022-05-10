package com.example.myatm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String selectedAmt;
    private Button btnWithdraw, btnDeposit;
    private TextView acctAmt, moneyAmt;
    private Spinner spr;
    private final Withdraw withdraw = new Withdraw();
    private final Deposit deposit = new Deposit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWithdraw = findViewById(R.id.withdraw);
        btnDeposit = findViewById(R.id.deposit);
        acctAmt = findViewById(R.id.accountAmt);
        moneyAmt = findViewById(R.id.moneyInHandAmt);
        spr = findViewById(R.id.spinner);

        initSpinner();
        initDeposit();
        initWithdraw();
    }

    private void initSpinner(){
        // Creates arrayAdapter from moneyArray and sets it to spinner
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource
                (this,R.array.moneyArray, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spr.setAdapter(arrayAdapter);

        // Listens for item selections and Handles spinner selection asynchronously
        spr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedAmt = adapterView.getItemAtPosition(i).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void initWithdraw() {
        // Listens for withdraw button clicks and handles them asynchronously
        btnWithdraw.setOnClickListener(view -> {

            if (withdraw.validator(acctAmt.getText().toString(), selectedAmt))
                return;
            acctAmt.setText(withdraw.reduce(acctAmt.getText().toString(), selectedAmt));
            moneyAmt.setText(deposit.add(moneyAmt.getText().toString(), selectedAmt));
        });
    }

    private void initDeposit(){
        // Listens for deposit button clicks and handles them asynchronously
        btnDeposit.setOnClickListener(view -> {
            if (withdraw.validator(moneyAmt.getText().toString(), selectedAmt))
                return;
            moneyAmt.setText(withdraw.reduce(moneyAmt.getText().toString(), selectedAmt));
            acctAmt.setText(deposit.add(acctAmt.getText().toString(), selectedAmt));
        });
    }
}
