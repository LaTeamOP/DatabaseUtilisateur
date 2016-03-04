package dev.hitema.org.databaseutilisateur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.hitema.org.databaseutilisateur.data.database.Database;
import dev.hitema.org.databaseutilisateur.data.objects.Utilisateur;

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_nom,edt_prenom,edt_password,edt_pseudo;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edt_nom = (EditText) findViewById(R.id.edt_reg_nom);
        edt_prenom = (EditText) findViewById(R.id.edt_reg_prenom);
        edt_pseudo = (EditText) findViewById(R.id.edt_reg_pseudo);
        edt_password = (EditText) findViewById(R.id.edt_reg_pwd);
        btn_register = (Button) findViewById(R.id.btn_reg_valid);

        setTitle( getResources().getString(R.string.register_title) );

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilisateur user_reg = new Utilisateur();
                user_reg.setNom(edt_nom.getText().toString());
                user_reg.setPrenom(edt_prenom.getText().toString());
                user_reg.setPassword(edt_password.getText().toString());
                user_reg.setPseudo(edt_pseudo.getText().toString());

                Database bdd = new Database(getApplicationContext());
                bdd.open();
                long result_insert = bdd.insertUtilisateur(user_reg);
                bdd.close();

                if(result_insert>-1){
                    Toast.makeText(getApplicationContext(),"Utilisateur inséré",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Echec de l'insertion",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}


