import java.io.FileWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Alexa{
    public static void main(String[] args)  {

        try {
            URL url = new URL("https://api.thingspeak.com/channels/1821080/feeds.json?api_key=JAEE8253EP7B45QB&results=2");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int rc = con.getResponseCode();

            if (rc != 200) {
                throw new RuntimeException("Response Code : " + rc);
            } else {
                while (true) {
                    System.out.println(rc);
                    String input = "";
                    Scanner sc = new Scanner(url.openStream());
                    while (sc.hasNext()) {
                        input += sc.nextLine();
                    }
                    sc.close();
                    JSONParser par = new JSONParser();
                    JSONObject jsonObject=(JSONObject)par.parse(input);
                    JSONArray jsonArray=(JSONArray)jsonObject.get("feeds");
                    JSONObject resObj=(JSONObject)jsonArray.get(0);
                    String result=(String)resObj.get("field1");
                    System.out.println(result);

                }
            }
        }
        catch (Exception e){}
    }
}