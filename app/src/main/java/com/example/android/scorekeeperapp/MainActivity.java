package com.example.android.scorekeeperapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;
import static com.example.android.scorekeeperapp.R.drawable.yosi;

public class MainActivity extends AppCompatActivity {

    int yosiHealth = 200;
    int edyHealth = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Saves the health instance state for both teams
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("yosiHealth", yosiHealth);
        savedInstanceState.putInt("edyHealth", edyHealth);
    }

    /**
     * Restores the health instance state for both teams in case of screen mode change
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        yosiHealth = savedInstanceState.getInt("yosiHealth");
        edyHealth = savedInstanceState.getInt("edyHealth");

        dispEdyDmg(yosiHealth);
        dispYosiDmg(edyHealth);
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
     * Displays the health for each character after a hit.
     */
    public void damage(View view) {
        String points = view.getTag().toString();
        if (points.indexOf("yosi") > -1) {
            points = points.substring(0,2);
            message(points);
            // check whether Yosi's health falls behind 0
            yosiHealth = yosiHealth - Integer.valueOf(points);
            if (yosiHealth < 0) {
                yosiHealth = 0;
            }
            dispEdyDmg(yosiHealth);
        }
        else if (points.indexOf("edy") > -1){
            points = points.substring(0,2);
            message(points);
            edyHealth = edyHealth - Integer.valueOf(points);
            // check whether Edy's health falls behind 0
            if (edyHealth < 0) {
                edyHealth = 0;
            }
            dispYosiDmg(edyHealth);
        }
    }

    /**
     * Display message with number of points damage
     */
    public void message(String points) {
        String message = "- " + points + " damage!";
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Reset Health for both
     */
    public void resetHealth(View view) {
        yosiHealth = 200;
        edyHealth = 200;
        dispEdyDmg(yosiHealth);
        dispYosiDmg(edyHealth);
    }
}
