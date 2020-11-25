package com.uniso.lpdm.horascomplementares;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class DashboardActivity extends Activity {
    final int TOTALMAX = 240, IDIOMAMAX = 100, EXTRACURRICULARMAX = 80, CURSOMAX = 120;
    int horaTotal, horaIdioma, horaExtraCurricular, horaCurso;
    ProgressBar simpleProgressBar, progressBarIdiomas, progressBarEventos, progressBarFormacaoComplementar;
    TextView percentage;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        db = new DatabaseHelper(this);

        setProgressBar();
    }

    // PEGA O PROGRESSO DE CADA CATEGORIA E PROGRESSO TOTAL.
    public void setProgressBar(){
        List<AtividadeComplementar> aprovadas = db.selecionarAprovadas();

        for(AtividadeComplementar atividade : aprovadas) {
            switch (atividade.getTipo()){
                case "Curso": horaCurso += atividade.getNumHoras(); break;
                case "Extracurricular": horaExtraCurricular += atividade.getNumHoras(); break;
                case "Idioma": horaIdioma += atividade.getNumHoras(); break;
            }
        }

        horaCurso = Math.min(horaCurso, CURSOMAX);
        horaIdioma = Math.min(horaIdioma, IDIOMAMAX);
        horaExtraCurricular = Math.min(horaExtraCurricular, EXTRACURRICULARMAX);

        horaTotal = Math.min(horaCurso + horaExtraCurricular + horaIdioma, TOTALMAX);

        simpleProgressBar=(ProgressBar) findViewById(R.id.progressBar); // initiate the progress bar
        percentage = (TextView) findViewById(R.id.percent);

        simpleProgressBar.setMax(TOTALMAX);
        simpleProgressBar.setProgress(horaTotal);

        percentage.setText(String.format("%d%%", horaTotal / TOTALMAX));

        progressBarIdiomas =(ProgressBar) findViewById(R.id.progressBarIdiomas);
        progressBarIdiomas.setMax(IDIOMAMAX);
        progressBarIdiomas.setProgress(horaIdioma);

        progressBarEventos =(ProgressBar) findViewById(R.id.progressBarEvento);
        progressBarEventos.setMax(EXTRACURRICULARMAX);
        progressBarEventos.setProgress(horaExtraCurricular);

        progressBarFormacaoComplementar  =(ProgressBar) findViewById(R.id.progressBarFormacaoComplementar);
        progressBarFormacaoComplementar.setMax(CURSOMAX);
        progressBarFormacaoComplementar.setProgress(horaCurso);
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