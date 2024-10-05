package com.example.fitnessforwomanapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;


import android.animation.ArgbEvaluator;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.tabs.TabLayout;

import pl.droidsonroids.gif.GifImageView;

public class ExerciseActivity extends AppCompatActivity {

    TextView ToolBarDayNo;
    FrameLayout contentFrame;
    private ViewPager2 viewPager;
    TextView animation, muscle, howTodo, select;
    String exercisename, duration, instructions, mistakes, breathingtips, muscles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        exercisename = "high Stepping";
        duration = "20 secs";
        instructions = ""

//        RelativeLayout instructions = findViewById(R.id.instructions);

        ToolBarDayNo = findViewById(R.id.day_number);
//        contentFrame = findViewById(R.id.content_frame);
//        viewPager = findViewById(R.id.view_pager);
//        animation = findViewById(R.id.anim_txt);
//        muscle = findViewById(R.id.muscle_txt);
//        howTodo = findViewById(R.id.how_to_do_txt);
//        select = findViewById(R.id.textSelected);
//
//        // Set OnClickListeners for each TextView
//        animation.setOnClickListener(view -> {
//            animation.setTextColor(getResources().getColor(R.color.white));
//            muscle.setTextColor(getResources().getColor(R.color.blue_gym_app_resource));
//            Toast.makeText(ExerciseActivity.this, "Animation Clicked", Toast.LENGTH_SHORT).show();
//            howTodo.setTextColor(getResources().getColor(R.color.blue_gym_app_resource));
//            select.animate().x(0).setDuration(60);
//        });
//
//        muscle.setOnClickListener(view -> {
//            animation.setTextColor(getResources().getColor(R.color.blue_gym_app_resource));
//            muscle.setTextColor(Color.parseColor("#FFFFFF"));
//            Toast.makeText(ExerciseActivity.this, "Muscle Clicked", Toast.LENGTH_SHORT).show();
//            howTodo.setTextColor(getResources().getColor(R.color.blue_gym_app_resource));
//            int size = muscle.getWidth();
//            select.animate().x(size).setDuration(60);
//        });
//
//        howTodo.setOnClickListener(view -> {
//            animation.setTextColor(getResources().getColor(R.color.blue_gym_app_resource));
//            muscle.setTextColor(getResources().getColor(R.color.blue_gym_app_resource));
//            Toast.makeText(ExerciseActivity.this, "How to Clicked", Toast.LENGTH_SHORT).show();
//            howTodo.setTextColor(Color.parseColor("#FFFFFF"));
//            int size = muscle.getWidth() * 2;
//            select.animate().x(size).setDuration(60);
//        });
//

        // Set up the ViewPager and TabLayout
//
//        setupViewPager();


        Intent intent = getIntent();
        String dayNumberB = intent.getStringExtra("dayNumberB");
        String dayNumberI = intent.getStringExtra("dayNumberI");
        String dayNumberA = intent.getStringExtra("dayNumberA");

//        int[] day1Gifs = {R.drawable.exersice_1, R.drawable.exersice_2, R.drawable.exersice_3, R.drawable.exersice_4, R.drawable.exersice_5,R.drawable.exersice_6,R.drawable.exersice_7,R.drawable.exersice_8}; // Add more as needed
//        String[] day1Names = {"Mountain Climbers ", "Crunches","Situps", "Bycycle Twists", "Leg Raises","planks","some","some"};
//        String[] day1Durations = {"00:20", "00:30","00:20", "00:20", "00:20","00000","00000","00000"};

//        int[] day2Gifs = {R.drawable.exercise_3, R.drawable.exercise_4}; // Add more as needed
//        String[] day2Names = {"Push Ups", "Squats"};
//        String[] day2Durations = {"00:15", "00:25"};


        Database db = new Database(getApplicationContext(), "workout_db", null, 1);




        ScrollView scrollView = findViewById(R.id.sc_view);
        RelativeLayout beginnerExerciseView = findViewById(R.id.beginner_exercise_view);
        Toolbar toolbar = findViewById(R.id.toolbar_exercise);

        String dayNumber = null;
        if (intent.hasExtra("dayNumberB")) {
            dayNumber = intent.getStringExtra("dayNumberB");
            Toast.makeText(this, "DAY NUMBER  " + dayNumber, Toast.LENGTH_SHORT).show();
        }
        ToolBarDayNo.setText("Day "+dayNumberB);
        // Initially, hide the toolbar
        toolbar.setVisibility(View.GONE);

        // Define the colors for the transition
        final int colorPrimaryDefault = ContextCompat.getColor(this, R.color.primary_dark_default);
        final int colorScrolled = ContextCompat.getColor(this, R.color.primary_dark_scrolled);

        // Set up color evaluator for smooth transition
        final ArgbEvaluator colorEvaluator = new ArgbEvaluator();
// Determine which day to load exercises for based on the day number passed via the intent
        if (dayNumberB != null) {
            // If it's the beginner day (dayNumberB), load exercises for beginner day
            loadExercises(day1Gifs, day1Names, day1Durations);
        }


        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                float fadeOutStart = 100f;
                float fadeOutEnd = 300f;

                // Change status bar color dynamically based on scroll position
                if (scrollY > 200) {
                    // Set white status bar when scrolled
                    getWindow().setStatusBarColor(ContextCompat.getColor(ExerciseActivity.this, R.color.primary_dark_scrolled));
                } else {
                    // Set back to #E5E8EF if scrolled up
                    getWindow().setStatusBarColor(ContextCompat.getColor(ExerciseActivity.this, R.color.primary_dark_default));
                }

                // Calculate the color interpolation based on scroll position
                float scrollRatio = Math.min(1f, (float) scrollY / 200f);
                int blendedColor = (int) colorEvaluator.evaluate(scrollRatio, colorPrimaryDefault, colorScrolled);
                getWindow().setStatusBarColor(blendedColor);

                // Toolbar visibility logic with fade-in/fade-out
                if (scrollY > fadeOutStart) {
                    if (toolbar.getVisibility() != View.VISIBLE) {
                        toolbar.setVisibility(View.VISIBLE);
                        toolbar.setAlpha(0);
                        toolbar.animate().alpha(1).setDuration(200).start();
                    }
                } else {
                    if (toolbar.getVisibility() == View.VISIBLE) {
                        toolbar.animate().alpha(0).setDuration(200).withEndAction(() -> {
                            toolbar.setVisibility(View.GONE);
                        }).start();
                    }
                }

                // Fade-out effect for beginnerExerciseView
                if (scrollY > fadeOutStart && scrollY < fadeOutEnd) {
                    float alpha = 1 - ((scrollY - fadeOutStart) / (fadeOutEnd - fadeOutStart));
                    beginnerExerciseView.setAlpha(alpha);
                } else if (scrollY >= fadeOutEnd) {
                    beginnerExerciseView.setAlpha(0);
                } else {
                    beginnerExerciseView.setAlpha(1);
                }
            }
        });


    }
    private void loadExercises(int[] gifs, String[] names, String[] durations) {
        // Find your layout containing the exercises
        LinearLayout exerciseLayout = findViewById(R.id.exercise_layout);

        // Clear existing views (if necessary)
        exerciseLayout.removeAllViews();

        // Dynamically add exercises for the selected day
        for (int i = 0; i < gifs.length; i++) {
            // Inflate a new view from a layout file or create it programmatically
            View exerciseView = getLayoutInflater().inflate(R.layout.exercise_item_layout, null);



            // Find the views in the newly inflated layout
            GifImageView gifImageView = exerciseView.findViewById(R.id.gif);
            TextView exerciseName = exerciseView.findViewById(R.id.ex_name);
            TextView durationText = exerciseView.findViewById(R.id.duration);

            // Set the exercise data for the current item
            gifImageView.setImageResource(gifs[i]);
            exerciseName.setText(names[i]);
            durationText.setText(durations[i]);

            final int index = i;
            exerciseView.setOnClickListener(v -> {
                loadMedia(index); // Load the media for the selected exercise
            });

            // Add this exercise view to the parent layout
            exerciseLayout.addView(exerciseView);
        }
    }
    private void loadMedia(int exerciseIndex) {
        contentFrame.removeAllViews(); // Clear the previous content

//        if (exerciseIndex == 2) { // Example: load YouTube video for this index
//            // Replace the FrameLayout content with HowToFragment
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.content_frame, new HowToFragment());
//            fragmentTransaction.commit();
//        } else
            if (exerciseIndex == 0) {

            // Load a GIF
            GifImageView gifView = new GifImageView(this);
            gifView.setImageResource(R.drawable.exersice_1);
            contentFrame.addView(gifView);
        } else if (exerciseIndex == 1) {
            // Load a Lottie animation
            LottieAnimationView animationView = new LottieAnimationView(this);
            animationView.setAnimation(R.raw.highsteppinganimation);
            contentFrame.addView(animationView);
        }
    }
//    private void setupViewPager() {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new AnimationFragment(), "Animation");
//        adapter.addFragment(new MuscleFragment(), "Muscle");
//        adapter.addFragment(new HowToFragment(), "How to do");
//
//        viewPager2.setAdapter(adapter);
//    }


}


