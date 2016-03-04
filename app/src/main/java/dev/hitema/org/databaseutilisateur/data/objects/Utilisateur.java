package dev.hitema.org.databaseutilisateur.data.objects;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Christophe on 04/03/2016.
 */
public class Utilisateur {



    private String nom;

    private String prenom;

    private String pseudo;

    private String password;

    public static String TABLE_NAME = "UTILISATEUR";
    public static String COL_NOM = "nom";
    public static String COL_PRENOM = "prenom";
    public static String COL_PSEUDO = "pseudo";
    public static String COL_PASSWORD = "password";

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "+
            COL_NOM + " TEXT,"+
            COL_PRENOM + " TEXT,"+
            COL_PSEUDO + " TEXT PRIMARY KEY,"+
            COL_PASSWORD + " TEXT );";


    public Utilisateur(){}

    public ContentValues getContentValues(){

        ContentValues cv_utilisateur = new ContentValues();

        cv_utilisateur.put(this.COL_NOM,this.getNom());
        cv_utilisateur.put(this.COL_PRENOM,this.getPrenom());
        cv_utilisateur.put(this.COL_PSEUDO,this.getPseudo());
        cv_utilisateur.put(this.COL_PASSWORD,this.getPassword());

        return cv_utilisateur;
    }

    public void fromCursor(Cursor curseur){

        this.setPseudo(curseur.getString(curseur.getColumnIndex(this.COL_PSEUDO)));
        this.setNom(curseur.getString(curseur.getColumnIndex(this.COL_NOM)));
        this.setPrenom(curseur.getString(curseur.getColumnIndex(this.COL_PRENOM)));
        this.setPassword(curseur.getString(curseur.getColumnIndex(this.COL_PASSWORD)));

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }


}
