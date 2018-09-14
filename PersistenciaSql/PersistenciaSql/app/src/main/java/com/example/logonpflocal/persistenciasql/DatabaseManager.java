package com.example.logonpflocal.persistenciasql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.logging.XMLFormatter;

public class DatabaseManager extends SQLiteOpenHelper {

    public DatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE ALUNO(\n" +
                "\tID_ALUNO INT,\n" +
                "\tNOME VARCHAR(50) NOT NULL,\n" +
                "\tCURSO VARCHAR(10) NOT NULL,\n" +
                "\tPRIMARY KEY (ID_ALUNO)\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ALUNO");
        sqLiteDatabase.execSQL("CREATE TABLE ALUNO(\n" +
                "\tID_ALUNO INT,\n" +
                "\tNOME VARCHAR(50) NOT NULL,\n" +
                "\tCURSO VARCHAR(10) NOT NULL,\n" +
                "\tPRIMARY KEY (ID_ALUNO)\n" +
                ");");
    }

    public void inserirAluno(Integer id,String nome,String curso){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("ID_ALUNO",id);
        cv.put("NOME",nome);
        cv.put("CURSO",curso);

        db.insert("ALUNO","ID_ALUNO",cv);
    }

    public void atualizaAluno(Integer id,String nome,String curso){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("NOME",nome);
        cv.put("CURSO",curso);

        db.update("ALUNO",cv,"ID_ALUNO=?",new String[] {id.toString()});

    }

    public void apagarAluno(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("ALUNO","ID_ALUNO=?",new String[] {id.toString()});
    }

    public Cursor listaAlunosPorId(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("SELECT ID_ALUNO,NOME,CURSO FROM ALUNO WHERE ID_ALUNO=?", new String[]{id.toString()});
    }
}
