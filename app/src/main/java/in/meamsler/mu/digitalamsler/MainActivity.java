package in.meamsler.mu.digitalamsler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


import in.meamsler.mu.digitalamsler.utils.*;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {



    MyView mv;
    AlertDialog dialog;
    ArrayList<Integer> zoneList, scoreList ,areaList_1,areaList_2,areaList_3,areaList_4,areaList_5;

    TextView zoneInfo;
    int defect,defect1 , defect2, defect3, defect4;
    int score =0;
    private LinearLayout.LayoutParams params,params2;
    static int total_score;
    private Paint mPaint;
    int ams_score,person_id;
    String date;
    File imagelink;
    String im_name;
    int u_id,scoreSum,x_area_old,y_area_old;

    SharedPreferences sp1;


    //private static final int COLOR_MENU_ID = Menu.FIRST;
    private static final int STROKE_WIDTH_ID = Menu.FIRST + 1;
    private static final int CLEAR_MENU_ID = Menu.FIRST + 2;
    private static final int LOGOUT_ID = Menu.FIRST + 3;



    Button submitButton ;
    Button resetButton ;
    Button AgainButton ;
    Button ExitButton ;


    LinearLayout layout;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        total_score = 0 ;
        x_area_old=999;
        y_area_old=999;

        // for getting values sent from MainActivity1
        //Intent intent = getIntent();
        //int temp = intent.getIntExtra("int_value", 0);
        //int temp = 2;


        try {
            sp1 = this.getSharedPreferences("Login", 0);
            u_id = Integer.parseInt(sp1.getString("USER_ID", null));
            Toast.makeText(this.getBaseContext(), sp1.getString("USER_ID", null), Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {

        }


        //------------

        //myClient = new TCPClient("192.168.43.130", 6666);
        //myClient.execute();



        //mList = (ListView)findViewById(R.id.list);

        // connect to the server



        //---------------


        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundResource(R.drawable.dactvbg);  //Background Image

        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        layout.setLayoutParams(params);

        submitButton = new Button(this);
        resetButton = new Button(this);
        AgainButton = new Button(this);
        ExitButton = new Button(this);



        params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        //submitButton.setGravity(Gravity.CENTER);
        //submitButton.setPadding(0, 75, 0, 0);
        //submitButton.setBackgroundColor(Color.TRANSPARENT);
        params.gravity = Gravity.LEFT;
        params2.gravity = Gravity.RIGHT;

        submitButton.setText("DONE ");
        submitButton.setTextColor(Color.BLACK);
        submitButton.setTypeface(null, Typeface.BOLD);
        submitButton.setTextSize(16);
        submitButton.setGravity(Gravity.CENTER_HORIZONTAL);

        resetButton.setText("RESET");
        resetButton.setTextColor(Color.BLACK);
        resetButton.setTypeface(null, Typeface.BOLD);
        resetButton.setTextSize(16);
        resetButton.setGravity(Gravity.CENTER_HORIZONTAL);


        AgainButton.setText("RETRY");
        AgainButton.setTextColor(Color.BLACK);
        AgainButton.setTypeface(null, Typeface.BOLD);
        AgainButton.setTextSize(16);
        AgainButton.setGravity(Gravity.CENTER_HORIZONTAL);

        ExitButton.setText("EXIT");
        ExitButton.setTextColor(Color.BLACK);
        ExitButton.setTypeface(null, Typeface.BOLD);
        ExitButton.setTextSize(16);
        ExitButton.setGravity(Gravity.CENTER_HORIZONTAL);



        zoneInfo = new TextView(this);
        zoneInfo.setTextColor(Color.GREEN);
        zoneInfo.setTextSize(16);
        zoneInfo.setTypeface(null, Typeface.BOLD);
        zoneInfo.setGravity(Gravity.CENTER_HORIZONTAL);



        //params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);


        //submitButton.setLayoutParams(params);

        mv = new MyView(this);


        params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.MATCH_PARENT);


        mv.setLayoutParams(params);
        mv.setDrawingCacheEnabled(true);
        mv.setAdjustViewBounds(true);

        layout.addView(mv);
        layout.addView(zoneInfo);


         params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
       // params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);


        submitButton.setLayoutParams(params);
        submitButton.setGravity(Gravity.CENTER);
        layout.addView(submitButton);

        resetButton.setLayoutParams(params);
        resetButton.setGravity(Gravity.CENTER);
        layout.addView(resetButton);

        AgainButton.setLayoutParams(params);
        AgainButton.setGravity(Gravity.CENTER);
        layout.addView(AgainButton);
        AgainButton.setVisibility(GONE);

        ExitButton.setLayoutParams(params);
        ExitButton.setGravity(Gravity.CENTER);
        layout.addView(ExitButton);
        ExitButton.setVisibility(GONE);



        //params.gravity= Gravity.CENTER_HORIZONTAL;





        setContentView(layout);

        defect1 = getIntent().getIntExtra("defect1", 0);
        defect2 = getIntent().getIntExtra("defect2", 0);
        defect3 = getIntent().getIntExtra("defect3", 0);
        defect4 = getIntent().getIntExtra("defect4", 0);
        defect = defect1+defect2+defect3+defect4;



        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF0000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(6);

        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                areaList_1=null;
                areaList_2=null;
                areaList_3=null;
                areaList_4=null;
                areaList_5=null;
                zoneList=null;
                zoneInfo.setText("Draw Defects");
                scoreSum=0;
                mv.setDrawingCacheEnabled(false);
                mv.clearScreen();

                // Intent intent = new Intent(getApplicationContext(),DrawScreen.class);
                // startActivity(intent);

            }
        });


        AgainButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(),DefectList.class);
                startActivity(intent);

            }
        });


        ExitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });







        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Add all the scores
                hideButtons();
                int finalScore = 0;

                //for (int i : scoreList) {
                   // finalScore += i;
                //}
                ams_score = scoreSum;
                scoreSum = 0;
                // Get timestamp
                // SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
                // String timeStamp = s.format(new Date());

                long timeStamp = System.currentTimeMillis();

                String fileName = timeStamp + "_" + finalScore;
                im_name=fileName;

                // Check if directory exists
                File dir = new File(MainActivity.this
                        .getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                        + "/MAmsler");

                if (!dir.exists()) {
                    dir = getExternalStorageDirectory();
                }

                if (!mv.isDrawingCacheEnabled())
                    mv.setDrawingCacheEnabled(true);

                Bitmap bitmap = mv.getDrawingCache();

                File file = new File(dir + "/" + fileName + ".png");
                try {
                    if (isExternalStorageWritable()) {
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        FileOutputStream ostream = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 10, ostream);
                        ostream.close();
                        mv.invalidate();
                        Toast.makeText(MainActivity.this,
                                "Image saved as " + fileName + ".png",
                                Toast.LENGTH_SHORT).show();
                        imagelink=file;
                        for (String imageFiles : Utils
                                .getFileList(MainActivity.this)) {
                            Log.i("Files", imageFiles);
                        }

                    } else {
                        GenericAlertDialog
                                .showDialog(MainActivity.this,
                                        "Unable to write to External Storage Directory, Please try again ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    GenericAlertDialog.showDialog(MainActivity.this,
                            "Unable to write to file! ");
                } finally {

                    mv.setDrawingCacheEnabled(false);

                    mv.clearScreen();

                }
                new SendPostMultiRequest().execute();




            }




        });


    }


    public void hideButtons(){
        submitButton.setVisibility(GONE);
        resetButton.setVisibility(GONE);
        AgainButton.setVisibility(VISIBLE);
        ExitButton.setVisibility(VISIBLE);
    }



    public class MyView extends  android.support.v7.widget.AppCompatImageView{

        private Bitmap mBitmap, originalBitmap, originalNonResizedBitmap;
        private Canvas mCanvas;
        private Path mPath;
        private Paint mBitmapPaint;
        Context context;
        private BitmapFactory.Options options = new BitmapFactory.Options();

        @SuppressLint("NewApi") public MyView(Context c) {
            super(c);
            context = c;
            mPath = new Path();
            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
            options.inMutable = true;

            scoreList = new ArrayList<Integer>();

            originalNonResizedBitmap = Bitmap.createBitmap(BitmapFactory
                    .decodeResource(getResources(), R.drawable.amslergrid,
                            options));

            originalNonResizedBitmap = getResizedBitmap(originalNonResizedBitmap);

            originalBitmap = originalNonResizedBitmap.copy(
                    Bitmap.Config.ARGB_8888, true);
            mBitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);

            mCanvas = new Canvas(mBitmap);

        }

        public MyView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

            mCanvas = new Canvas(mBitmap);

        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);

            canvas.drawPath(mPath, mPaint);

        }

        private float mX, mY;
        private static final float TOUCH_TOLERANCE = 2;

        private void touch_start(float x, float y) {

            mPath.reset();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;

        }

        private void touch_move(float x, float y) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
        }

        private void touch_up() {



            mPath.lineTo(mX, mY);
            // commit the path to our offscreen
            mCanvas.drawPath(mPath, mPaint);
            // kill this so we don't double draw
            mPath.reset();
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SCREEN));
            // mPaint.setMaskFilter(null);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touch_start(x, y);
                    areaList_1 = new ArrayList<Integer>();
                    areaList_2 = new ArrayList<Integer>();
                    areaList_3 = new ArrayList<Integer>();
                    areaList_4 = new ArrayList<Integer>();
                    areaList_5 = new ArrayList<Integer>();
                    zoneList   = new ArrayList<Integer>();
                    zoneList.add(getZoneLocation((int) x, (int) y));
                    getArea((int)x,(int)y);

                    //zoneInfo.setText("Current Score is " + score);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:

                    touch_move(x, y);
                    zoneList.add(getZoneLocation((int) x, (int) y));
                    getArea((int)x,(int)y);
                    x= Math.round(x);
                    y=Math.round(y);
                    //myClient.sendMessage(x+"@"+y);
                    zoneInfo.setText("Current loc" +Math.abs(x)+" and "+Math.abs(y));
                    invalidate();
                    break;


                case MotionEvent.ACTION_UP:
                    touch_up();
                   // zoneInfo.setText(areaList.toString());
                    int sum = 0;
                    int f1=1,f2=1,f3=1,f4=1,f5=1;
                    for (int i=0;i<zoneList.size();i++) {
                        if(zoneList.get(i)==1 && f1==1)
                        {
                            sum += (areaList_1.size()*areaList_1.size())*defect*zoneList.get(i);
                            f1=0;
                            continue;
                        }
                        else if(zoneList.get(i)==2 && f2==1)
                        {
                            sum += (areaList_2.size()*areaList_2.size())*defect*zoneList.get(i);
                            f2=0;
                            continue;
                        }
                        else if(zoneList.get(i)==3 && f3==1)
                        {
                            sum += (areaList_3.size()*areaList_3.size())*defect*zoneList.get(i);
                            f3=0;
                            continue;
                        }
                        else if(zoneList.get(i)==4 && f4==1)
                        {
                            sum += (areaList_4.size()*areaList_4.size())*defect*zoneList.get(i);
                            f4=0;
                            continue;
                        }
                        else if(zoneList.get(i)==5 && f5==1)
                        {
                            sum += (areaList_5.size()*areaList_5.size())*defect*zoneList.get(i);
                            f5=0;
                            //zoneInfo.setText("Ar="+areaList_5.size()+" X def="+defect+ "X zone="+zoneList.get(i));
                            continue;
                        }


                    }
                    scoreSum+=sum;
                    //Old Scoring
                    /*
                    int avg = Math.round((float) sum / zoneList.size());

                    score = (defect1 + defect2 + defect3 + defect4)
                            * avg
                            * ((defect1 * avg) + (defect2 * avg) + (defect3 * avg) + (defect4 * avg))
                            + 1;

                    scoreList.add(score);

                    int scoreSum = 0;
                    for (int i : scoreList) {
                        scoreSum += i;
                    }
                    */
                    zoneInfo.setText("Current Score " + scoreSum );
                    //zoneInfo.setText("Area 1=" + areaList_1.size()+"--2="+areaList_2.size()+"--3="+areaList_3.size()+"--4="+areaList_4.size()+"--5="+areaList_5.size() );
                    mPaint.setXfermode(null);
                    invalidate();

                    break;
            }
            return true;
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            int newWidthMeasure = MeasureSpec.makeMeasureSpec(
                    mBitmap.getWidth(), MeasureSpec.EXACTLY);
            int newHeightMeasure = MeasureSpec.makeMeasureSpec(
                    mBitmap.getHeight(), MeasureSpec.EXACTLY);

            setMeasuredDimension(newWidthMeasure, newHeightMeasure);

        }

        public void clearScreen() {
            mCanvas.drawColor(Color.TRANSPARENT);
            mCanvas.drawBitmap(originalBitmap, 0, 0, mBitmapPaint);
            scoreList.clear();
            score = 0;
            zoneInfo.setText("Current Score is " + score);
            invalidate();
        }








        public void getArea(int x,int y){
            //areaList.add(x);
            //areaList.add(y);
            int x_key = Math.round((x*100/(mBitmap.getWidth()))/10);
            int y_key = Math.round((y*100/(mBitmap.getHeight()))/10);

            if(x_area_old == x_key && y_area_old == y_key )
            {
                return;
            }
            else
            {
                x_area_old = x_key;
                y_area_old = y_key;
                //code for finding rect
                int zone = getZoneLocation(x,y);
                switch (zone){
                    case 1:areaList_1.add(zone);
                        break;
                    case 2:areaList_2.add(zone);
                        break;
                    case 3:areaList_3.add(zone);
                        break;
                    case 4:areaList_4.add(zone);
                        break;
                    case 5:areaList_5.add(zone);
                        break;
                    default:
            }
            }
            return;


        }


        public Rect getCoordinateRect() {
            return new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight());
        }

        int getZoneLocation(int x, int y) {

            Rect rectangle = new Rect();

            rectangle = getCoordinateRect();

            // Zone 1 Rectangle

            Rect outerMostRect = new Rect(rectangle);

            int xOffset = rectangle.width() / 10;
            int yOffset = rectangle.height() / 10;

            // Log.i("Rectangle Attribs", "Width: " + xOffset + "Height: " +
            // yOffset);

            // Zone 2 Rectangle

            Rect zone2Rectangle = new Rect(outerMostRect.left + xOffset,
                    outerMostRect.top + yOffset, outerMostRect.right - xOffset,
                    outerMostRect.bottom - yOffset);

            // Log.i("Zone 2 Coordinates", "" + zone2Rectangle.left + " " +
            // zone2Rectangle.top + " " + zone2Rectangle.right + " "
            // + zone2Rectangle.bottom);

            // Zone 3 Rectangle

            Rect zone3Rectangle = new Rect(zone2Rectangle.left + xOffset,
                    zone2Rectangle.top + yOffset, zone2Rectangle.right
                    - xOffset, zone2Rectangle.bottom - yOffset);

            // Zone 4 Rectangle

            Rect zone4Rectangle = new Rect(zone3Rectangle.left + xOffset,
                    zone3Rectangle.top + yOffset, zone3Rectangle.right
                    - xOffset, zone3Rectangle.bottom - yOffset);

            // Zone 5 Rectangle
            Rect zone5Rectangle = new Rect(zone4Rectangle.left + xOffset,
                    zone4Rectangle.top + yOffset, zone4Rectangle.right
                    - xOffset, zone4Rectangle.bottom - yOffset);

            // Check from inside out for point existence
            if (zone5Rectangle.contains(x, y)) {
                return 5;
            } else if (zone4Rectangle.contains(x, y)) {
                return 4;
            } else if (zone3Rectangle.contains(x, y)) {
                return 3;
            } else if (zone2Rectangle.contains(x, y)) {
                return 2;
            } else if (outerMostRect.contains(x, y)) {
                return 1;
            }
            return -1;
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm) {
        int width = bm.getWidth();
        int height = bm.getHeight();

        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int newDimen = size.x;

        float scaleWidth = ((float) newDimen) / width;
        float scaleHeight = ((float) newDimen) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);
        return resizedBitmap;

    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getExternalStorageDirectory() {
        File file = new File(
                this.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                "MAmsler");
        if (!file.mkdirs())
            return null;
        else
            return file;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // menu.add(0, COLOR_MENU_ID, 0, "Color");
        //menu.add(0, STROKE_WIDTH_ID, 0, "Stroke Width");
        menu.add(0, CLEAR_MENU_ID, 0, "Clear");
        menu.add(0, LOGOUT_ID, 0, "Logout");

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case LOGOUT_ID:
                SharedPreferences sp=getSharedPreferences("Login", 0);
                sp.edit().clear().commit();
                return true;

            case CLEAR_MENU_ID:
                mv.clearScreen();
                return true;

            /*case STROKE_WIDTH_ID:
                new StrokeSelectorDialog(this, this, mPaint.getStrokeWidth())
                        .show();
                return true;*/

        }return super.onOptionsItemSelected(item);
    }



    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("https://anuragdattaroy.pythonanywhere.com/ams_api/"); // here is your URL path





                person_id= u_id;
                date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

                JSONObject postDataParams = new JSONObject();
                //postDataParams.put("verify_date", "2017-03-31");
                postDataParams.put("verify_date", date);
                postDataParams.put("amsler_score", ams_score);
                postDataParams.put("status", "u");
                postDataParams.put("patient", person_id);

                Log.e("params",postDataParams.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == 201) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result,
                    Toast.LENGTH_LONG).show();
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

    public class SendPostMultiRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... params) {

            HttpURLConnection conn = null;
            DataOutputStream dos = null;
            String lineEnd = "\r\n";
            String twoHyphens = "--";
            String boundary = "*****";
            int bytesRead, bytesAvailable, bufferSize;
            byte[] buffer;
            int maxBufferSize = 1 * 1024 * 1024;
            int serverResponseCode = 0;


            //parameters
            //String urldisplay = params[0];
            //String email = params[1];
            //String personName = params[2];
            //String loginMethod = params[3];
            Bitmap mIcon11 = null;
            try {
                person_id=u_id;
            }
            catch (Exception e)
            {

            }

            date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            try {

            //    System.setProperty("http.keepAlive", "false");

                /////////////////////////////////////////////
                ////UPLOADING PICTURE AND DATA

                //download picture from the source
               // InputStream in = new URL(urldisplay).openStream();
                //mIcon11 = BitmapFactory.decodeStream(in);

                //encoding image into a byte array
              //  ByteArrayOutputStream baos = new ByteArrayOutputStream();
              //  mIcon11.compress(Bitmap.CompressFormat.JPEG, 100, baos);
              //  byte[] imageBytes = baos.toByteArray();

                // open a URL connection to the Servlet
                //ByteArrayInputStream fileInputStream = new ByteArrayInputStream(imageBytes);
                FileInputStream fileInputStream = new FileInputStream(imagelink);

                URL url = new URL("https://anuragdattaroy.pythonanywhere.com/ams_api/");

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs

                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Accept-Encoding", "");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", im_name);


                dos = new DataOutputStream(conn.getOutputStream());

                //first parameter - email
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"verify_date\"" + lineEnd + lineEnd
                        + date + lineEnd);

                //second parameter - userName
                //String test = personName.getBytes("UTF-8").toString();
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"amsler_score\"" + lineEnd + lineEnd
                        + ams_score + lineEnd);

                //third parameter - password
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"status\"" + lineEnd + lineEnd
                        + "u" + lineEnd);

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"patient\"" + lineEnd + lineEnd
                        + person_id + lineEnd);

                //fifth parameter - filename
                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"grid1\";filename=\""
                        + im_name + "\"" + lineEnd);
                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {
                    try {
                        dos.write(buffer, 0, bufferSize);
                    }
                    catch (OutOfMemoryError e) {
                        Toast.makeText(MainActivity.this, "Insufficient Memory!", Toast.LENGTH_SHORT).show();
                    }
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                try {
                    serverResponseCode = conn.getResponseCode();
                }
                catch (OutOfMemoryError e) {
                    Toast.makeText(MainActivity.this, "Memory Insufficient!", Toast.LENGTH_SHORT).show();
                }
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                //close the streams //
                //fileInputStream.close();
                dos.flush();
                dos.close();
                conn.disconnect();
            } catch (MalformedURLException ex) {
                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {
                Log.e("The caught exception is", e.getMessage());
            }
            return null;
        }
    }
}


