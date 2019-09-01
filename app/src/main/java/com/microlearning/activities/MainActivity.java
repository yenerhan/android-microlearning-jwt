package com.microlearning.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.microlearning.fragments.CardsFragment;
import com.microlearning.fragments.HomeFragment;
import com.microlearning.fragments.ProfileFragment;
import com.microlearning.interfaces.CardViewListener;
import com.sinavtime.microlearning.R;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        CardViewListener {

    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //bottom menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new HomeFragment());
    }

    private void displayFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                //getSupportActionBar().setTitle("AKS");
                break;
            case R.id.navigation_dashboard:
                fragment = new CardsFragment();
                //getSupportActionBar().setTitle("AKS");
                break;
            case R.id.navigation_notifications:
                fragment = new ProfileFragment();
                //getSupportActionBar().setTitle("AKS");
                break;
        }
        if (fragment != null) {
            displayFragment(fragment);
            return true;
        }
        return false;
    }

    @Override
    public void cardActivityStart(Long cardId) {
        Intent intent = new Intent(this, ContentActivity.class);
        intent.putExtra("selectedCardId", cardId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
