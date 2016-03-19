package com.derekcoley.laceup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Timeline extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /** Called when the user clicks the timeline button */
    public void topCompetitors(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, TopCompetitors.class);
        // TODO: Put some sign up page logic here
        String message = "Top Competitors";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user clicks the timeline button */
    public void challenges(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Challenges.class);
        // TODO: Put some sign up page logic here
        String message = "Challenges";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user clicks the timeline button */
    public void acceptedChallenges(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, AcceptedChallenges.class);
        // TODO: Put some sign up page logic here
        String message = "Accepted Challenges";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
