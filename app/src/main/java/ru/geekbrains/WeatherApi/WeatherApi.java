package ru.geekbrains.WeatherApi;

import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

import ru.geekbrains.BuildConfig;
import ru.geekbrains.MainFragment1;

public class WeatherApi implements View.OnClickListener {

    private static final String TAG = "WEATHER";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?id=524901&appid=";



    public static View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                final URL uri = new URL(WEATHER_URL + BuildConfig.WEATHER_API_KEY);
                final Handler handler = new Handler(); // Запоминаем основной поток
                new Thread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void run() {
                        HttpsURLConnection urlConnection = null;
                        try {
                            urlConnection = (HttpsURLConnection) uri.openConnection();
                            urlConnection.setRequestMethod("GET"); // установка метода получения данных -GET
                            urlConnection.setReadTimeout(10000); // установка таймаута - 10 000 миллисекунд
                            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // читаем  данные в поток
                            String result = getLines(in);
                            // преобразование данных запроса в модель
                            Gson gson = new Gson();
                            final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                            // Возвращаемся к основному потоку
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    displayWeather(weatherRequest);
                                }
                            });
                        } catch (Exception e) {
                            Log.e(TAG, "Fail connection", e);
                            e.printStackTrace();
                        } finally {
                            if (null != urlConnection) {
                                urlConnection.disconnect();
                            }
                        }
                    }
                }).start();
            } catch (MalformedURLException e) {
                Log.e(TAG, "Fail URI", e);
                e.printStackTrace();
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private String getLines(BufferedReader in) {
            return in.lines().collect(Collectors.joining("\n"));
        }

    };

    static void displayWeather(WeatherRequest weatherRequest){
        MainFragment1.city.setText(weatherRequest.getName());
        MainFragment1.temperature.setText(String.format("%f2", weatherRequest.getMain().getTemp()-273.0));
        MainFragment1.pressure.setText(String.format("%d", weatherRequest.getMain().getPressure()));
        MainFragment1.humidity.setText(String.format("%d", weatherRequest.getMain().getHumidity()));
        MainFragment1.windSpeed.setText(String.format("%d", weatherRequest.getWind().getSpeed()));
    }

    @Override
    public void onClick(View view) {

    }
}

