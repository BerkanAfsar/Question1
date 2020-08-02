package com.example.Question1.Controller;

import com.example.Question1.Model.CurrencyApiRes;
import com.example.Question1.Service.CurrencyService;
import com.example.Question1.Model.CurrencySrvRes;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path="/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    public CurrencyApiRes getCheapestCurrency() throws IOException, JSONException {

        CurrencySrvRes srvResponse = currencyService.getCheapestCurrency();

        CurrencyApiRes apiResponse = new CurrencyApiRes();

        if(srvResponse.getCurrencyDTOList() != null && srvResponse.getCurrencyDTOList().size() > 0)
            apiResponse.setCurrencyDTOList(srvResponse.getCurrencyDTOList());

        return apiResponse;
    }
}
