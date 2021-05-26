package com.example.demo.Entity;

import com.sun.istack.NotNull;
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
public class CreditCard {
    @Id
    @GeneratedValue
    private Long id;


    private String cardHolder;//持卡人姓名
    private String cardNumber;//銀行卡號
    private Date cardExpiryDate;//到期日
    private String cardCvv;//CVV安全碼
    private String cardAddress;//地址
    private String cardCity;//城市
    private String cardZipcode;//郵遞區號

//    @OneToOne
//    private Order order;

    private CreditCard(Builder builder){
        this.cardHolder = builder.cardHolder;
        this.cardNumber = builder.cardNumber;
        this.cardExpiryDate = builder.cardExpiryDate;
        this.cardCvv = builder.cardCvv;
        this.cardAddress = builder.cardAddress;
        this.cardCity = builder.cardCity;
    }
    public static class Builder {

        private String cardHolder;//持卡人姓名
        private String cardNumber;//銀行卡號
        private Date cardExpiryDate;//到期日
        private String cardCvv;//CVV安全碼
        private String cardAddress;//地址
        private String cardCity;//城市

        Builder(){}
        public Builder setCardHolder(String cardHolder) {
            this.cardHolder = cardHolder;
            return this;
        }

        public Builder setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder setCardExpiryDate(Date cardExpiryDate) {
            this.cardExpiryDate = cardExpiryDate;
            return this;
        }

        public Builder setCardCvv(String cardCvv) {
            this.cardCvv = cardCvv;
            return this;
        }

        public Builder setCardAddress(String cardAddress) {
            this.cardAddress = cardAddress;
            return this;
        }

        public Builder setCardCity(String cardCity) {
            this.cardCity = cardCity;
            return this;
        }


        public CreditCard build() {
            return new CreditCard(this);
        }


    }
}
