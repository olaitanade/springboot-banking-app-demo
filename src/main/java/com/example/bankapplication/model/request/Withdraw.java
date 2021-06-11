package com.example.bankapplication.model.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class Withdraw extends Credential{
    @NotNull(message = "withdrawnAmount required")
    @DecimalMin(value = "1.0", message = "1.0 is the minimum")
    private Double withdrawnAmount;

    public Double getWithdrawnAmount() {
        return withdrawnAmount;
    }

    public void setWithdrawnAmount(Double withdrawnAmount) {
        this.withdrawnAmount = withdrawnAmount;
    }
}
