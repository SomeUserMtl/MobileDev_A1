package com.example.myatm;

public class Deposit {
    public String add(String amt, String selectedAmt){
        return String.valueOf(Integer.parseInt(amt) + Integer.parseInt(selectedAmt));
    }
}
