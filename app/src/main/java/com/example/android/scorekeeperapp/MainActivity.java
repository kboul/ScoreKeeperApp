package com.example.android.scorekeeperapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int yosiScore = 200;
    int edyScore = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Saves the score instance state for both teams
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("yosiScore", yosiScore);
        savedInstanceState.putInt("edyScore", edyScore);
    }

    /**
     * Restores the score instance state for both teams in case of screen mode change
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        yosiScore = savedInstanceState.getInt("yosiScore");
        edyScore = savedInstanceState.getInt("edyScore");

        dispEdyDmg(yosiScore);
        dispYosiDmg(edyScore);
    }

    /**
     * Displays Edy's health after the damage by Yosi's hit.
     */
    public void dispEdyDmg(int score) {
        TextView scoreView = (TextView) findViewById(R.id.edy_health);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays Yosi's health after the damage by Edy's hit.
     */
    public void dispYosiDmg(int score) {
        TextView scoreView = (TextView) findViewById(R.id.yosi_health);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for both teams.
     */
    public void damage(View view) {
        String points = view.getTag().toString();
        if (points.indexOf("yosi") > -1) {
            points = points.substring(0,2);
            yosiScore = yosiScore - Integer.valueOf(points);
            dispEdyDmg(yosiScore);
        }
        else if (points.indexOf("edy") > -1){
            points = points.substring(0,2);
            edyScore = edyScore - Integer.valueOf(points);
            dispYosiDmg(edyScore);
        }
    }

    /**
     * Reset Score for both
     */
    public void resetHealth(View view) {
        yosiScore = 200;
        edyScore = 200;
        dispEdyDmg(yosiScore);
        dispYosiDmg(edyScore);
    }
}
