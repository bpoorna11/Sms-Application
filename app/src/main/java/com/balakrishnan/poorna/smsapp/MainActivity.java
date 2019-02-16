package com.balakrishnan.poorna.smsapp;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,TextToSpeech.OnInitListener{
TextView tv,tv2,tv3;
EditText ed,ed2;
Button b1,b2;
TextToSpeech tts;
    public static TextView SMS_textview1;
    public static TextView SMS_textview2;
SmsManager smsManager;//=SmsManager.getDefault();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView2);
        tv2=findViewById(R.id.textView);
        tv3=findViewById(R.id.textView3);
        SMS_textview1=findViewById(R.id.textView4);
        SMS_textview2=findViewById(R.id.textView5);
        ed=findViewById(R.id.editText);
        ed2=findViewById(R.id.editText2);

        b1=findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsManager=SmsManager.getDefault();
               // smsManager.sendTextMessage(ed.getText().toString(),null,ed2.getText().toString(),null,null);
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);

                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0,intent,0);
                smsManager.sendTextMessage(ed.getText().toString(),null,ed2.getText().toString(),pi,null);

                if(pi.equals(null))
                    Toast.makeText(getApplicationContext(),"Message not sent",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getApplicationContext(),"Message sent",Toast.LENGTH_LONG).show();

            }
        });
        b2=findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        tts.setSpeechRate((float).5);
                        HashMap<String ,String> hm=new HashMap<>();
                        if(status==TextToSpeech.SUCCESS){
                            tts.speak(SMS_textview2.getText().toString(),TextToSpeech.QUEUE_ADD,hm);
                        }
                        else if(status==TextToSpeech.ERROR){
                            Toast.makeText(getApplicationContext(), "Error in getting sms", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
    public void receivedSms(String phno,String msg){
        try {
            SMS_textview1.setText(phno);
            SMS_textview2.setText(msg);

        }catch (Exception e){

        }
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onInit(int status) {

    }
}
