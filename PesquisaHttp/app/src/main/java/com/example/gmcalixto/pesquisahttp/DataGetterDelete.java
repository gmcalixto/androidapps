package com.example.gmcalixto.pesquisahttp;

import android.os.AsyncTask;
import android.widget.TextView;

public class DataGetterDelete extends AsyncTask<String,Void, Boolean> {

    TextView txtMensagem;

    public DataGetterDelete(TextView txtMensagem) {
        this.txtMensagem = txtMensagem;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        return NetworkToolkit.doDelete(strings[0]);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean) txtMensagem.setText("Deleted!");
        else txtMensagem.setText("Not Deleted!");
    }
}
