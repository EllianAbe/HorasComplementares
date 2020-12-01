/*
Anderson Paulino (00101112)
André Mobaier (00101025)
Ellian Abe (00098381)
Matheus Montalvão (00100234) 
*/

package com.uniso.lpdm.horascomplementares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    /*
        PARA IR PARA O DASHBOARD DO ALUNO
        Usuário: aluno
        Senha: aluno

        PARA IR PARA O DASHBOARD DO COORDENADOR
        Usuário: coordenador
        Senha: coordenador

        fluxo da aplicação: ao realizar o login como aluno você é direcionado para o dashboard principal
        nele há uma visualização da contagem das horas de atividades que estão com o status 10 (aprovada) no banco
        sqlite. Há uma opção para compartilhar o progresso como um texto formatado e uma opção de cadastro
        de novas atividades, que abrira uma tela onde é possível selecionar uma descrição, uma categoria e
        a quantidade de horas da atividade a ser lançada.

        Criada a atividade, não há impacto no dashboard do aluno, uma vez que o status inicial
        da atividade é 0 (pendente), para que o status da atividade seja alterado, é preciso que o coordenador
        faça a aprovação ou rejeição.
        Ao realizar o login como coordenador, é possível visualizar uma lista com todos as atividades que estão
        com o status 0 (pendente) e para aprovar ou rejeitar uma atividade o coordenador deve selecionar um grupo
        de uma ou mais atividades (pressionando as atividades para serem adicionadas ou removidas da seleção)
        *** atividades selecionadas -> background acizentado.
        Clicando em aprovar ou rejeitar, o status das atividades selecionadas será alterado, fazendo com que
        deixem de aparecer no dashboard do coordenador. Caso aprovadas, será alterado o saldo de horas no
        dashboard do aluno (desde que não haja excesso baseado no limite de cada categoria de atividade)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /*  aqui está feito um método genérico de login, que deve ser substituido para tornar
        a combinação login / password dinamica.
     */
    public void onClickAutenticar(View view) {
        String appUser = ((EditText) findViewById(R.id.userName)).getText().toString();
        String appPassword = ((EditText) findViewById(R.id.userPassword)).getText().toString();

        // se aluno vai para dashboard de aluno
        if(appUser.equals("aluno") && appPassword.equals("aluno")){
            Intent intentStudent = new Intent(this, DashboardActivity.class);
            startActivity(intentStudent);
        }
        // se coordenador vai para dashboard de coordenador;
        else if(appUser.equals("coordenador") && appPassword.equals("coordenador")){
            Intent intentCoordinator = new Intent(this, CoordinatorDashboardActivity.class);
            startActivity(intentCoordinator);
        }
        else{
            Toast toast = Toast.makeText(this, "Usuário / Senha incorretos!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
