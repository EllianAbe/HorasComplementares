package com.uniso.lpdm.horascomplementares;

import androidx.annotation.RequiresApi;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DashboardActivity extends Activity {
    int currentProgress;
    ProgressBar simpleProgressBar, progressBarIdiomas, progressBarEventos, progressBarFormacaoComplementar;
    TextView percentage;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setProgressBar();
    }

    // PEGA O PROGRESSO DE CADA CATEGORIA E PROGRESSO TOTAL.
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setProgressBar(){
          currentProgress = 20;

//        List<Progress> progressList = getProgress();
//
//        for(Progress progress : progressList){
//            renderIndividualProgress(progress);
//            currentProgress += progress.progressValue;
//        }

        simpleProgressBar=(ProgressBar) findViewById(R.id.progressBar); // initiate the progress bar
        percentage = (TextView) findViewById(R.id.percent);

        simpleProgressBar.setMax(getMax());
        simpleProgressBar.setProgress(currentProgress);

        percentage.setText(String.format("%d%%", currentProgress / getMax()));

        progressBarIdiomas =(ProgressBar) findViewById(R.id.progressBarIdiomas);
        progressBarIdiomas.setMax(30);
        progressBarIdiomas.setProgress(30);

        progressBarEventos =(ProgressBar) findViewById(R.id.progressBarEvento);
        progressBarEventos.setMax(20);
        progressBarEventos.setProgress(10);

        progressBarFormacaoComplementar  =(ProgressBar) findViewById(R.id.progressBarFormacaoComplementar);
        progressBarFormacaoComplementar.setMax(50);
        progressBarFormacaoComplementar.setProgress(25);
    }

    // ESSA FUNÇÃO É PARA EFEITOS DE DEMONSTRAÇÃO, ELA PRECISA SER ALTERADA PARA QUANDO UTILIZARMOS UMA API NO BANCO
    private int getMax(){
        return 100;
    }


    /* essa atividade gera um report basicão com o progresso total e o progresso de cada categoria,
       esse progresso é formatado como string e compartilhado usando ACTION_SEND*/
    public void onClickCompartilhar(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        String progressReport = String.format("O progresso total é de %d/%d horas\n" +
                        "O progresso em Idiomas é de %d/%d horas\n" +
                        "O progresso em Eventos é de %d/%d horas\n" +
                        "O progresso em Formação Complementar é de %d/%d horas",
                        simpleProgressBar.getProgress(), simpleProgressBar.getMax(),
                        progressBarIdiomas.getProgress(), progressBarIdiomas.getMax(),
                        progressBarEventos.getProgress(), progressBarEventos.getMax(),
                        progressBarFormacaoComplementar.getProgress(), progressBarFormacaoComplementar.getMax());

        intent.putExtra(Intent.EXTRA_TEXT, progressReport);
        startActivity(intent);
    }

    public void onClickAddAtividade(View view){
        Intent intent = new Intent(this, CadastroAtividadeActivity.class);

        startActivity(intent);
    }
}