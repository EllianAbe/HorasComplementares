package com.uniso.lpdm.horascomplementares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAutenticar(View view) {
        String appUser = ((EditText) findViewById(R.id.userName)).getText().toString();
        String appPassword = ((EditText) findViewById(R.id.userPassword)).getText().toString();

        Intent intent = new Intent(this, Dashboard.class);

        startActivity(intent);
    }
}