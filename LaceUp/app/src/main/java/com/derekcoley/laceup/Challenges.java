package com.derekcoley.laceup;

import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import java.util.ArrayList;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Challenges extends BaseMenu {
    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    private int index;
    private Map<String, String> cMap;

    @InjectView(R.id.frame) SwipeFlingAdapterView flingContainer;

    /** Called when the user clicks the timeline button */
    public void clickCard() {
        /* Pass in title and description to Card.class and instantiate one */
        Intent intent = new Intent(this, Card.class);
        intent.putExtra("title", al.get(index));
        intent.putExtra("description", cMap.get(al.get(index)));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cMap = new HashMap<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ButterKnife.inject(this);
        al = new ArrayList<>();
        String[] titles = {"Run Greenlake", "3-on-3 Basketball Game"};
        String[] desc = {"Run a fucking lap around Greenlake.", "First to 21."};
        for (int i = 0; i < titles.length; i++) {
            al.add(titles[i]);
            cMap.put(titles[i], desc[i]);
        }
        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                makeToast(Challenges.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(Challenges.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                al.add("XML ".concat(String.valueOf(i)));
                arrayAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                i++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                index = itemPosition;
                clickCard();
            }
        });

    }

    static void makeToast(Context ctx, String s){
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.right)
    public void right() {
        /**
         * Trigger the right event manually.
         */
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.left)
    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }
}
