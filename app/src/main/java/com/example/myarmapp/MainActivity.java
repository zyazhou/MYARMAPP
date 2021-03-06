package com.example.myarmapp;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.util.Log;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import com.friendlyarm.AndroidSDK.MyRequest;
import com.google.android.material.shadow.ShadowRenderer;
import com.friendlyarm.AndroidSDK.HardwareControler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import org.achartengine.ChartFactory;

public class MainActivity extends AppCompatActivity {
    private int PWMstate = 0,f=0;
    private int LEDstate = 0;
    private ConstraintLayout CurveLayout;
    private GraphicalView mView;
    private ChartService mService;
    private Timer timer;
    private int AdcValue = 0;
    private int t = 0,i=0,j=0;
    private  TextView ADCview;


//    private void run() {
//        MyRequest request = new MyRequest();
//        String data = "username=root&password=12345";//POST???????????????
//        String res = request.post("http://10.102.117.157:5000/text", data);//??????????????????post??????
//        Log.d("TAG", res);
//        Log.d("TAG", getNumeric(res));
//        i=Integer.parseInt(getNumeric(res));
//        Log.d("TAG", getNumeric(res));
//        if(i>0&&i<=10000)
//        {
//            Button PWMbtn = (Button) findViewById(R.id.button);
//            EditText freqInput =(EditText) findViewById (R.id.textInputEditText);
//            int freq=i;
//            if (freq>100000) {
//                Toast.makeText(MainActivity.this,"???????????????10000?????????",Toast.LENGTH_SHORT).show();
//            } else{
//                HardwareControler.PWMPlay(freq);
//            }
//            PWMstate = 1;
//
//        }
//    }
    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            AdcValue = HardwareControler.readADC();
            AdcValue = AdcValue * 1800 / 4096;
            mService.updateChart(t, AdcValue);
            t += 5;


            //Log.d("TAG","ssss");
            return false;
        }
    });
//    private Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            AdcValue = HardwareControler.readADC();
//            AdcValue = AdcValue * 1800 / 4096;
//            mService.updateChart(t, AdcValue);
//            t += 5;
//        }
//
//    };

//    public void run_net() throws InterruptedException {
//        Thread.sleep(5000);
//        MyRequest request = new MyRequest();
//        Log.d("TAG","ssss");
//            //String data = "username=root&password=12345";//POST???????????????
//        String data = "pwm="+PWMstate+"&fre="+f+"&led="+LEDstate;//POST???????????????
//        String resled = request.post("http://10.102.117.157:5000/client_led", data);//??????????????????post??????
//        Log.d("TAG", resled);
//        i=Integer.parseInt(getNumeric(resled));
//        String respwm = request.post("http://10.102.117.157:5000/client_pwm", data);//??????????????????post??????
//        Log.d("TAG", respwm);
//        j=Integer.parseInt(getNumeric(respwm));
//        if(i==1){       //LED???
//            HardwareControler.setLedState(1,1);
//            LEDstate = 1;
//            Toast.makeText(MainActivity.this,"Opps,Open!",Toast.LENGTH_SHORT).show();
//        }
//        else{
//            HardwareControler.setLedState(1,0);
//            LEDstate = 0;
//            Toast.makeText(MainActivity.this,"Opps,Close!",Toast.LENGTH_SHORT).show();
//        }
//
//        if(j>0&&j<10000){
//            EditText freqInput =(EditText) findViewById (R.id.textInputEditText);
//
//            int freq=j;
//            f=freq;
//            if (freq>10000) {
//                Toast.makeText(MainActivity.this,"???????????????10000?????????",Toast.LENGTH_SHORT).show();
//            } else{
//                HardwareControler.PWMPlay(freq);
//                Toast.makeText(MainActivity.this,"????????????",Toast.LENGTH_SHORT).show();
//            }
//            PWMstate = 1;
//        }
//        else {
//            HardwareControler.PWMStop();
//            PWMstate = 0;
//        }
//    }


   // handler.handleMessage();
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CurveLayout = (ConstraintLayout) findViewById(R.id.left_temperature_curve);
//        mService = new ChartService(this);
//        mService.setXYMultipleSeriesDataset("ADC??????");
//        mService.setXYMultipleSeriesRenderer(100, 180, "ADC??????", "??????", "??????",
//                Color.BLUE, Color.BLUE, Color.RED, Color.WHITE);
//        mView = mService.getGraphicalView();
//        CurveLayout.addView(mView, new LayoutParams(
//                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            public void run() {
//                handler.sendMessage(handler.obtainMessage());
//            }
//        }, 20, 2000);
    }

    public void btnOnClick(View v) throws IOException {
        Button PWMbtn = (Button) findViewById(R.id.button);
        if(PWMstate==0){
            EditText freqInput =(EditText) findViewById (R.id.textInputEditText);
            int freq=Integer.parseInt(freqInput.getText().toString());
            f=freq;
            if (freq>10000) {
                Toast.makeText(MainActivity.this,"???????????????10000?????????",Toast.LENGTH_SHORT).show();
            } else{
                HardwareControler.PWMPlay(freq);
                Toast.makeText(MainActivity.this,"????????????",Toast.LENGTH_SHORT).show();
            }
            PWMbtn.setText("??????");
            PWMstate = 1;
        }
        else {
            HardwareControler.PWMStop();
            PWMbtn.setText("??????");
            PWMstate = 0;
        }

        new Thread() {//???????????????????????????????????????
            @Override
            public void run() {
                MyRequest request = new MyRequest();
                //String data = "username=root&password=12345";//POST???????????????
                String data = "pwm="+PWMstate+"&fre="+f+"&led="+LEDstate;//POST???????????????
                String res = request.post("http://10.101.163.58:5000/text", data);//??????????????????post??????
                Log.d("TAG", res);
            }
        }.start();
    }

    public void mybtnOnClick(View v)  {
        int adc = HardwareControler.readADC();
        String s = String.valueOf(adc);
        ADCview=findViewById(R.id.textView3);
        ADCview.setText(s);
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
//
        new Thread() {//???????????????????????????????????????
            Button PWMbtn = (Button) findViewById(R.id.button);
            @Override
            public void run() {
                MyRequest request = new MyRequest();
                Log.d("TAG","mybtnOnClick");
                String data = "pwm="+PWMstate+"&fre="+f+"&led="+LEDstate;//POST???????????????
                String resled = request.post("http://10.101.163.58:5000/client_led", data);//????????????post??????
                Log.d("TAG", resled);
                j=Integer.parseInt(getNumeric(resled));
                String jjj=Integer.toString(j);

                Log.d("TAG", jjj);
                if(j%10==0)
                {
                    j=j/10;
                    i=0;
                }
                else{
                    i=1;
                    j=(j-1)/10;
                }
                if(j==100)
                    Log.d("TAG", "j=100");
                if(i==1)
                    Log.d("TAG", "i=1");
                //String data2 = "led="+LEDstate;//POST???????????????
                //String respwm = request.post("http://10.102.117.157:5000/client_pwm", data2);//??????????????????post??????
                //Log.d("TAG", respwm);
               // j=Integer.parseInt(getNumeric(respwm));
                if(i==1){       //LED???
                    HardwareControler.setLedState(1,1);
                    LEDstate = 1;
                    //Toast.makeText(MainActivity.this,"Opps,Open!",Toast.LENGTH_SHORT).show();
                }
                else{
                    HardwareControler.setLedState(1,0);
                    LEDstate = 0;
                    //Toast.makeText(MainActivity.this,"Opps,Close!",Toast.LENGTH_SHORT).show();
                }

                if(j>0&&j<10000){
                    EditText freqInput =(EditText) findViewById (R.id.textInputEditText);

                    int freq=j;
                    f=freq;
                    if (freq>10000) {
                        Toast.makeText(MainActivity.this,"???????????????10000?????????",Toast.LENGTH_SHORT).show();
                    } else{
                        HardwareControler.PWMPlay(freq);
                        //Toast.makeText(MainActivity.this,"????????????",Toast.LENGTH_SHORT).show();
                    }
                    PWMstate = 1;
                }
                else {
                    HardwareControler.PWMStop();
                    PWMstate = 0;
                }
            }
        }.start();

    }



    public void ledController(View v){
        if(LEDstate==0){
            HardwareControler.setLedState(1,1);
            LEDstate = 1;
            Toast.makeText(MainActivity.this,"Opps,Open!",Toast.LENGTH_SHORT).show();
        }else{
            HardwareControler.setLedState(1,0);
            LEDstate = 0;
            Toast.makeText(MainActivity.this,"Opps,Close!",Toast.LENGTH_SHORT).show();
        }
        new Thread() {//???????????????????????????????????????
            @Override
            public void run() {
                MyRequest request = new MyRequest();
                //String data = "username=root&password=12345";//POST???????????????
                String data = "pwm="+PWMstate+"&fre="+f+"&led="+LEDstate;//POST???????????????
                Log.d("TAG", data);
                String res = request.post("http://10.101.163.58:5000/text", data);//??????????????????post??????
                Log.d("TAG", res);
            }
        }.start();

//        new Thread() {//???????????????????????????????????????
//            @Override
//            public void run() {
//                MyRequest request = new MyRequest();
//                String data = "username=root&password=12345";//POST???????????????
//                String res = request.post("http://10.102.117.157:5000/text", data);//??????????????????post??????
//                Log.d("TAG", res);
//                Log.d("TAG", getNumeric(res));
//                i=Integer.parseInt(getNumeric(res));
//                Log.d("TAG", getNumeric(res));
//                if(i>0&&i<=10000)
//                {
//                    Button PWMbtn = (Button) findViewById(R.id.button);
//                    EditText freqInput =(EditText) findViewById (R.id.textInputEditText);
//                    int freq=i;
//                    if (freq>100000) {
//                        Toast.makeText(MainActivity.this,"???????????????10000?????????",Toast.LENGTH_SHORT).show();
//                    } else{
//                        HardwareControler.PWMPlay(freq);
//                    }
//                    PWMstate = 1;
//
//                }
//            }
//        }.start();
    }
    public static String getNumeric(String str) {
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
    private void networdRequest()  {
        //???????????????Log.d("tt", "jinjin");
        HttpURLConnection connection=null;

        try {
            URL url = new URL("http://10.101.163.58:5000/t");//??????
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            //?????????????????? GET / POST ???????????????

            connection.setRequestMethod("POST");
            Log.d("t", "1");
            connection.setDoInput(true);
            connection.setDoOutput(false);
            Log.d("t", "2");
            connection.connect();  //??????
            Log.d("t", "3");


            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code" + responseCode);
            }
            String result = getStringByStream(connection.getInputStream());
            if (result == null) {
                Log.d("Fail", "?????????");
            }else{
                Log.d("succuss", "????????? ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private String getStringByStream(InputStream inputStream){
        Reader reader;
        try {
            reader=new InputStreamReader(inputStream,"UTF-8");
            char[] rawBuffer=new char[512];
            StringBuffer buffer=new StringBuffer();
            int length;
            while ((length=reader.read(rawBuffer))!=-1){
                buffer.append(rawBuffer,0,length);
            }
            return buffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



}
