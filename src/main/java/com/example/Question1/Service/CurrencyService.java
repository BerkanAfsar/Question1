package com.example.Question1.Service;

import com.example.Question1.Model.CurrencySrvRes;
import org.json.JSONException;

import java.io.IOException;

public interface CurrencyService {

    CurrencySrvRes getCheapestCurrency() throws IOException, JSONException;
}
