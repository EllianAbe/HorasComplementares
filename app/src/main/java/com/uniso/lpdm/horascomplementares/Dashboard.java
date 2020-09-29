package com.uniso.lpdm.horascomplementares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setProgressBar();
    }

    public void setProgressBar(){
        ProgressBar simpleProgressBar=(ProgressBar) findViewById(R.id.progressBar); // initiate the progress bar
        simpleProgressBar.setMax(getMax());
        simpleProgressBar.setProgress(getProgress());
    }

    private int getMax(){
        return 100;
    }

    private int getProgress(){
        return 89;
    }
}