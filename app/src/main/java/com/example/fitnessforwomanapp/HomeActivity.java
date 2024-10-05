package com.example.fitnessforwomanapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        bottomNavigationView = findViewById(R.id.bottomNavigation_view);

        // Check if savedInstanceState is null to avoid re-adding the fragment on orientation change
        if (savedInstanceState == null) {
            loadFragment(new HomeDashboardFragment());
        }
         bottomNavigationView.setOnItemSelectedListener(item -> {
            while (true) {
                if (item.getItemId() == R.id.training_bottom) {
                    loadFragment(new HomeDashboardFragment());
                    break;
                }
                if (item.getItemId() == R.id.report_bottom) {
                    loadFragment(new ReportsFragment());
                    break;
                }
                if (item.getItemId() == R.id.user_bottom) {
                    loadFragment(new UserFragment());
                    break;
                }
                break; // Exit the while loop if no match is found
            }
            return true; // Return true to indicate that the item selection has been handled
        });
    }


    // Method to load the fragment
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.visible, fragment); // 'visible' is the FrameLayout where the fragment will be loaded
        transaction.commit(); // Commit the transaction
    }
}
