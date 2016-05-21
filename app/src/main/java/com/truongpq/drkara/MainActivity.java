package com.truongpq.drkara;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.truongpq.drkara.adapters.SongsAdapter;
import com.truongpq.drkara.fragments.InformationFragment;
import com.truongpq.drkara.fragments.KaraFragment;
import com.truongpq.drkara.models.Song;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.post(new Runnable() {
            @Override
            public void run() {
                onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_arirang));
                navigationView.setCheckedItem(R.id.nav_arirang);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;


        int id = item.getItemId();

        if (id == R.id.nav_arirang) {
            fragment = KaraFragment.newInstance(SongsAdapter.TABLE_ARIRANG);
        } else if (id == R.id.nav_california) {
            fragment = KaraFragment.newInstance(SongsAdapter.TABLE_CALIFORNIA);
        } else if (id == R.id.nav_musiccore) {
            fragment = KaraFragment.newInstance(SongsAdapter.TABLE_MUSICCORE);
        } else if (id == R.id.nav_vietktv) {
            fragment = KaraFragment.newInstance(SongsAdapter.TABLE_VIETKTV);
        } else if (id == R.id.nav_favorite) {
            fragment = InformationFragment.newInstance();
        } else if (id == R.id.nav_help) {
            fragment = InformationFragment.newInstance();
        } else if (id == R.id.nav_information) {
            fragment = InformationFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();

        getSupportActionBar().setTitle(item.getTitle());
        TextView tvHeader = (TextView) findViewById(R.id.title_header);
        tvHeader.setText(item.getTitle() + "");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
