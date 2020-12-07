package ru.geekbrains;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.util.Date;

public class MainFragment2 extends Fragment {

    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.main_fragment2, container, false);

        TextView textView1 = (TextView) inflatedView.findViewById(R.id.date1);
        textView1.setText(currentDateTimeString);
        TextView textView2 = (TextView) inflatedView.findViewById(R.id.date2);
        textView2.setText(currentDateTimeString);
        TextView textView3 = (TextView) inflatedView.findViewById(R.id.date3);
        textView3.setText(currentDateTimeString);
        TextView textView4 = (TextView) inflatedView.findViewById(R.id.date4);
        textView4.setText(currentDateTimeString);
        TextView textView5 = (TextView) inflatedView.findViewById(R.id.date5);
        textView5.setText(currentDateTimeString);
        TextView textView6 = (TextView) inflatedView.findViewById(R.id.date6);
        textView6.setText(currentDateTimeString);
        TextView textView7 = (TextView) inflatedView.findViewById(R.id.date7);
        textView7.setText(currentDateTimeString);
        return inflatedView;
    }
}
