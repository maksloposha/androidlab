package com.example.androidlab2fragments;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnButtonClickListener {
    SecondFragment secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secondFragment = ((SecondFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2));
    }

    @Override
    public void onButtonClick(String text, int textSize) {
        secondFragment.updateTextView(text, textSize);
    }
}