package com.derekcoley.laceup;

import android.content.Intent;
import android.os.Bundle;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /** Called when the user clicks the top competitors button */
    public void topCompetitors(View view) {
        Intent intent = new Intent(this, TopCompetitors.class);
        String message = "Top Competitors";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user clicks the challenges button */
    public void challenges(View view) {
        Intent intent = new Intent(this, Challenges.class);
        String message = "Challenges";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /** Called when the user clicks the accepted challenges button */
    public void acceptedChallenges(View view) {
        Intent intent = new Intent(this, AcceptedChallenges.class);
        String message = "Accepted Challenges";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
