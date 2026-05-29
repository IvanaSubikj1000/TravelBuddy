package com.example.travelbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button btnLogout, btnAddDestination;
    private EditText etDestination, etDate;
    private ListView lvDestinations;

    private ArrayList<String> destinationList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. Поврзување на сите елементи (го додадовме и etDate)
        btnLogout = findViewById(R.id.btnLogout);
        btnAddDestination = findViewById(R.id.btnAddDestination);
        etDestination = findViewById(R.id.etDestination);
        etDate = findViewById(R.id.etDate);
        lvDestinations = findViewById(R.id.lvDestinations);

        destinationList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, destinationList);
        lvDestinations.setAdapter(adapter);

        // 2. Логика за додавање град + датум
        btnAddDestination.setOnClickListener(v -> {
            String destination = etDestination.getText().toString().trim();
            String date = etDate.getText().toString().trim();

            // Проверка дали се пополнети двете полиња
            if (!destination.isEmpty() && !date.isEmpty()) {
                // Ги спојуваме во еден текст: "Град (Датум)"
                String combinedData = destination + " (" + date + ")";

                destinationList.add(combinedData);
                adapter.notifyDataSetChanged();

                // Ги празниме двете полиња за нов внес
                etDestination.setText("");
                etDate.setText("");
            } else {
                Toast.makeText(HomeActivity.this, "Внесете и дестинација и датум!", Toast.LENGTH_SHORT).show();
            }
        });

        // 3. Долго кликање за бришење
        lvDestinations.setOnItemLongClickListener((parent, view, position, id) -> {
            String removedItem = destinationList.get(position);
            destinationList.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(HomeActivity.this, "Избришано: " + removedItem, Toast.LENGTH_SHORT).show();
            return true;
        });

        // 4. Одјава
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
