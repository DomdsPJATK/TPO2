package zad1.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class LocaleAPI {

    private String country;

    public LocaleAPI(String country) {
        this.country = country;
    }

    public String getCurrency(){
        ReaderAPI readerAPI = new ReaderAPI("https://restcountries.eu/rest/v2/name/" + this.country + "?fields=currencies");
        try {
            readerAPI.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONArray object = new JSONArray(readerAPI.getContent());
        JSONObject currencies = (JSONObject) object.get(0);
        JSONArray currenciesArray = currencies.getJSONArray("currencies");
        JSONObject currenciesObj = (JSONObject) currenciesArray.get(0);
        return currenciesObj.getString("code");
    }

    public String getCountry() {
        return country;
    }

}
