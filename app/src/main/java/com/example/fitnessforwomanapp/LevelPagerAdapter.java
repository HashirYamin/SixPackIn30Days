package com.example.fitnessforwomanapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class LevelPagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> levels;

    public LevelPagerAdapter(List<String> levels) {
        this.levels = levels;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0: // Beginner Level
                return new BeginnerViewHolder(inflater.inflate(R.layout.level_beginner, parent, false));
            case 1: // Intermediate Level
                return new IntermediateViewHolder(inflater.inflate(R.layout.level_intermediate, parent, false));
            case 2: // Advanced Level
                return new AdvancedViewHolder(inflater.inflate(R.layout.level_advanced, parent, false));
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BeginnerViewHolder) {
            ((BeginnerViewHolder) holder).bindData(position);
        } else if (holder instanceof IntermediateViewHolder) {
            ((IntermediateViewHolder) holder).bindData(position);
        } else if (holder instanceof AdvancedViewHolder) {
            ((AdvancedViewHolder) holder).bindData(position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        // Determine which layout to use based on position
        return position;
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    // ViewHolder for Beginner level
    static class BeginnerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container; // Assuming you have a container for the CardViews
        int selectedDay = -1; // Variable to track the currently selected day

        public BeginnerViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container_beginner);
        }

        public void bindData(int position) {
            container.removeAllViews(); // Clear previous views if any

            for (int i = 1; i <= 30; i++) {
                // Inflate the beginner day layout dynamically
                View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.card_day_item, container, false);

                // Adjust the size of the CardView
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                // Optionally, add margins around the CardView
                int margin = (int) (8 * itemView.getResources().getDisplayMetrics().density); // Convert dp to pixels
                params.setMargins(margin, margin, margin, margin); // Add margins around the CardView

                // Apply the layout parameters to the view
                view.setLayoutParams(params);

                // Access each view within the layout
                TextView dayText = view.findViewById(R.id.day_text);
                TextView exerciseText = view.findViewById(R.id.no_of_exercises);
                RelativeLayout dayBg = view.findViewById(R.id.day_bg);
                ImageView checkIcon = view.findViewById(R.id.check);
                Button startBtn = view.findViewById(R.id.start_btn);

                // Set the day number dynamically
                dayText.setText("Day " + i);

                // Set default background and style for Day 1
                if (i == 1) {
                    dayBg.setBackgroundResource(R.drawable.bg_days); // Default background for Day 1
                    dayText.setTextColor(Color.WHITE); // Change text color to white for Day 1
                    exerciseText.setTextColor(Color.WHITE); // Change exercise text color to white for Day 1
                    startBtn.setVisibility(View.VISIBLE); // Show the Start button by default for Day 1

                    // Increase the height of the CardView for Day 1
                    LinearLayout.LayoutParams selectedParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            (int) (100 * itemView.getResources().getDisplayMetrics().density) // Increased height (e.g., 100dp)
                    );
                    selectedParams.setMargins(margin, margin, margin, margin); // Keep margins the same
                    view.setLayoutParams(selectedParams);
                }

                // Set click listener for the CardView
                int finalI = i;
                view.setOnClickListener(v -> {
                    // Access the context from the itemView
                    Context context = itemView.getContext();

                    // Check if the current card is already selected
                    if (selectedDay != finalI) {
                        selectedDay = finalI; // Update selected day

                        // Change background color on click
                        dayBg.setBackgroundResource(R.drawable.bg_days); // Use your custom drawable

                        // Change TextView colors
                        dayText.setTextColor(Color.WHITE);
                        exerciseText.setTextColor(Color.WHITE);

                        // Show the Start button when the card is clicked
                        startBtn.setVisibility(View.VISIBLE);

                        // Increase the height of the selected day's CardView
                        LinearLayout.LayoutParams selectedParams = new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                (int) (100 * itemView.getResources().getDisplayMetrics().density) // Increased height (e.g., 120dp)
                        );
                        selectedParams.setMargins(margin, margin, margin, margin); // Keep margins the same
                        view.setLayoutParams(selectedParams);

                        // Optionally, reset the height of other CardViews to the original size
                        for (int j = 0; j < container.getChildCount(); j++) {
                            View child = container.getChildAt(j);
                            if (child != view) { // Check to avoid resetting the selected view
                                LinearLayout.LayoutParams originalParams = new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        (int) (67 * itemView.getResources().getDisplayMetrics().density) // Initial height
                                );
                                originalParams.setMargins(margin, margin, margin, margin);
                                child.setLayoutParams(originalParams);
                            }
                        }

                        // Start ExerciseActivity and pass the selected day number
                        Intent intent = new Intent(context, ExerciseActivity.class);
                        intent.putExtra("dayNumberB", String.valueOf(selectedDay)); // Pass the selected day
                        context.startActivity(intent);
                    }
                });

                // Add the CardView to the container
                container.addView(view);
            }
        }
    }


    // ViewHolder for Intermediate level
    static class IntermediateViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container; // Assuming you have a container for the CardViews

        public IntermediateViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container_intermediate);
        }

        public void bindData(int dayNumber) {
            container.removeAllViews(); // Clear previous views if any

            for (int i = 1; i <= 30; i++) {
                final int currentDay = i;
                // Inflate the intermediate day layout dynamically
                View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.card_day_item, container, false);

                // Adjust the size of the CardView
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, // Set width to match parent or a specific value
                        ViewGroup.LayoutParams.WRAP_CONTENT  // Set height to wrap content or a specific value
                );

                // Optionally, add margins around the CardView
                int margin = (int) (8 * itemView.getResources().getDisplayMetrics().density); // Convert dp to pixels
                params.setMargins(margin, margin, margin, margin); // Add margins around the CardView

                // Apply the layout parameters to the view
                view.setLayoutParams(params);

                // Access each view within the layout
                TextView dayText = view.findViewById(R.id.day_text);
                TextView exerciseText = view.findViewById(R.id.no_of_exercises);
                RelativeLayout dayBg = view.findViewById(R.id.day_bg);
                ImageView checkIcon = view.findViewById(R.id.check);
                Button startBtn = view.findViewById(R.id.start_btn);

                // Set the day number dynamically
                dayText.setText("Day " + i);

                // Set the default background for Day 1 without a click listener
                if (i == 1) {
                    dayBg.setBackgroundResource(R.drawable.bg_days); // Default background for Day 1
                    dayText.setTextColor(Color.WHITE); // Change text color to white for Day 1
                    exerciseText.setTextColor(Color.WHITE); // Change exercise text color to white for Day 1
                    startBtn.setVisibility(View.VISIBLE); // Show the Start button by default for Day 1

                    // Increase the height of the CardView for Day 1 by default
                    LinearLayout.LayoutParams selectedParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            (int) (80 * itemView.getResources().getDisplayMetrics().density) // Increased height (e.g., 100dp)
                    );
                    selectedParams.setMargins(margin, margin, margin, margin); // Keep margins the same
                    view.setLayoutParams(selectedParams);
                }

                // Set click listener for the CardView
                view.setOnClickListener(v -> {
                    // Access the context from the itemView
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, ExerciseActivity.class);
                    intent.putExtra("dayNumberI", currentDay);
                    context.startActivity(intent);

                    // Change background color on click
                    dayBg.setBackgroundResource(R.drawable.bg_days); // Use your custom drawable for intermediate

                    // Change TextView colors
                    dayText.setTextColor(Color.WHITE);
                    exerciseText.setTextColor(Color.WHITE);

                    // Show the Start button when the card is clicked
                    startBtn.setVisibility(View.VISIBLE);

                    // Increase the height of the selected day's CardView
                    LinearLayout.LayoutParams selectedParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            (int) (100 * itemView.getResources().getDisplayMetrics().density) // Increased height (e.g., 120dp)
                    );
                    selectedParams.setMargins(margin, margin, margin, margin); // Keep margins the same

                    view.setLayoutParams(selectedParams);

                    // Optionally, reset the height of other CardViews to the original size
                    for (int j = 0; j < container.getChildCount(); j++) {
                        View child = container.getChildAt(j);
                        if (child != view) { // Check to avoid resetting the selected view
                            LinearLayout.LayoutParams originalParams = new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    (int) (67 * itemView.getResources().getDisplayMetrics().density) // Initial height
                            );
                            originalParams.setMargins(margin, margin, margin, margin);
                            child.setLayoutParams(originalParams);
                        }
                    }
                });

                // Add the CardView to the container
                container.addView(view);
            }
        }
    }


    // ViewHolder for Advanced level
    static class AdvancedViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container; // Assuming you have a container for the CardViews

        public AdvancedViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container_advanced);
        }

        public void bindData(int dayNumber) {
            container.removeAllViews(); // Clear previous views if any

            for (int i = 1; i <= 30; i++) {
                final int currentDay = i;
                // Inflate the advanced day layout dynamically
                View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.card_day_item, container, false);

                // Adjust the size of the CardView
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, // Set width to match parent or a specific value
                        ViewGroup.LayoutParams.WRAP_CONTENT  // Set height to wrap content or a specific value
                );

                // Optionally, add margins around the CardView
                int margin = (int) (8 * itemView.getResources().getDisplayMetrics().density); // Convert dp to pixels
                params.setMargins(margin, margin, margin, margin); // Add margins around the CardView

                // Apply the layout parameters to the view
                view.setLayoutParams(params);

                // Access each view within the layout
                TextView dayText = view.findViewById(R.id.day_text);
                TextView exerciseText = view.findViewById(R.id.no_of_exercises);
                RelativeLayout dayBg = view.findViewById(R.id.day_bg);
                ImageView checkIcon = view.findViewById(R.id.check);
                Button startBtn = view.findViewById(R.id.start_btn);

                // Set the day number dynamically
                dayText.setText("Day " + i);

                // Set the default background for Day 1 without a click listener
                if (i == 1) {
                    dayBg.setBackgroundResource(R.drawable.bg_days); // Default background for Day 1
                    dayText.setTextColor(Color.WHITE); // Change text color to white for Day 1
                    exerciseText.setTextColor(Color.WHITE); // Change exercise text color to white for Day 1
                    startBtn.setVisibility(View.VISIBLE); // Show the Start button by default for Day 1

                    // Increase the height of the CardView for Day 1 by default
                    LinearLayout.LayoutParams selectedParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            (int) (100 * itemView.getResources().getDisplayMetrics().density) // Increased height (e.g., 120dp)
                    );
                    selectedParams.setMargins(margin, margin, margin, margin); // Keep margins the same
                    view.setLayoutParams(selectedParams);
                }

                // Set click listener for the CardView
                view.setOnClickListener(v -> {
                    // Access the context from the itemView
                    Context context = itemView.getContext();
                    Intent intent = new Intent(context, ExerciseActivity.class);
                    intent.putExtra("dayNumberA", currentDay);
                    context.startActivity(intent);

                    // Change background color on click
                    dayBg.setBackgroundResource(R.drawable.bg_days); // Use your custom drawable for advanced

                    // Change TextView colors
                    dayText.setTextColor(Color.WHITE);
                    exerciseText.setTextColor(Color.WHITE);

                    // Show the Start button when the card is clicked
                    startBtn.setVisibility(View.VISIBLE);

                    // Increase the height of the selected day's CardView
                    LinearLayout.LayoutParams selectedParams = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            (int) (100 * itemView.getResources().getDisplayMetrics().density) // Increased height (e.g., 120dp)
                    );
                    selectedParams.setMargins(margin, margin, margin, margin); // Keep margins the same

                    view.setLayoutParams(selectedParams);

                    // Optionally, reset the height of other CardViews to the original size
                    for (int j = 0; j < container.getChildCount(); j++) {
                        View child = container.getChildAt(j);
                        if (child != view) { // Check to avoid resetting the selected view
                            LinearLayout.LayoutParams originalParams = new LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    (int) (67 * itemView.getResources().getDisplayMetrics().density) // Initial height
                            );
                            originalParams.setMargins(margin, margin, margin, margin);
                            child.setLayoutParams(originalParams);
                        }
                    }
                });

                // Add the CardView to the container
                container.addView(view);
            }
        }

    }
}
