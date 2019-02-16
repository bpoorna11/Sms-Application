package com.balakrishnan.poorna.smsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.balakrishnan.poorna.smsapp.MainActivity;

public class MyRec extends BroadcastReceiver {
    String str1;
    String str2;


    @Override

    public void onReceive(Context context, Intent intent) {

        if(intent.getExtras()!=null){
            Bundle bundle=intent.getExtras();
            Object[] objs=(Object[])bundle.get("pdus");
            for(Object obj:objs){
                byte b[] =(byte[])obj;
                SmsMessage sms=SmsMessage.createFromPdu(b);
                //Log.e("Received",sms.getOriginatingAddress() +"  " +sms.getDisplayMessageBody());
                str1=sms.getOriginatingAddress();
                str2=sms.getDisplayMessageBody();
                MainActivity Sms1=new MainActivity();
                Sms1.receivedSms(str1,str2);
                //Toast.makeText(context,str1,Toast.LENGTH_LONG).show();
            }
        }
    }

}
