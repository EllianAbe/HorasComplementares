package com.uniso.lpdm.horascomplementares;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class CadastroAtividadeActivity extends Activity {

    // Variáveis de controle do layout
    EditText campo1, campo3;
    Spinner campo2;
    Button btnViewAll, btnAdd;
    ListView lvAtividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_atividade);

        campo1 = (EditText) findViewById(R.id.et_campo1);
        campo2 = (Spinner) findViewById(R.id.sp_campo2);
        campo3 = (EditText) findViewById(R.id.et_campo3);
        btnViewAll = (Button) findViewById(R.id.btn_selectAll);
        btnAdd = (Button) findViewById((R.id.btn_add));
        lvAtividades = (ListView) findViewById(R.id.lv_listaDeAC);

        // Exibir todas as atividades complementares do banco de dados
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(CadastroAtividadeActivity.this);
                List<AtividadeComplementar> todos = databaseHelper.selecionarTodos();

                ArrayAdapter atividadeAdapter = new ArrayAdapter<AtividadeComplementar>(CadastroAtividadeActivity.this, android.R.layout.simple_list_item_1, todos);
                lvAtividades.setAdapter(atividadeAdapter);
            }
        });

        // Adicionar uma nova atividade
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtividadeComplementar ac = null;
                DatabaseHelper databaseHelper = new DatabaseHelper(CadastroAtividadeActivity.this);
                try {
                    ac = new AtividadeComplementar(-1, campo1.getText().toString(), campo2.getSelectedItem().toString(), Integer.parseInt(campo3.getText().toString()), 0);
                    databaseHelper.adicionarAtividade(ac);
                    Toast.makeText(CadastroAtividadeActivity.this, "Sucesso!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(CadastroAtividadeActivity.this, "Erro ao criar atividade!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}