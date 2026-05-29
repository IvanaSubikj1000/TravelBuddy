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
    private EditText etDestination;
    private ListView lvDestinations;

    // Листа во која ќе ги чуваме имињата на градовите
    private ArrayList<String> destinationList;
    // Адаптер кој помага листата да се прикаже во ListView-то
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 1. Поврзување на елементите од дизајнот
        btnLogout = findViewById(R.id.btnLogout);
        btnAddDestination = findViewById(R.id.btnAddDestination);
        etDestination = findViewById(R.id.etDestination);
        lvDestinations = findViewById(R.id.lvDestinations);

        // 2. Иницијализација на листата и адаптерот
        destinationList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, destinationList);
        lvDestinations.setAdapter(adapter);

        // 3. Логика за копчето „Додади“
        btnAddDestination.setOnClickListener(v -> {
            String destination = etDestination.getText().toString().trim();

            if (!destination.isEmpty()) {
                destinationList.add(destination); // Го додаваме градот во листата
                adapter.notifyDataSetChanged();   // Му кажуваме на екранот да се освежи
                etDestination.setText("");        // Го празниме полето за пишување
            } else {
                Toast.makeText(HomeActivity.this, "Внесете име на дестинација!", Toast.LENGTH_SHORT).show();
            }
        });

        // 4. Логика за копчето „Одјави се“
        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        // 5. Логика: Долго кликање на ставка од листата за да се избрише
        lvDestinations.setOnItemLongClickListener((parent, view, position, id) -> {
            String removedItem = destinationList.get(position); // Го зема името на тоа што го бришеме
            destinationList.remove(position); // Го брише од листата
            adapter.notifyDataSetChanged();   // Го освежува екранот

            Toast.makeText(HomeActivity.this, "Избришано: " + removedItem, Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
