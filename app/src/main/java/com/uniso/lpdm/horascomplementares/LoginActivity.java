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

public class LoginActivity extends AppCompatActivity {

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

    }
}
