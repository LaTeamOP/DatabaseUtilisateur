package dev.hitema.org.databaseutilisateur;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dev.hitema.org.databaseutilisateur.data.database.Database;
import dev.hitema.org.databaseutilisateur.data.objects.Utilisateur;

public class ProfilActivity extends AppCompatActivity {

    public static String PSEUDO = "pseudo";

    private EditText edt_pro_nom,edt_pro_prenom,edt_pro_password;
    private TextView edt_pro_login;
    private Button btn_update;

    private String login_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        edt_pro_nom = (EditText) findViewById(R.id.edt_pro_nom);
        edt_pro_prenom = (EditText) findViewById(R.id.edt_pro_prenom);
        edt_pro_login = (TextView) findViewById(R.id.edt_pro_pseudo);
        edt_pro_password = (EditText) findViewById(R.id.edt_pro_password);

        btn_update = (Button) findViewById(R.id.btn_pro_update);

        login_user = getIntent().getStringExtra(this.PSEUDO);

        final Database bdd = new Database(getApplicationContext());
        bdd.open();
        Utilisateur u =  bdd.getUtilisateurByPseudo(login_user);
        bdd.close();

        if(u != null){
            edt_pro_nom.setText(u.getNom());
            edt_pro_prenom.setText(u.getPrenom());
            edt_pro_password.setText(u.getPassword());
            edt_pro_login.setText(login_user);
        }else{
            Toast.makeText(getApplicationContext(),"Echec !",Toast.LENGTH_SHORT).show();
        }

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MISE A JOUR
                Utilisateur user_to_update = new Utilisateur();
                user_to_update.setNom(edt_pro_nom.getText().toString());
                user_to_update.setPrenom(edt_pro_prenom.getText().toString());
                user_to_update.setPseudo(edt_pro_login.getText().toString());
                user_to_update.setPassword(edt_pro_password.getText().toString());

                Database bdd_update = new Database(getApplicationContext());
                bdd_update.open();
                int update_result = bdd_update.updateUtilisateur(user_to_update);
                bdd_update.close();

                if(update_result == 1){
                    Toast.makeText(getApplicationContext(), "MAJ OK", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "MAJ FAIL !", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
