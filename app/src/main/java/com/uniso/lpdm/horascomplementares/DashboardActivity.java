package com.uniso.lpdm.horascomplementares;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class DashboardActivity extends AppCompatActivity {
    int currentProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setProgressBar();
    }

    public void setProgressBar(){
          currentProgress = 20;

//        List<Progress> progressList = getProgress();
//
//        for(Progress progress : progressList){
//            renderIndividualProgress(progress);
//            currentProgress += progress.progressValue;
//        }

        ProgressBar simpleProgressBar=(ProgressBar) findViewById(R.id.progressBar); // initiate the progress bar
        simpleProgressBar.setMax(getMax());
        simpleProgressBar.setProgress(currentProgress);

        ProgressBar progressBarIdiomas =(ProgressBar) findViewById(R.id.progressBarIdiomas);
        progressBarIdiomas.setMax(30);
        progressBarIdiomas.setProgress(30);

        ProgressBar progressBarEventos =(ProgressBar) findViewById(R.id.progressBarEvento);
        progressBarEventos.setMax(20);
        progressBarEventos.setProgress(10);

        ProgressBar progressBarFormacaoComplementar =(ProgressBar) findViewById(R.id.progressBarFormacaoComplementar);
        progressBarFormacaoComplementar.setMax(50);
        progressBarFormacaoComplementar.setProgress(25);
    }

    private int getMax(){
        return 100;
    }


}