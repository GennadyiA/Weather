package ru.geekbrains;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;

public class MainFragment1 extends Fragment implements View.OnClickListener {
    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    private static final String TAG = "WEATHER";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?id=524901&appid=";
    private static final String WEATHER_API_KEY = "e3a01d4021f950c06b450aa8588b3b67";

    private EditText city;
    private EditText temperature;
    private EditText pressure;
    private EditText humidity;
    private EditText windSpeed;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.main_fragment1, container, false);
        ImageButton button2 =  inflatedView.findViewById(R.id.button2);
        button2.setOnClickListener(this);

        TextView textView =  inflatedView.findViewById(R.id.today);
        textView.setText(currentDateTimeString);

        city = inflatedView.findViewById(R.id.textCity);
        temperature = inflatedView.findViewById(R.id.textTemprature);
        pressure = inflatedView.findViewById(R.id.textPressure);
        humidity = inflatedView.findViewById(R.id.textHumidity);
        windSpeed = inflatedView.findViewById(R.id.textWindspeed);
        Button refresh = inflatedView.findViewById(R.id.refresh);
        refresh.setOnClickListener(clickListener);
        return inflatedView;
    }



    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                final URL uri = new URL(WEATHER_URL + WEATHER_API_KEY);
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

        private void displayWeather(WeatherRequest weatherRequest){
            city.setText(weatherRequest.getName());
            temperature.setText(String.format("%f2", weatherRequest.getMain().getTemp()));
            pressure.setText(String.format("%d", weatherRequest.getMain().getPressure()));
            humidity.setText(String.format("%d", weatherRequest.getMain().getHumidity()));
            windSpeed.setText(String.format("%d", weatherRequest.getWind().getSpeed()));
        }
    };



    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }




}
