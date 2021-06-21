package com.microlearning.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.microlearning.fragments.CardDetailsFragment;
import com.microlearning.fragments.CardsFragment;
import com.microlearning.fragments.HomeFragment;
import com.microlearning.fragments.ProfileFragment;
import com.microlearning.model.GalleryItem;
import com.microlearning.fragments.GalleryFragment;
import com.sinavtime.microlearning.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        CardsFragment.CardViewListener {

    Fragment fragment = null;
    private static final int RC_READ_STORAGE = 5;
    public List<GalleryItem> galleryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            //Get images
            galleryItems = GalleryUtils.getImages(this);
        } else {
            //request permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RC_READ_STORAGE);
            galleryItems=new ArrayList<>();
        }

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //bottom menu
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new HomeFragment());
        getSupportActionBar().setTitle("Home");
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
                getSupportActionBar().setTitle("Home");
                break;
            case R.id.navigation_dashboard:
                fragment = new CardsFragment();
                getSupportActionBar().setTitle("Story");
                break;
            case R.id.navigation_notifications:
                fragment = new ProfileFragment();
                getSupportActionBar().setTitle("Profile");
                break;
        }
        if (fragment != null) {
            displayFragment(fragment);
            return true;
        }
        return false;
    }

    /*@Override
    public void cardActivityStart(Long cardId) {
        Intent intent = new Intent(this, ContentActivity.class);
        //intent.putExtra("selectedCardId", cardId);
        intent.putExtra("galleryItems", (Serializable) galleryItems);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }*/

    @Override
    public void showCardDetails(Long cardId) {
        fragment = new CardDetailsFragment();
        getSupportActionBar().setTitle("Details");
        displayFragment(fragment);
    }

    @Override
    public void createNewGallery() {
        fragment = new GalleryFragment();
        getSupportActionBar().setTitle("Gallery");
        displayFragment(fragment);
    }
}
