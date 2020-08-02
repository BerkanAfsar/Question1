package com.example.Question1.Service.impl;

import com.example.Question1.Adapter.Provider1Adapter;
import com.example.Question1.Adapter.Provider2Adapter;
import com.example.Question1.Dao.CurrencyEntity;
import com.example.Question1.Dao.CurrencyRepository;
import com.example.Question1.Model.CurrencySrvRes;
import com.example.Question1.Enum.CurrencyCode;
import com.example.Question1.Service.CurrencyService;
import com.example.Question1.Utility.JsonToString;
import com.example.Question1.Model.CurrencyDTO;
import com.example.Question1.Model.Provider1;
import com.example.Question1.Model.Provider2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public CurrencySrvRes getCheapestCurrency() throws IOException, JSONException {

        /*I designed this way to read data from Url and find and show the cheapest amount of currency and save to db.*/

        JSONArray jsonArray1 = getJsonArrayByUrl("https://run.mocky.io/v3/e4c58892-3eaa-49e8-a2d4-88ffb0f97c27");

        List<CurrencyEntity> currencyEntityList = new ArrayList<>();

        getCurrencyList(jsonArray1, currencyEntityList);

        JSONArray jsonArray2 = getJsonArrayByUrl("https://run.mocky.io/v3/cff2fa19-a599-46c7-a83c-c891ba721561");

        getCurrencyList(jsonArray2, currencyEntityList);

        List<CurrencyDTO> currencyDTOList = getCheapestCurrencyList(currencyEntityList);

        CurrencySrvRes response = new CurrencySrvRes();

        response.setCurrencyDTOList(currencyDTOList);

        return response;
    }

    private JSONArray getJsonArrayByUrl(String url) throws IOException, JSONException {

        JsonToString jsonToString = new JsonToString();

        JSONObject jsonObject = jsonToString.readJsonFromUrl(url);

        return (JSONArray) jsonObject.get("result");
    }

    private void getCurrencyList(JSONArray jsonArray, List<CurrencyEntity> currencyEntityList) throws JSONException {

        JSONObject jsonObject = (JSONObject)jsonArray.get(0);

        if(jsonObject.has("symbol")) {

            Provider1 provider1 = new Provider1();

            for(int counter = 0; counter < jsonArray.length(); counter++)
            {
                JSONObject jsonObj = (JSONObject)jsonArray.get(counter);

                provider1.setAmount((Double)jsonObj.get("amount"));
                provider1.setSymbol((String)jsonObj.get("symbol"));

                Provider1Adapter provider1Adapter = new Provider1Adapter(provider1);

                CurrencyEntity currencyEntity = provider1Adapter.mapToEntity();

                currencyEntityList.add(currencyEntity);
            }
        }
        else {
            Provider2 provider2 = new Provider2();

            for(int counter = 0; counter < jsonArray.length(); counter++)
            {
                JSONObject jsonObj = (JSONObject)jsonArray.get(counter);

                provider2.setValue((Double)jsonObj.get("amount"));
                provider2.setTo((String)jsonObj.get("to"));
                provider2.setFrom((String)jsonObj.get("from"));

                Provider2Adapter provider2Adapter = new Provider2Adapter(provider2);

                CurrencyEntity currencyEntity = provider2Adapter.mapToEntity();

                currencyEntityList.add(currencyEntity);
            }
        }
    }

    private List<CurrencyDTO> getCheapestCurrencyList(List<CurrencyEntity> currencyEntityList) {

        List<String> enumNames = Stream.of(CurrencyCode.values())
                .map(CurrencyCode::name)
                .collect(Collectors.toList());

        List<CurrencyDTO> currencyDTOList= new ArrayList<>();

        for (String enumName : enumNames) {

            Optional<CurrencyEntity> minAmountWithCurrency = currencyEntityList.stream().filter(currencyEntity ->
                    currencyEntity.getFrom().equals(enumName)).collect(Collectors.toList()).stream().min(Comparator.comparing(CurrencyEntity::getAmount));

            if(minAmountWithCurrency.isPresent()) {

                // currencyRepository.save(minAmountWithCurrency);

                /*I couldn't fully understand the task. So I saved records to db in here*/

                CurrencyDTO currencyDTO = new CurrencyDTO();

                currencyDTO.setAmount(minAmountWithCurrency.get().getAmount());
                currencyDTO.setFrom(minAmountWithCurrency.get().getFrom());
                currencyDTO.setTo(minAmountWithCurrency.get().getTo());

                currencyDTOList.add(currencyDTO);
            }
        }

        return currencyDTOList;
    }
}
