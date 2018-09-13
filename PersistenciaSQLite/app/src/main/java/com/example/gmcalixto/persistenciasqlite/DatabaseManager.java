package com.example.gmcalixto.persistenciasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {


    public DatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLIENTE");

        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE(\n" +
                "\tID_CLIENTE INT NOT NULL,\n" +
                "\tCPF VARCHAR(11) NOT NULL,\n" +
                "\tNOME VARCHAR(50) NOT NULL,\n" +
                "\tDATA_NASC VARCHAR(8),\n" +
                "\tPRIMARY KEY (ID_CLIENTE)\n" +
                ");");



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLIENTE");
    }

    //insert
    public void inserirCliente(Integer id,String nome, String cpf, String data_nasc){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("ID_CLIENTE",id);
        cv.put("NOME",nome);
        cv.put("CPF",cpf);
        cv.put("DATA_NASC",data_nasc);
        db.insert("CLIENTE","ID_CLIENTE",cv);


    }

    //select
    Cursor listaTodosClientes(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT ID_CLIENTE,CPF,NOME,DATA_NASC FROM CLIENTE ORDER BY ID_CLIENTE",null);
        return cur;
    }

    //update
    public void atualizaCliente(Integer id_cliente,String nome, String cpf, String data_nasc){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("NOME",nome);
        cv.put("CPF",cpf);
        cv.put("DATA_NASC",data_nasc);
        db.update("CLIENTE",cv,"ID_CLIENTE=?",new String[]{id_cliente.toString()});
    }

    //delete
    public void removeCliente(String cpf){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CLIENTE","CPF=?",new String[]{cpf});
    }




}
