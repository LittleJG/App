package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class ConsultaCep extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cep);

        Button bntBuscar = findViewById(R.id.bntBuscar);

        final EditText cep = findViewById(R.id.textCep);
        final TextView resposta = findViewById(R.id.textResultado);

        bntBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CEP resultado = new HttpService(cep.getText().toString()).execute().get();
                    resposta.setText(resultado.toString());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
