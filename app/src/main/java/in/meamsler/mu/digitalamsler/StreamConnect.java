package in.meamsler.mu.digitalamsler;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import in.meamsler.mu.digitalamsler.utils.TcpClient;

import static android.view.KeyEvent.ACTION_UP;
import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static java.lang.Math.abs;
import static java.lang.Math.round;

public class StreamConnect extends AppCompatActivity {
    TcpClient mTcpClient;
    Button connect_btn,stop_btn;
    TextView text;
    LinearLayout parent;
    JSONObject coordinate_pack=new JSONObject();
    JSONArray zoneList ;
    int j=0,i=0,length;
    String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream_connect);

        connect_btn = (Button) findViewById(R.id.ConnectButton);
        stop_btn =(Button) findViewById(R.id.stopButton);
        text = (TextView) findViewById(R.id.textView1);
        parent = (LinearLayout) findViewById(R.id.parent);
        text.setTextColor(Color.GREEN);
        text.setText("Haiiiii");




        //HashMap<Coords, String> map = new HashMap<Coords, String>();

        //map.put(new Coords(65, 72), "Dan");

        //map.put(new Coords(68, 78), "Amn");
        //map.put(new Coords(675, 89), "Ann");

        //System.out.println(map.size());




       /*JSONObject jsonObject = new JSONObject();
        List<Integer> points= new ArrayList<>();
        //pairList.add(pair1);




        points.add(12,94);
        points.add(23,84);
        points.add(23,37);
        points.add(10,35);

        try {
           jsonObject.put("MyPoints", points);
        } catch (JSONException e) {
           e.printStackTrace();

        }

        str = jsonObject.toString();
        text.setText(str); */


        connect_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //sends the message to the server
                if (mTcpClient != null) {
                    JSONObject jsonObject = new JSONObject();

                    try {

                        //String msg = new Gson().toJson(zoneList);
                        jsonObject.put("XYLength",length);
                        jsonObject.put("XYPoints", zoneList);
                        text.setText(jsonObject.toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String msg = new Gson().toJson(zoneList);

                    //String msg= obj.toString();
                    mTcpClient.sendMessage(jsonObject.toString());
                }


            }
        });


        new ConnectTask().execute("");


    }


    public boolean onTouchEvent(MotionEvent event) {
        int x = Math.round(event.getX());
        int y = Math.round(event.getY());

        switch (event.getAction()) {
            case ACTION_DOWN:
                i=0;
                zoneList = new JSONArray();
                zoneList.put(x);
                zoneList.put(y);
                i=i+1;
                break;
            case ACTION_MOVE:
                zoneList.put(x);
                zoneList.put(y);
                i=i+1;
                text.setText("Current loc" +Math.round(x)+" and "+Math.round(y));
                break;
            case ACTION_UP:
                length = 2*i;
                break;
        }
        return true;
    }





    public class ConnectTask extends AsyncTask<String, String, TcpClient> {

        @Override
        protected TcpClient doInBackground(String... message) {

            //we create a TCPClient object
            mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    //this method calls the onProgressUpdate
                    publishProgress(message);
                }
            });
            mTcpClient.run();

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //response received from server
            Log.d("test", "response " + values[0]);
            //process server response here....

        }
    }




}



