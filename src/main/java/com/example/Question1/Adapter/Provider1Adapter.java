package com.example.Question1.Adapter;

import com.example.Question1.Dao.CurrencyEntity;
import com.example.Question1.Model.Provider;
import com.example.Question1.Model.Provider1;

public class Provider1Adapter extends Provider {

    private Provider1 provider1;

    public Provider1Adapter(Provider1 provider1) {
        this.provider1 = provider1;
    }

    @Override
    public CurrencyEntity mapToEntity() {

        CurrencyEntity currencyEntity = new CurrencyEntity();

        currencyEntity.setAmount(provider1.getAmount());

        String from = provider1.getSymbol().substring(0,3);
        currencyEntity.setFrom(from);

        String to = provider1.getSymbol().substring(3,6);
        currencyEntity.setTo(to);

        return currencyEntity;
    }
}
