package zad1.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReaderAPI implements Readable{

    private URL url;
    private String content;

    public ReaderAPI(URL url) {
        this.url = url;
    }

    public ReaderAPI(String urlString){
        try {
            this.url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String getContent(){
        if(content == null) return "";
        return this.content;
    }

    @Override
    public void read() throws IOException {
        if(url == null) return;
        String cont = "";
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream(), "UTF-8"))) {
            String line;
            while((line = in.readLine()) != null) cont +=line + "\n";
        }
        this.content = cont;
    }

}
