package com.example.demo.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String oilType;
    private Date startMonth;
    private Date endMonth;
    private float oilVolume;
    private float handleFee;
    private float oilCost;
    @OneToOne
    private CreditCard creditCard;
    @ManyToOne
    private Customer customer;



    private Order(Builder builder){
        this.oilType = builder.oilType;
        this.startMonth = builder.startMonth;
        this.endMonth = builder.endMonth;
        this.oilVolume = builder.oilVolume;
        this.handleFee = builder.handleFee;
        this.oilCost = builder.oilCost;

    }
    private static class Builder{

        private String oilType;
        private Date startMonth;
        private Date endMonth;
        private float oilVolume;
        private float handleFee;
        private float oilCost;

        public Builder setOilType(String oilType) {
            this.oilType = oilType;
            return this;
        }

        public Builder setStartMonth(Date startMonth) {
            this.startMonth = startMonth;
            return this;
        }

        public Builder setEndMonth(Date endMonth) {
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
        public Order build() {
            return new Order(this);
        }
    }


}
