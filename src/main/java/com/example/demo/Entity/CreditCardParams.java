package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardParams {
    private String cardHolder;//持卡人姓名
    private String cardNumber;//銀行卡號
    private String cardExpiryDate;//到期日
    private String cardCvv;//CVV安全碼
    private String cardAddress;//地址
    private String cardCity;//城市
    private String cardZipcode;//郵遞區號

    @Override
    public String toString() {
        return "CreditCardParams{" +
                "cardHolder='" + cardHolder + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardExpiryDate='" + cardExpiryDate + '\'' +
                ", cardCvv='" + cardCvv + '\'' +
                ", cardAddress='" + cardAddress + '\'' +
                ", cardCity='" + cardCity + '\'' +
                ", cardZipcode='" + cardZipcode + '\'' +
                '}';
    }
}
