package dev.hitema.org.databaseutilisateur.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import dev.hitema.org.databaseutilisateur.data.objects.Utilisateur;

/**
 * Created by Christophe on 04/03/2016.
 */
public class Database {

    private static int VERSION_BDD = 1;
    private static String NAME_BDD = "utilisateurs.db";

    private SQLiteDatabase bdd;

    private DatabaseHandler maBaseSQLite;

    public Database(Context context){
        maBaseSQLite = new DatabaseHandler(context,NAME_BDD,null,VERSION_BDD);
    }

    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return this.bdd;
    }

    public long insertUtilisateur(Utilisateur current){

        return bdd.insert( Utilisateur.TABLE_NAME , null , current.getContentValues() );

    }

    public boolean testConnexionUtilisateur(String pseudo,String password){

        Cursor cur = bdd.rawQuery("SELECT * FROM "+Utilisateur.TABLE_NAME +
                                            " WHERE "+Utilisateur.COL_PSEUDO +  " = ? AND "+
                                            Utilisateur.COL_PASSWORD + " = ?",new String[]{pseudo,password});
        boolean result = false;
        while(cur.moveToNext()){
            result = true;
        }
         cur.close();

        return result;

    }

    public Utilisateur getUtilisateurByPseudo(String pseudo){
        Utilisateur result = null;

        Cursor cur = bdd.rawQuery("SELECT * FROM "+Utilisateur.TABLE_NAME +
                " WHERE "+Utilisateur.COL_PSEUDO +  " = ? ",new String[]{pseudo});

        while(cur.moveToNext()){
            result = new Utilisateur();
            result.fromCursor(cur);
        }
        cur.close();

        return result;
    }

    public int updateUtilisateur(Utilisateur user){

        return bdd.update(Utilisateur.TABLE_NAME , user.getContentValues() , Utilisateur.COL_PSEUDO+" = ?" ,
                new String[]{user.getPseudo()});

    }

}
