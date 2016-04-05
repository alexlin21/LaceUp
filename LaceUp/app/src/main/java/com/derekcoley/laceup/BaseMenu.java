package com.derekcoley.laceup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.facebook.login.LoginManager;

public class BaseMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_challenges) {
            // Challenges
            Intent intent = new Intent(this, Challenges.class);
            // TODO: Put some sign up page logic here
            String message = "Challenges";
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        } else if (id == R.id.nav_matches) {
            // Matches
            Intent intent = new Intent(this, AcceptedChallenges.class);
            // TODO: Put some sign up page logic here
            String message = "Accepted Challenges";
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        } else if (id == R.id.nav_top_challengers) {
            // Top Competitors
            Intent intent = new Intent(this, TopCompetitors.class);
            // TODO: Put some sign up page logic here
            String message = "Top Competitors";
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        } else if (id == R.id.nav_timeline) {
            Intent intent = new Intent(this, Timeline.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {
            // Take User to Settings
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            // Log out button
            LoginManager.getInstance().logOut();
            Intent intent = new Intent(this, MyActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
