package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button addCityBtn, deleteCityBtn, confirmBtn;
    EditText cityInput;

    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 
        setContentView(R.layout.content);

        // Find views
        cityList = findViewById(R.id.city_list);
        addCityBtn = findViewById(R.id.add_city);
        deleteCityBtn = findViewById(R.id.delete_city);
        confirmBtn = findViewById(R.id.confirm);
        cityInput = findViewById(R.id.city_input);

        // Initial cities
        String[] cities = {
                "Edmonton",
                "Vancouver",
                "Moscow",
                "Sydney",
                "Berlin",
                "Vienna",
                "Tokyo",
                "Beijing",
                "Osaka"
        };

        // Data + adapter
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_activated_1,
                dataList
        );

        cityList.setAdapter(cityAdapter);

        // Allow selecting a city
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            cityList.setItemChecked(position, true);
        });

        // CONFIRM = add city
        confirmBtn.setOnClickListener(view -> {
            String city = cityInput.getText().toString().trim();

            if (!city.isEmpty()) {
                dataList.add(city);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
            }
        });

        // DELETE = remove selected city
        deleteCityBtn.setOnClickListener(view -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged();
                cityList.clearChoices();
                selectedPosition = -1;
            }
        });
    }
}
