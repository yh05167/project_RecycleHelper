package org.techtown.drawer;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.techtown.drawer.Campaign_fragment;
import org.techtown.drawer.Challenge_fragment;
import org.techtown.drawer.FragmentCallback;
import org.techtown.drawer.Home_fragment;
import org.techtown.drawer.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {

    Home_fragment homefragment;
    Challenge_fragment challengefragment;
    Campaign_fragment campaignfragment;
    DrawerLayout drawer;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        homefragment = new Home_fragment();
        challengefragment = new Challenge_fragment();
        campaignfragment = new Campaign_fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.container, homefragment).commit();
    }

    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id ==R.id.menu_home){
            onFragmentSelected(0,null);
        }
        else if(id==R.id.menu_challenge){
            onFragmentSelected(1,null);
        }
        else if(id==R.id.menu_campaign){
            onFragmentSelected(2,null);
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onFragmentSelected(int position, Bundle bundle){
        Fragment curFragment = null;

        if(position ==0){
            curFragment = homefragment;
            toolbar.setTitle("Recycle Helper");
        }
        else if(position ==1){
            curFragment = challengefragment;
            toolbar.setTitle("챌린지");
        }
        else if(position ==2){
            curFragment = campaignfragment;
            toolbar.setTitle("캠페인");
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, curFragment).commit();
    }
    public void showChallengeAll() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Challenge_all_fragment()).commit();
    }
    public void showChallengeMy() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new Challenge_my_fragment()).commit();
    }
}