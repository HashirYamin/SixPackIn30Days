package com.example.fitnessforwomanapp;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class HomeDashboardFragment extends Fragment {
    private ViewPager2 viewPager;
    private LevelPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_dashboard, container, false);

        // Initialize ViewPager2
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setClipToPadding(false);
        viewPager.setClipChildren(false);
        viewPager.setOffscreenPageLimit(3);

        // Access RecyclerView to adjust padding and clipping properties
        RecyclerView recyclerView = (RecyclerView) viewPager.getChildAt(0);
        recyclerView.setClipToPadding(false);
        recyclerView.setPadding(40, 0, 40, 0);
        recyclerView.setClipChildren(false);

        // Optional: Adding item decoration for space between pages
        int margin = 0;
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                       @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.right = margin;
                outRect.left = margin;
            }
        });

        // Prepare level data
        List<String> levels = new ArrayList<>();
        levels.add("Beginner");
        levels.add("Intermediate");
        levels.add("Advanced");

        // Set up the adapter
        adapter = new LevelPagerAdapter(levels);
        viewPager.setAdapter(adapter);

        // Set page transformer for preview effect
        viewPager.setPageTransformer((page, position) -> page.setAlpha(0.5f + (1 - Math.abs(position))));

        return view;
    }
}
