/**
 * @author Suchner Dominik S19036
 */

package zad1.Utils;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Service {

    private String country;
    private LocaleAPI locale;

    public Service(String country) {
        this.country = country;
        this.locale = new LocaleAPI(country);
    }

    public String getWeather(String city) {
        ReaderAPI readerAPI = new ReaderAPI("http://api.openweathermap.org/data/2.5/weather?q=" + locale.getCountry() + "&appid=793263df38bd9791d9bb86fffdbbfbfd");
        try {
            readerAPI.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return readerAPI.getContent();
    }

    public Double getRateFor(String currency) {
        ReaderAPI readerAPI = new ReaderAPI("https://api.exchangeratesapi.io/latest?base=" + currency + "&symbols=" + locale.getCurrency());
        try {
            readerAPI.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject object = new JSONObject(readerAPI.getContent());
        JSONObject rates = (JSONObject) object.get("rates");
        return (Double) rates.get(locale.getCurrency());
    }

    public Double getNBPRate() {
        if(locale.getCurrency().equals("PLN")) return Double.valueOf(1);
        ReaderAPI readerAPI = new ReaderAPI("http://api.nbp.pl/api/exchangerates/rates/a/"+ locale.getCurrency() + "/?format=json");
        try {
            readerAPI.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject object = new JSONObject(readerAPI.getContent());
        JSONArray array = (JSONArray) object.get("rates");
        JSONObject rates = (JSONObject) array.get(0);
        System.out.println(rates.getDouble("mid"));
        return rates.getDouble("mid");
    }

    public JFXPanel getWikipediaCity(String city){
        JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(() -> {
            WebView webView = new WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().load("http://www.wikipedia.org/wiki/" + city);
        });
        return jfxPanel;
    }

    public String getCountry() {
        return country;
    }

}
