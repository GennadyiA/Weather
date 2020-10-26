package ru.geekbrains;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;

import java.util.Date;

public class MainFragment1 extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflatedView = inflater.inflate(R.layout.main_fragment1, null);
        Button button2 =   inflatedView.findViewById(R.id.button2);
        button2.setOnClickListener(this);
        return inflater.inflate(R.layout.main_fragment1, container, false);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }

    Date today = new Date();


}
