package in.meamsler.mu.digitalamsler;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;


public class DefectList extends AppCompatActivity {


    ListView list;
    String[] web = {"Distortion", "Black Spot", "Blind Spot", "Normal", "xxx", "xxxx", "xxx"};
    Integer[] imageId = {R.drawable.e, R.drawable.e, R.drawable.e, R.drawable.e, R.drawable.e, R.drawable.e, R.drawable.e};

    CheckBox defect1Check, defect2Check, defect3Check, defect4Check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button button = (Button) findViewById(R.id.defectDoneButton);
        Button retry_btn = (Button) findViewById(R.id.retryButton);
        defect1Check = (CheckBox) findViewById(R.id.defect1Check);
        defect2Check = (CheckBox) findViewById(R.id.defect2Check);
        defect3Check = (CheckBox) findViewById(R.id.defect3Check);
        defect4Check = (CheckBox) findViewById(R.id.defect4Check);


        retry_btn.setOnClickListener(new View.OnClickListener() {
                     public void onClick(View v) {


                         Intent intent = new Intent(DefectList.this, AmslerStatic.class);
                         startActivity(intent);


                     }
         });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {



                //i.putExtra("message", message);
                if (!defect1Check.isChecked() && !defect2Check.isChecked() && !defect3Check.isChecked() && !defect4Check.isChecked())
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DefectList.this);
                    builder.setMessage("Please select atleast one defect!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {

                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                        }
                    });
                    builder.show();
                } else
                {
                    Intent intent = new Intent(DefectList.this, MainActivity.class);

                    if (defect1Check.isChecked())
                        intent.putExtra("defect1", 1);// Blurry

                    if (defect2Check.isChecked())
                        intent.putExtra("defect2", 2);// Wavy/Curvy

                    if (defect3Check.isChecked())
                        intent.putExtra("defect3", 3);// Missing/Faded

                    if (defect4Check.isChecked())
                        intent.putExtra("defect4", 4);// Dark Spot

                    startActivity(intent);

                }




                // your code here
            }
        });


    }

}
