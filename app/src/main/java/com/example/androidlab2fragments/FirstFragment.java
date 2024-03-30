package com.example.androidlab2fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.androidlab2fragments.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FirstFragment extends Fragment {
    OnButtonClickListener listener;

    public interface OnButtonClickListener {
        void onButtonClick(String text, int textSize);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonClickListener) {
            listener = (OnButtonClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnButtonClickListener");
        }
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonOk).setOnClickListener(v -> {
            int checkedRadioButtonId = ((RadioGroup) view.findViewById(R.id.radioGroupForSize)).getCheckedRadioButtonId();
            if (Objects.requireNonNull(((TextInputEditText) view.findViewById(R.id.textInput)).getText()).toString().isEmpty()) {
                showAlert(v, "Please enter text to input", view.findViewById(R.id.buttonOk));
            } else if (checkedRadioButtonId == -1) {
                showAlert(v, "Please choose the size of font", view.findViewById(R.id.buttonOk));
            } else {
                listener.onButtonClick(Objects.requireNonNull(((TextInputEditText) view.findViewById(R.id.textInput)).getText()).toString(), getSizeOfText(checkedRadioButtonId, view));
            }
        });
    }

    private void showAlert(View v, String text, Button fragmentFirstBinding) {
        Snackbar.make(v, text, Snackbar.LENGTH_LONG).setAnchorView(fragmentFirstBinding).setAction("Action", null).show();
    }

    private int getSizeOfText(int checkedRadioButton, View view) {
        int size = 0;
        if (checkedRadioButton == view.findViewById(R.id.radioButtonSize1).getId()) {
            size = 12;
        } else if (checkedRadioButton == view.findViewById(R.id.radioButtonSize2).getId()) {
            size = 16;
        } else if (checkedRadioButton == view.findViewById(R.id.radioButtonSize3).getId()) {
            size = 20;
        }
        return size;
    }

}