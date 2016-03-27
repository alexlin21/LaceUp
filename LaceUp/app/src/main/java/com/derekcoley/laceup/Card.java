package com.derekcoley.laceup;

import android.widget.TextView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class Card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String title = getIntent().getExtras().getString("title");
        String desc = getIntent().getExtras().getString("description");
        setTitle(title);
        setContentView(R.layout.activity_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView descView = (TextView)findViewById(R.id.tview);
        descView.setText(desc);
    }
}
