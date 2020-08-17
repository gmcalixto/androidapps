package com.example.gmcalixto.persistenciasqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//toda classe que faz a gestão de uma base interna de um
//banco de dados SQLite deve herdar de SQLiteOpenHelper
public class DatabaseManager extends SQLiteOpenHelper {


    public DatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //metodo executado quando o banco de dados é criado.
    //geralmente aqui podem ser criadas/recriadas as tabelas
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

    //método executado quando se deseja recriar o banco de dados.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLIENTE");
    }

    //método personalizado para inserir registro
    public void inserirCliente(Integer id,String nome, String cpf, String data_nasc){

        //recebe objeto para escrita no banco de dados
        SQLiteDatabase db = this.getWritableDatabase();

        //objeto que representa um novo registro no banco de dados
        ContentValues cv = new ContentValues();
        cv.put("ID_CLIENTE",id);
        cv.put("NOME",nome);
        cv.put("CPF",cpf);
        cv.put("DATA_NASC",data_nasc);

        //fazer a inserção do registro na base de dados
        db.insert("CLIENTE","ID_CLIENTE",cv);


    }

    //Faz a busca de dados na base e armazena a tabela resulado em um cursor.
    Cursor listaTodosClientes(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cur = db.rawQuery("SELECT ID_CLIENTE,CPF,NOME,DATA_NASC FROM CLIENTE ORDER BY ID_CLIENTE",null);
        return cur;
    }

    //atualiza o valor de um registro
    public void atualizaCliente(Integer id_cliente,String nome, String cpf, String data_nasc){
        SQLiteDatabase db = this.getWritableDatabase();

        //valores a serem utlizados/atualizados
        ContentValues cv = new ContentValues();

        cv.put("NOME",nome);
        cv.put("CPF",cpf);
        cv.put("DATA_NASC",data_nasc);

        //atualiza o registro baseado em uma cláusula (whereClause)
        db.update("CLIENTE",cv,"ID_CLIENTE=?",new String[]{id_cliente.toString()});
    }

    //delete com expressão semelhante a atualização
    public void removeCliente(String cpf){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CLIENTE","CPF=?",new String[]{cpf});
    }




}
