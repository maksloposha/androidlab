package com.example.androidlab2fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.snackbar.Snackbar;
import org.jetbrains.annotations.NotNull;

public class SecondFragment extends Fragment {
    private TextView resultTextView;
    private Button buttonCancel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListeners(view);
    }

    private void initListeners(@NotNull View view) {
        buttonCancel = view.findViewById(R.id.buttonCancel);
        resultTextView = view.findViewById(R.id.textResult);
        buttonCancel.setOnClickListener(v -> {
            if(!resultTextView.getText().toString().isEmpty()){
                resultTextView.setText("");
            }else {
                showAlert(v, "The result text container is already empty", buttonCancel);
            }
        });
    }
    private void showAlert(View v, String text, Button button) {
        Snackbar.make(v, text, Snackbar.LENGTH_LONG)
                .setAnchorView(button)
                .setAction("Action", null).show();
    }
    public void updateTextView(String text, int textSize) {
        resultTextView.setText(text);
        resultTextView.setTextSize(textSize);
    }
}