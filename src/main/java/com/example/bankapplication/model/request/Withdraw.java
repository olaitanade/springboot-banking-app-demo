package com.example.bankapplication.model.request;

public class Withdraw extends Credential{
    private Double withdrawnAmount;

    public Double getWithdrawnAmount() {
        return withdrawnAmount;
    }

    public void setWithdrawnAmount(Double withdrawnAmount) {
        this.withdrawnAmount = withdrawnAmount;
    }
}
