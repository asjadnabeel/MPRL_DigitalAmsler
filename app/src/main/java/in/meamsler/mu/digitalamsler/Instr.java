package in.meamsler.mu.digitalamsler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import in.meamsler.mu.digitalamsler.utils.Session;

public class Instr extends AppCompatActivity {
    ImageView img ;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        session = new Session(this);



        img = (ImageView) findViewById(R.id.imageView2);


        SharedPreferences sp1=this.getSharedPreferences("Login",0);

        String u_id=sp1.getString("USER_ID", null);


        Toast.makeText(this.getBaseContext(),u_id,
                Toast.LENGTH_LONG).show();


        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), AmslerStatic.class);
                startActivity(i);

                // your code here
            }
        });








    }

}
