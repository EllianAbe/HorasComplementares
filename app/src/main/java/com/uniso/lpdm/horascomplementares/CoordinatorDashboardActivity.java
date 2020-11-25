package com.uniso.lpdm.horascomplementares;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/*
  Essa atividade deve ser responsável pela visualização pelo coordenador das atividades
  aqui ele deverá verificar as atividades que estão pendentes de aprovação e aprovar ou rejeitar.
*/

/* Códigos de status de atividade
0 - Pendente
10 - Aprovado
20 - Rejeitado
*/
public class CoordinatorDashboardActivity extends Activity {
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Quando a atividade é criada todas as atividades do banco com
        // status 0 (Pendente) são exibidos e podem ser aprovados ou rejeitados
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

    // Botão para aprovar as atividades
    public void onClickAceitar(View view){
        databaseHelper.atualizarTodasAtividades(10);
        listarAtividades();
    }

    // Botão para rejeitar as atividades
    public void onClickRejeitar(View view) {
        databaseHelper.atualizarTodasAtividades(20);
        listarAtividades();
    }

}