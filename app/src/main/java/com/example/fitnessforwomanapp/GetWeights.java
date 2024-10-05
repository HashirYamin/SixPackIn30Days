package com.example.fitnessforwomanapp;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GetWeights extends AppCompatActivity {

    private TextView tvCurrentWeight;
    private SeekBar seekBarWeight;
    private RadioGroup radioGroupUnit;
    private boolean isKg = true;  // Default is kg
    private float currentWeight = 75.0f;  // Default weight in kg


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout resource file for this activity
        setContentView(R.layout.activity_get_weights); // Use the correct layout file

        // Initialize views after setting the content view
        tvCurrentWeight = findViewById(R.id.tvCurrentWeight);
        seekBarWeight = findViewById(R.id.weightSeekBar);
        radioGroupUnit = findViewById(R.id.radioGroupUnit);

        // SeekBar max value range is 73 to 77 (40 steps for smooth progress)
        seekBarWeight.setMax(40); // 40 steps between 73 and 77 (0.1 increments)
        seekBarWeight.setProgress(20); // Initial value corresponding to 75.0 kg

        // Initialize SeekBar listener
        seekBarWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentWeight = 73.0f + (progress * 0.1f);  // Calculate weight based on progress
                updateWeightDisplay();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: handle start of touch
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Optional: handle stop of touch
            }
        });

        // Initialize RadioGroup listener for kg/lb toggle
        radioGroupUnit.setOnCheckedChangeListener((group, checkedId) -> {
            isKg = checkedId == R.id.rbKg;
            updateWeightDisplay();
        });
    }

    // Method to update the displayed weight
    private void updateWeightDisplay() {
        String weightText;
        if (isKg) {
            weightText = String.format("%.1f kg", currentWeight); // Display weight in kg
        } else {
            float weightInLb = currentWeight * 2.20462f;  // Convert kg to lb
            weightText = String.format("%.1f lb", weightInLb); // Display weight in lb
        }
        tvCurrentWeight.setText(weightText);  // Update the TextView with the calculated weight
    }
}