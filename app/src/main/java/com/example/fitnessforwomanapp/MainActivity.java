package com.example.fitnessforwomanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout myButton, myButton2, myButton3, myButton4;
    private int pressedBackground = R.drawable.pressed_background;
    private int defaultBackground = R.drawable.custom_button_background;

    private TextView tv, tv2, tv3, tv4;  // TextViews for each button's description
    private TextView lastShownTextView = null;  // Keep track of the last shown TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button next = findViewById(R.id.next_btn);

        // Initialize buttons
        myButton = findViewById(R.id.cvbg);
        myButton2 = findViewById(R.id.cvbg2);
        myButton3 = findViewById(R.id.cvbg3);
        myButton4 = findViewById(R.id.cvbg4);

        // Initialize TextViews for descriptions
        tv = findViewById(R.id.description);
        tv2 = findViewById(R.id.description2);
        tv3 = findViewById(R.id.description3);
        tv4 = findViewById(R.id.description4);

        // Set onClickListeners for each button
        myButton.setOnClickListener(v -> {
            updateButtonBackground(myButton, tv);
        });

        myButton2.setOnClickListener(v -> {
            updateButtonBackground(myButton2, tv2);
        });

        myButton3.setOnClickListener(v -> {
            updateButtonBackground(myButton3, tv3);
        });

        myButton4.setOnClickListener(v -> {
            updateButtonBackground(myButton4, tv4);
        });

        next.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        });

    }

    private void updateButtonBackground(RelativeLayout pressedButton, TextView selectedTextView) {
        // Reset all buttons to default background
        myButton.setBackgroundResource(defaultBackground);
        myButton2.setBackgroundResource(defaultBackground);
        myButton3.setBackgroundResource(defaultBackground);
        myButton4.setBackgroundResource(defaultBackground);

        // Set pressed button's background
        pressedButton.setBackgroundResource(pressedBackground);

        // Animate TextView visibility change
        if (lastShownTextView != null && lastShownTextView != selectedTextView) {
            hideTextView(lastShownTextView);
        }
        showTextView(selectedTextView);
        lastShownTextView = selectedTextView;
    }

    private void showTextView(TextView textView) {
        textView.setVisibility(View.VISIBLE);
        textView.setAlpha(0f);
        textView.setScaleX(0.8f);
        textView.setScaleY(0.8f);

        textView.setTranslationY(-50f);

        // Animate alpha and scale to make it appear smoothly
        textView.animate()
                .alpha(1f)
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(200)
                .translationY(0f)  // Animate back to its original position (downwards)
                .start();
    }

    private void hideTextView(TextView textView) {
        textView.animate()
                .alpha(0f)
                .scaleX(0.8f)
                .scaleY(0.8f)
                .setDuration(1)
                .withEndAction(() -> textView.setVisibility(View.GONE))
                .start();
    }
}
