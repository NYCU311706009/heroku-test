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

//    @OneToOne
//    private CreditCard creditCard;
//
//    @ManyToOne
//    private Customer customer;



    private NewOrder(Builder builder){
        this.oilType = builder.oilType;
        this.startMonth = builder.startMonth;
        this.endMonth = builder.endMonth;
        this.oilVolume = builder.oilVolume;
        this.handleFee = builder.handleFee;
        this.oilCost = builder.oilCost;
        this.owner = builder.owner;
    }
    private static class Builder{
        private String oilType;
        private String startMonth;
        private String endMonth;
        private float oilVolume;
        private float handleFee;
        private float oilCost;
        private String owner;
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

        Builder(){}
        public NewOrder build() {
            return new NewOrder(this);
        }
    }


}
