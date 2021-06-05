package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderParams {
    private String oilType;
    private String startMonth;
    private String endMonth;
    private float oilVolume;
    private float handleFee;
    private float oilCost;
    private String owner; //Customer.Username
    private CreditCardParams creditCardParams;

    @Override
    public String toString() {
        return "OrderParams{" +
                "oilType='" + oilType + '\'' +
                ", startMonth='" + startMonth + '\'' +
                ", endMonth='" + endMonth + '\'' +
                ", oilVolume=" + oilVolume +
                ", handleFee=" + handleFee +
                ", oilCost=" + oilCost +
                ", owner='" + owner + '\'' +
                ", creditCardParams=" + creditCardParams +
                '}';
    }
}
