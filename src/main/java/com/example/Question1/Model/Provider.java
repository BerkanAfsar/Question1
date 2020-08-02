package com.example.Question1.Model;

import com.example.Question1.Dao.CurrencyEntity;

public class Provider {

    private String from;
    private String to;
    private Double amount;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CurrencyEntity mapToEntity() {

        CurrencyEntity currencyEntity = new CurrencyEntity();

        currencyEntity.setAmount(amount);
        currencyEntity.setFrom(from);
        currencyEntity.setTo(to);

        return currencyEntity;
    }
}
