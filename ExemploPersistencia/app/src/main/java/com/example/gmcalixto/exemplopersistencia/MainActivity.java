package com.example.gmcalixto.exemplopersistencia;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final String PreferenceKey = "PREF";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gravaPreferencia(View view){
        EditText txtPref = findViewById(R.id.txtPref);
        addPreference("teste",txtPref.getText().toString());
    }

    public void recuperaPreferencia(View view){
        TextView txtMensagem = findViewById(R.id.textViewSharedPref);
        txtMensagem.setText(getPreference("teste"));
    }

    public void addPreference(String chave,String valor){
        SharedPreferences sh = getSharedPreferences(PreferenceKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();

        ed.putString(chave,valor);
        ed.apply();
    }

    public String getPreference(String chave){
        SharedPreferences sh = getSharedPreferences(PreferenceKey,Context.MODE_PRIVATE);
        return sh.getString(chave,"");
    }
}
