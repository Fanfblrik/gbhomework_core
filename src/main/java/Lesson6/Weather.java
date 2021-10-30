package Lesson6;


import com.github.tsohr.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Weather {

    public static void main(String[] args)
    {
        URL url = null;
        try {
            url = new URL("api.openweathermap.org/data/2.5/weather?q=London");
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            JSONObject myJSON=new JSONObject();
            JSONObject auth=new JSONObject();
            auth.put("username","adm");
            auth.put("password", "pwd");
            JSONObject coords=new JSONObject();
            coords.put("x", "23");
            coords.put("y", "255");
            myJSON.put("auth", auth.toString());
            myJSON.put("coords", coords.toString());

            sun.net.www.protocol.http.HttpURLConnection con = null;
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(myJSON.toString());
            wr.flush();
            StringBuilder sb = new StringBuilder();
            int HttpResult = con.getResponseCode();
            if(HttpResult == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }

                br.close();

                System.out.println(""+sb.toString());

            }else{
                System.out.println(con.getResponseMessage());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
