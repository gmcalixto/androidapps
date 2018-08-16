package com.example.gmcalixto.pesquisahttp;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class DataGetterPut extends AsyncTask<String,Void,String>{


    private TextView txtMensagem;

    public DataGetterPut(TextView txtMensagem) {
        this.txtMensagem = txtMensagem;
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkToolkit.doPut(strings[0],strings[1]);
    }

    @Override
    protected void onPostExecute(String s) {
        try{
            JSONObject jsonResponse = new JSONObject(s);
            String createdAt = jsonResponse.getString("updatedAt");

            txtMensagem.setText(createdAt);
        }
        catch(JSONException e){
            this.txtMensagem.setText("erroJSON");
        }
    }
}
