package dev.hitema.org.databaseutilisateur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {
    ListView listvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listvUsers = (ListView) findViewById(R.id.listViewUsers);
    }
}
