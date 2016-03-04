package dev.hitema.org.databaseutilisateur;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import dev.hitema.org.databaseutilisateur.data.database.Database;

public class MainActivity extends AppCompatActivity {

    private EditText edt_login,edt_password;
    private Button btn_logme, btn_registerme,btn_list_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_login = (EditText) findViewById(R.id.edt_log_pseudo);
        edt_password = (EditText) findViewById(R.id.edt_log_pwd);
        btn_logme = (Button) findViewById(R.id.btn_log_valid);
        btn_registerme = (Button) findViewById(R.id.btn_log_reg);
        btn_list_users = (Button) findViewById(R.id.btn_list_user);

        btn_registerme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(it);
            }
        });

        btn_logme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database bdd = new Database(getApplicationContext());
                bdd.open();
                if(bdd.testConnexionUtilisateur(edt_login.getText().toString(),edt_password.getText().toString())){
                    Intent it = new Intent(MainActivity.this,ProfilActivity.class);
                    it.putExtra( ProfilActivity.PSEUDO , edt_login.getText().toString() );
                    startActivity(it);
                }else{
                    Toast.makeText(MainActivity.this, "Echec connexion", Toast.LENGTH_SHORT).show();
                }
                bdd.close();

            }
        });
        btn_list_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,ListActivity.class);
                startActivity(it);
            }
        });

    }
}
