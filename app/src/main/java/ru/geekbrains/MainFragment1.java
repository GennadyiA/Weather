package ru.geekbrains;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.util.Date;

public class MainFragment1 extends Fragment implements View.OnClickListener {
    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.main_fragment1, container, false);
        ImageButton button2 =  inflatedView.findViewById(R.id.button2);
        button2.setOnClickListener(this);

        TextView textView =  inflatedView.findViewById(R.id.today);
        textView.setText(currentDateTimeString);



        return inflatedView;



    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }




}
