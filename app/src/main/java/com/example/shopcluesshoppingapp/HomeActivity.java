package com.example.shopcluesshoppingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bnv;
    private NavigationView nav;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private ImageView mIvCartIcon;
    boolean isWishlistDataAvailable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        checkWishlistData();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        mIvCartIcon = findViewById(R.id.ivCartIcon);
        mIvCartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CartLayout.class));
            }
        });

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
                        if(isWishlistDataAvailable) {
                            temp = new Wishlist();
                        } else {
                            temp = new OffersFragment();
                        }
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

    void checkWishlistData() {

        FirebaseDatabase.getInstance().getReference("wishlist").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                Log.d("TAG", "Value is: " + map);
                if(map == null) {
                    isWishlistDataAvailable = false;
                    bnv.getMenu().getItem(3).setIcon(R.drawable.offer_icon);
                    bnv.getMenu().getItem(3).setTitle("Offers");
                } else {
                    isWishlistDataAvailable = true;
                    bnv.getMenu().getItem(3).setIcon(R.drawable.favorite_blank_icon);
                    bnv.getMenu().getItem(3).setTitle("Wishlist");
                    bnv.getOrCreateBadge(R.id.bottomNavOffers).setNumber(2);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
}