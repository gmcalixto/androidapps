package com.example.logonpflocal.persistenciasql;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = new DatabaseManager(this,"alunos",null,1);
    }

    public void pesquisar(View view){
        EditText id = findViewById(R.id.txtId);

        Cursor c = db.listaAlunosPorId(new Integer(id.getText().toString()));

        if(c.getCount() > 0){
            c.moveToFirst();

            EditText nome = findViewById(R.id.txtNome);
            EditText curso = findViewById(R.id.txtCurso);

            nome.setText(c.getString(c.getColumnIndex("NOME")));
            curso.setText(c.getString(c.getColumnIndex("CURSO")));
        }
        else{
            Toast.makeText(getApplicationContext(),"Não encontrado", Toast.LENGTH_LONG).show();

        }
    }

    public void gravar(View view){

        EditText id = findViewById(R.id.txtId);
        EditText nome = findViewById(R.id.txtNome);
        EditText curso = findViewById(R.id.txtCurso);

        Cursor c = db.listaAlunosPorId(new Integer(id.getText().toString()));

        if(c.getCount() > 0){
            db.atualizaAluno(new Integer(id.getText().toString()),nome.getText().toString(),curso.getText().toString());
            Toast.makeText(getApplicationContext(),"Atualizado!", Toast.LENGTH_LONG).show();
        }
        else{
            db.inserirAluno(new Integer(id.getText().toString()),nome.getText().toString(),curso.getText().toString());
            Toast.makeText(getApplicationContext(),"Inserido!", Toast.LENGTH_LONG).show();
        }

    }

    public void limpar(View view){
        EditText id = findViewById(R.id.txtId);
        EditText nome = findViewById(R.id.txtNome);
        EditText curso = findViewById(R.id.txtCurso);

        id.setText("");
        nome.setText("");
        curso.setText("");
    }

    public void apagar(View view){
        EditText id = findViewById(R.id.txtId);

        Cursor c = db.listaAlunosPorId(new Integer(id.getText().toString()));

        if(c.getCount()>0){
            db.apagarAluno(new Integer(id.getText().toString()));
            Toast.makeText(getApplicationContext(),"Apagado!", Toast.LENGTH_LONG).show();
            limpar(null);
        }
        else{
            Toast.makeText(getApplicationContext(),"Não encontradpo!", Toast.LENGTH_LONG).show();
        }

    }
}
