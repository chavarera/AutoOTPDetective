package com.ravisblogs.myotpdetective;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.telephony.SmsMessage;

/**
 * Created by ravi on 12/4/2017.
 */

public class SMSBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // Create a bundle object to get intent passed from SMS
        Bundle bundle = intent.getExtras();
        SmsMessage[] smsm = null;
        String sms_str ="";

        if (bundle != null)
        {
            // Get the SMS message from intent
            Object[] pdus = (Object[]) bundle.get("pdus");
            smsm = new SmsMessage[pdus.length];
            for (int i=0; i<smsm.length; i++){
                smsm[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                sms_str += smsm[i].getMessageBody().toString();


                String Sender = smsm[i].getOriginatingAddress();

                //Sender Verification here
                  Intent smsIntent = new Intent("otp");
                    smsIntent.putExtra("message",sms_str);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(smsIntent);

            }
        }
    }
}
