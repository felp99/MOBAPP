package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.view.WindowCompat;

import com.example.myapplication.databinding.ActivityBBinding;

public class ActivityB extends AppCompatActivity {

    private ActivityBBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        final EditText answerEditText = findViewById(R.id.answerEditText);
        Button submitAnswerButton = findViewById(R.id.submitAnswerButton);

        submitAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, answerEditText.getText().toString(), Snackbar.LENGTH_LONG).show();
            }
        });
    }
}