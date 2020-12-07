package ru.geekbrains;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.util.Date;

import ru.geekbrains.WeatherApi.WeatherApi;

public class MainFragment1 extends Fragment implements View.OnClickListener {
    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    public static EditText city;
    public static EditText temperature;
    public static EditText pressure;
    public static EditText humidity;
    public static EditText windSpeed;


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
        refresh.setOnClickListener(WeatherApi.clickListener);
        return inflatedView;
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }
}
