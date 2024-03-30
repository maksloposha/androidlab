package com.example.androidlab2fragments;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DisplayRecordsActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private RecyclerView recyclerView;
    private RecordAdapter adapter;
    private List<String> recordList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_records);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.returnToMainActivityButton).setOnClickListener(v -> {
            Intent intent = new Intent(DisplayRecordsActivity.this, MainActivity.class);
            startActivity(intent);
        });
        findViewById(R.id.buttonClearDB).setOnClickListener(v -> {
            dbHelper.clearDatabase();
            displayRecords();
        });


        // Отримати записи з бази даних і встановити їх у RecyclerView
        displayRecords();
    }

    private void displayRecords() {
        recordList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM your_table", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String text = cursor.getString(cursor.getColumnIndex("text"));
                recordList.add(text);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(this, "No records found", Toast.LENGTH_SHORT).show();
        }

        // Зберігаємо дані в адаптері і встановлюємо його в RecyclerView
        adapter = new RecordAdapter(recordList);
        recyclerView.setAdapter(adapter);
    }
}
