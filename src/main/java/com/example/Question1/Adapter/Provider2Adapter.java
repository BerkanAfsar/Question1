package com.example.Question1.Adapter;

import com.example.Question1.Dao.CurrencyEntity;
import com.example.Question1.Model.Provider;
import com.example.Question1.Model.Provider2;

public class Provider2Adapter extends Provider {

    private Provider2 provider2;

    public Provider2Adapter(Provider2 provider2) {
        this.provider2 = provider2;
    }

    @Override
    public CurrencyEntity mapToEntity() {

        CurrencyEntity currencyEntity = new CurrencyEntity();

        currencyEntity.setAmount(provider2.getValue());
        currencyEntity.setTo(provider2.getTo());
        currencyEntity.setFrom(provider2.getFrom());

        return currencyEntity;
    }
}
