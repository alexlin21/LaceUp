package com.derekcoley.laceup;

import android.os.Bundle;
import android.app.Activity;

public class Challenges extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
