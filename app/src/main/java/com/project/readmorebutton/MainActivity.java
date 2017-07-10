package com.project.readmorebutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.project.readmorebutton.view.ExpandableTextView;

public class MainActivity extends AppCompatActivity {

    private int MAX_LINES = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableTextView text = (ExpandableTextView) findViewById(R.id.text);
        text.setTrimLength(MAX_LINES);
        text.setText(getString(R.string.text), TextView.BufferType.NORMAL);
    }
}
