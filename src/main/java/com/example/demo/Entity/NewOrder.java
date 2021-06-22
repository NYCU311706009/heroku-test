package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewOrder {
    @Id
    @GeneratedValue
    private Long id;

    private String oilType;
    private String startMonth;
    private String endMonth;
    private float oilVolume;
    private float handleFee;
    private float oilCost;
    private String owner; //Customer.Username
    private String cardNumber;
    private String timestamp;
//    @OneToOne
//    private CreditCard creditCard;
//
//    @ManyToOne
//    private Customer customer;

    public Double getCpcPrice() {
        double cpcPrice = 0;
        switch (oilType){
            case "92無鉛":
                cpcPrice = 26.6;
                break;
            case "95無鉛":
                cpcPrice = 28.1;
                break;
            case "98無鉛":
                cpcPrice = 30.1;
                break;
            case "超級柴油":
                cpcPrice = 23.8;
                break;
        }
        return cpcPrice;
    }

    private NewOrder(Builder builder){
        this.oilType = builder.oilType;
        this.startMonth = builder.startMonth;
        this.endMonth = builder.endMonth;
        this.oilVolume = builder.oilVolume;
        this.handleFee = builder.handleFee;
        this.oilCost = builder.oilCost;
        this.owner = builder.owner;
        this.cardNumber = builder.cardNumber;
        this.timestamp = builder.timestamp;
    }
    public static class Builder{
        private String timestamp;
        private String oilType;
        private String startMonth;
        private String endMonth;
        private float oilVolume;
        private float handleFee;
        private float oilCost;
        private String owner;
        private String cardNumber;

        public Builder setTimestamp(String timestamp){
            this.timestamp = timestamp;
            return this;
        }
        public Builder setCardNumber(String cardNumber){
            this.cardNumber = cardNumber;
            return this;
        }
        public Builder setOwner(String owner){
            this.owner = owner;
            return this;
        }

        public Builder setOilType(String oilType) {
            this.oilType = oilType;
            return this;
        }

        public Builder setStartMonth(String startMonth) {
            this.startMonth = startMonth;
            return this;
        }

        public Builder setEndMonth(String endMonth) {
            this.endMonth = endMonth;
            return this;
        }

        public Builder setOilVolume(float oilVolume) {
            this.oilVolume = oilVolume;
            return this;
        }

        public Builder setHandleFee(float handleFee) {
            this.handleFee = handleFee;
            return this;
        }

        public Builder setOilCost(float oilCost) {
            this.oilCost = oilCost;
            return this;
        }

        public Builder(){}
        public NewOrder build() {
            return new NewOrder(this);
        }
    }


}
