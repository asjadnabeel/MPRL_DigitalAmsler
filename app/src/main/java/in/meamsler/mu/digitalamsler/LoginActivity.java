package in.meamsler.mu.digitalamsler;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;

import in.meamsler.mu.digitalamsler.utils.Session;


public class LoginActivity extends AppCompatActivity {

    EditText Ed_uid ;
    private Session session;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session = new Session(this);
        Ed_uid  = (EditText) findViewById(R.id.Ed_User_id);
        sp=getSharedPreferences("Login", 0);


        if(sp.contains("USER_ID")) {
            Intent intent = new Intent(getApplicationContext(), Instr.class);
            //intent.putExtra("USER_ID", Ed_uid.getText().toString());
            startActivity(intent);
        }








        Button button = (Button) findViewById(R.id.sign_in);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();

                if(!sp.contains("USER_ID")) {
                    String uname = Ed_uid.getText().toString();
                    SharedPreferences.Editor Ed = sp.edit();
                    Ed.putString("USER_ID", uname);
                    Ed.commit();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), Instr.class);
                    //intent.putExtra("USER_ID", Ed_uid.getText().toString());
                    startActivity(intent);
                }


            }
        });
    }

}
