package com.example.Question1.Model;

import com.example.Question1.Model.CurrencyDTO;

import java.util.List;

public class CurrencyApiRes {

    private List<CurrencyDTO> currencyDTOList;

    public List<CurrencyDTO> getCurrencyDTOList() {
        return currencyDTOList;
    }

    public void setCurrencyDTOList(List<CurrencyDTO> currencyDTOList) {
        this.currencyDTOList = currencyDTOList;
    }
}
