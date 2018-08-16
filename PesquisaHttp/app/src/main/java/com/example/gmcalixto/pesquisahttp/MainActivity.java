package com.example.gmcalixto.pesquisahttp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void executaConsulta(View view){

        String response;
        String url = "https://reqres.in/api/users/";

        EditText txtConsulta = findViewById(R.id.editTextPesquisa);
        url += txtConsulta.getText().toString();

        TextView txtNome = findViewById(R.id.txtNome);
        TextView txtSobrenome = findViewById(R.id.txtSobrenome);
        TextView txtMensagem = findViewById(R.id.viewMensagem);

        //NetworkToolkit.getJSONFromAPI(url);

        new DataGetter(txtNome,txtSobrenome,txtMensagem).execute(url);

    }

    public void executaPost(View view){

        String url = "https://reqres.in/api/users/";
        String parameter =  "{\n" +
                "    \"name\": \"testando\",\n" +
                "    \"job\": \"1234\"\n" +
                "}";
        TextView txtMensagem = findViewById(R.id.viewMensagem);

       new DataGetterPost(txtMensagem).execute(url,parameter);

    }

    public void executaPut(View view) {

        try {
            String url = "https://reqres.in/api/users/";

            EditText txtConsulta = findViewById(R.id.editTextPesquisa);
            url += txtConsulta.getText().toString();

            TextView txtMensagem = findViewById(R.id.viewMensagem);
            TextView txtNome = findViewById(R.id.txtNome);
            TextView txtSobrenome = findViewById(R.id.txtSobrenome);


            JSONObject mensagemPut = new JSONObject();
            mensagemPut.put("name", txtNome.getText().toString());
            mensagemPut.put("job", txtSobrenome.getText().toString());

            new DataGetterPut(txtMensagem).execute(url, mensagemPut.toString());
        } catch (JSONException e) {
            TextView txtNome = findViewById(R.id.txtNome);
            txtNome.setText("erroJSON");
        }

    }

        public void executaDelete(View view){
            String response;
            String url = "https://reqres.in/api/users/";

            EditText txtConsulta = findViewById(R.id.editTextPesquisa);
            url += txtConsulta.getText().toString();

            TextView txtMensagem = findViewById(R.id.viewMensagem);


            new DataGetterDelete(txtMensagem).execute(url);
        }
}
