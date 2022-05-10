package com.example.myatm;

public class Withdraw {
    public String reduce(String amt, String selectedAmt){
        return String.valueOf(Integer.parseInt(amt) - Integer.parseInt(selectedAmt));
    }

    public boolean validator(String amt, String selectedAmt){
        return Integer.parseInt(amt) < Integer.parseInt(selectedAmt);
    }
}
