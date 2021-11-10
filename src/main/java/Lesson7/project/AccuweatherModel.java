package Lesson7.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import Lesson7.project.entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "3gvlT2wsTDTAGOCzQMcwwnrhOgA8a9Lk";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";
    private static final double d = 0.55;
    private static final double c = 32;

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    public void getWeather(String selectedCity, Period period) throws IOException, SQLException {
        switch (period) {
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = oneDayForecastResponse.body().string();

                try (PrintWriter out = new PrintWriter("weather.json")) {
                    out.println(weatherResponse);
                }
                catch (Exception e){
                    System.out.println("error" + e.toString());
                }

                        String localDate = objectMapper.readTree(weatherResponse)
                                .at("/DailyForecasts")
                                .get(0).at("/Day/IconPhrase")
                                .asText();

                        double temperature = objectMapper.readTree(weatherResponse).at("/DailyForecasts")
                                .get(0)
                                .at("/Temperature/Maximum/Value")
                                .asDouble();

                temperature = (temperature - c) * d;

                System.out.println("Погода: " + localDate + " - " + (String.format("%.1f", temperature)) + "C");
                Weather weather = new Weather(selectedCity, localDate, temperature);
                dataBaseRepository.saveWeatherToDataBase(weather);
                break;
            case FIVE_DAYS:
                HttpUrl httpUrl5 = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request5 = new Request.Builder()
                        .url(httpUrl5)
                        .build();

                Response fiveDaysForecastResponse = okHttpClient.newCall(request5).execute();
                String weatherResponse5 = fiveDaysForecastResponse.body().string();
                try (PrintWriter out = new PrintWriter("weather5.json")) {
                    out.println(weatherResponse5);
                }
                catch (Exception e){
                    System.out.println("error" + e.toString());
                }
                List<Weather> myList = new ArrayList<>();
                for (int i = 0; i < 5; i++){
                    String Date5 = objectMapper.readTree(weatherResponse5)
                            .at("/DailyForecasts")
                            .get(i).at("/EpochDate")
                            .asText();

                    String weatherText = objectMapper.readTree(weatherResponse5)
                            .at("/DailyForecasts")
                            .get(i).at("/Day/IconPhrase")
                            .asText();

                    double temperature5 = objectMapper.readTree(weatherResponse5).at("/DailyForecasts")
                            .get(i)
                            .at("/Temperature/Maximum/Value")
                            .asDouble();
                    temperature5 = (temperature5 - c) * d;
                    int chislo = Integer.parseInt(Date5.trim());
                    LocalDateTime dateTime = LocalDateTime.ofEpochSecond(chislo, 0, ZoneOffset.UTC);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE,MMMM d,yyyy h:mm,a", Locale.ENGLISH);
                    String formattedDate = dateTime.format(formatter);
                    myList.add(weather = new Weather(selectedCity, Date5, temperature5));
                    System.out.println("Погода в " + selectedCity + " : " + formattedDate + " - " + weatherText + " - " + (String.format("%.1f", temperature5)) + "C");
                }
                dataBaseRepository.saveWeatherToDataBase(myList);
                break;
        }
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return dataBaseRepository.getSavedToDBWeather();
    }

    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }
}
