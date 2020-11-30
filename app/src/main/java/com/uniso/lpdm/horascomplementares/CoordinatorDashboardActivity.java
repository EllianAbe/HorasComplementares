package com.uniso.lpdm.horascomplementares;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    List<AtividadeComplementar> atividadesPendentes;
    AtividadeComplementarAdapter atividadeAdapter;
    ListView lvAtividades;
    Collection<Integer> atividadesSelecionadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Quando a atividade é criada todas as atividades do banco com
        // status 0 (Pendente) são exibidos e podem ser aprovados ou rejeitados
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_dashboard);

        databaseHelper = new DatabaseHelper(CoordinatorDashboardActivity.this);

        lvAtividades = (ListView) findViewById(R.id.atividadesPendentes);
        atividadesSelecionadas = new HashSet<Integer>();
        listarAtividades();


        AdapterView.OnItemLongClickListener onItemLongClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int atividadeId = atividadeAdapter.getAtividadeId(i);

                view.setBackgroundColor(getResources().getColor(R.color.colorSelectedItem));

                if(atividadesSelecionadas.contains(atividadeId)){
                    Toast.makeText(CoordinatorDashboardActivity.this,
                            "Atividade de id " + atividadeId + " removida dos itens selecionados",
                            Toast.LENGTH_SHORT).show();

                    atividadesSelecionadas.remove(atividadeId);
                    view.setBackgroundColor(getResources().getColor(R.color.colorItem));

                } else {
                    Toast.makeText(CoordinatorDashboardActivity.this,
                            "Atividade de id " + atividadeId + " adicionada aos itens selecionados",
                            Toast.LENGTH_SHORT).show();

                    atividadesSelecionadas.add(atividadeId);
                    view.setBackgroundColor(getResources().getColor(R.color.colorSelectedItem));
                }

                return false;
            }
        };

        lvAtividades.setOnItemLongClickListener(onItemLongClickListener);

    }

        public void listarAtividades() {
        atividadesPendentes = databaseHelper.selecionarPendentes();

        atividadeAdapter = new AtividadeComplementarAdapter(
                CoordinatorDashboardActivity.this,
                R.layout.atividade_list_element,
                new ArrayList<AtividadeComplementar>(atividadesPendentes));

        lvAtividades.setAdapter(atividadeAdapter);
        atividadesSelecionadas.clear();
    };

    // Botão para aprovar as atividades
    public void onClickAceitar(View view){

        databaseHelper.atualizarAtividades(10, atividadesSelecionadas);

        Toast.makeText(
                this,
                Integer.toString(atividadesSelecionadas.size()) + " atividades aprovadas",
                Toast.LENGTH_SHORT)
                .show();

        listarAtividades();
    }

    // Botão para rejeitar as atividades
    public void onClickRejeitar(View view) {
        databaseHelper.atualizarAtividades(20, atividadesSelecionadas);

        Toast.makeText(
                this,
                Integer.toString(atividadesSelecionadas.size()) + " atividades rejeitadas",
                Toast.LENGTH_SHORT)
                .show();

        listarAtividades();
    }

}