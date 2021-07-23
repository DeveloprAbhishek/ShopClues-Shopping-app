package com.example.shopcluesshoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bnv;

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        nav = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, myToolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.categoryMensTShirt:
                        Toast.makeText(HomeActivity.this, "Clothing", Toast.LENGTH_SHORT).show();
                        break;
                    case  R.id.categoryMensClothing:
                        Toast.makeText(HomeActivity.this, "jewellery", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, new HomeFragment()).commit();
        bnv = findViewById(R.id.bottom_navigation);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;
                switch (item.getItemId()) {
                    case R.id.bottomNavHome:
                        temp = new HomeFragment();
                        break;
                    case R.id.bottomNavCategories:
                        temp = new CategoriesFragment();
                        break;
                    case R.id.bottomNavMakeMoney:
                        temp = new MakeMoneyFragment();
                        break;
                    case R.id.bottomNavOffers:
                        temp = new OffersFragment();
                        break;
                    case R.id.bottomNavAccount:
                        temp = new AccountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, temp).commit();
                return true;
            }
        });
    }
}