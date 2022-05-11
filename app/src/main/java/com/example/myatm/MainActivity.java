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

        // Initializes view objects once globally so they don't have to be found for each click
        btnWithdraw = findViewById(R.id.withdraw);
        btnDeposit = findViewById(R.id.deposit);
        acctAmt = findViewById(R.id.accountAmt);
        moneyAmt = findViewById(R.id.moneyInHandAmt);
        spr = findViewById(R.id.spinner);

        // Launches helper methods
        initSpinner();
        initWithdraw();
        initDeposit();
    }

    // Creates ArrayAdapter and Listens for spinner selections asynchronously
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

    // Listens for withdraw button clicks and handles them asynchronously
    private void initWithdraw() {
        btnWithdraw.setOnClickListener(view -> {
            // Uses Withdraw class validator to ensure acctAmt doesn't go into negative
            if (withdraw.validator(acctAmt.getText().toString(), selectedAmt))
                return;
            // Updates values of acctAmt & moneyAmt using withdraw & deposit class' methods
            acctAmt.setText(withdraw.reduce(acctAmt.getText().toString(), selectedAmt));
            moneyAmt.setText(deposit.add(moneyAmt.getText().toString(), selectedAmt));
        });
    }

    // Listens for deposit button clicks and handles them asynchronously
    private void initDeposit(){
        btnDeposit.setOnClickListener(view -> {
            // Uses Withdraw class validator to ensure moneyAmt doesn't go into negative
            if (withdraw.validator(moneyAmt.getText().toString(), selectedAmt))
                return;
            // Updates values of acctAmt & moneyAmt using withdraw & deposit class' methods
            moneyAmt.setText(withdraw.reduce(moneyAmt.getText().toString(), selectedAmt));
            acctAmt.setText(deposit.add(acctAmt.getText().toString(), selectedAmt));
        });
    }
}
