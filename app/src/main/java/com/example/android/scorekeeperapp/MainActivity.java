package com.example.android.scorekeeperapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.id.message;
import static android.R.transition.move;
import static com.example.android.scorekeeperapp.R.drawable.yosi;

public class MainActivity extends AppCompatActivity {

    int yosiHealth = 200;
    int edyHealth = 200;
    int[] edyButtonIDs = new int[]{R.id.edy1move, R.id.edy2move, R.id.edy3move, R.id.edy4move, R.id.edy5move};
    int[] yosiButtonIDs = new int[]{R.id.yosi1move, R.id.yosi2move, R.id.yosi3move, R.id.yosi4move, R.id.yosi5move};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Saves the health instance state for both players.
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("yosiHealth", yosiHealth);
        savedInstanceState.putInt("edyHealth", edyHealth);

//        for (int i = 0; i < edyButtonIDs.length; i++) {
//            savedInstanceState.putInt(Integer.toString(edyButtonIDs[i]), edyButtonIDs[i]);
//        }
    }

    /**
     * Restores the health instance state for both players in case of screen mode change.
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        yosiHealth = savedInstanceState.getInt("yosiHealth");
        edyHealth = savedInstanceState.getInt("edyHealth");

        dispEdyDmg(yosiHealth);
        dispYosiDmg(edyHealth);

//        for (int i = 0; i < edyButtonIDs.length; i++) {
//            savedInstanceState.getInt(Integer.toString(edyButtonIDs[i]), edyButtonIDs[i]);
//        }
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
            points = points.substring(0, 2);
            message(points);
            yosiHealth = yosiHealth - Integer.valueOf(points);

            setVisibility(true, edyButtonIDs);
            setVisibility(false, yosiButtonIDs);

            checkPlayerHealth(yosiHealth);
        } else if (points.indexOf("edy") > -1) {
            points = points.substring(0, 2);
            message(points);
            edyHealth = edyHealth - Integer.valueOf(points);

            setVisibility(false, edyButtonIDs);
            setVisibility(true, yosiButtonIDs);

            checkPlayerHealth(edyHealth);
        }
    }

    /**
     * Check whether Yosi's or Edy' health falls behind 0 - Game over.
     */
    public void checkPlayerHealth(int playerHealth) {
        String message = "Game Over \n";
        if (playerHealth <= 0) {
            if (yosiHealth > edyHealth) {
                Toast.makeText(MainActivity.this, message + "Yosimitsu wins!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, message + "Edy wins!", Toast.LENGTH_SHORT).show();
            }
            yosiHealth = edyHealth = 200;
            setVisibility(true, edyButtonIDs);
            setVisibility(true, yosiButtonIDs);
        }
        dispEdyDmg(yosiHealth);
        dispYosiDmg(edyHealth);
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
        Toast.makeText(MainActivity.this, "Starting new game...", Toast.LENGTH_SHORT).show();
        yosiHealth = edyHealth = 200;
        dispEdyDmg(yosiHealth);
        dispYosiDmg(edyHealth);
        setVisibility(true, edyButtonIDs);
        setVisibility(true, yosiButtonIDs);
    }

    private void setVisibility(boolean bool, int[] buttonIDs) {
        for (int i = 0; i < buttonIDs.length; i++) {
            Button b = (Button) findViewById(buttonIDs[i]);
            b.setEnabled(bool);
        }
    }
}
