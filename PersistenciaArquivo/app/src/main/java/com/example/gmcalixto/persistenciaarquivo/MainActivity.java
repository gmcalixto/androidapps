package com.example.gmcalixto.persistenciaarquivo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public final String fileName = "texto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void doGravar(View view){
        EditText texto = findViewById(R.id.editTextPersistencia);
        gravaDadoArquivo(fileName, texto.getText().toString());
    }

    public void doRecuperar(View view){
        EditText texto = findViewById(R.id.editTextPersistencia);
        texto.setText(recuperaDadoArquivo(fileName));
    }

    public void gravaDadoArquivo(String fileName, String data){
        try{
            FileOutputStream fs =  openFileOutput(fileName, Context.MODE_PRIVATE);

            fs.write(data.getBytes());
            fs.close();

        }
        catch(FileNotFoundException e){

        }
        catch(IOException e){

        }
    }

    public String recuperaDadoArquivo(String fileName){
        try{
            int data;
            StringBuilder output  = new StringBuilder();

            FileInputStream fi =  openFileInput(fileName);
            data = fi.read();

            while(data != -1){
                output.append((char)data);
                data = fi.read();
            }
            return output.toString();
        }
        catch(FileNotFoundException e){
            return "";
        }
        catch(IOException e){
            return "";
        }
    }
}
