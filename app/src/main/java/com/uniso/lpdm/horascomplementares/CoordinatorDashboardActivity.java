package com.uniso.lpdm.horascomplementares;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/*  essa atividade deve ser responsável pela visualização pelo coordenador das atividades
    aqui ele deverá verificar as atividades que estão pendentes de aprovação e aprovar ou rejeitar.
 */
public class CoordinatorDashboardActivity extends Activity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_dashboard);
        databaseHelper = new DatabaseHelper(CoordinatorDashboardActivity.this);

        listarAtividades();
    }

    public void listarAtividades() {

        List<AtividadeComplementar> todos = databaseHelper.selecionarPendentes();
        ListView lvAtividades = (ListView) findViewById(R.id.atividadesPendentes);

        ArrayAdapter atividadeAdapter = new ArrayAdapter<AtividadeComplementar>(CoordinatorDashboardActivity.this, android.R.layout.simple_list_item_1, todos);
        lvAtividades.setAdapter(atividadeAdapter);
    };

    public void onClickAceitar(View view){
        databaseHelper.atualizarAtividade(10);
    }

    public void onClickRejeitar(View view) {
        databaseHelper.atualizarAtividade(20);
    }

}