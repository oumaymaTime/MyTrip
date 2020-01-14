package com.example.stage

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    private val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
    private val TAG = "SmsBroadcastReceiver"
    private var msg =""
    private var phoneNo="+21629586390"

    override fun onReceive(context: Context, intent: Intent) {
        //retrives the general action to be performed and display on Log
        Log.i(TAG, "Intent Received: " +intent.action)
        if(intent.action == SMS_RECEIVED){
            //retrieves a map of extended data from the intent
            var dataBundle = intent.extras
            if(dataBundle != null){
                //creating PDU (protocol data unit) obj which is a protocol for transfering msg
                val mypdu = dataBundle.get("pdus") as Array<Any>
                val messaage = arrayOfNulls<SmsMessage>(mypdu.size)
                for (i in mypdu.indices)
                {
                    // for build version
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        val format = dataBundle.getString("format")
                        messaage[i] = SmsMessage.createFromPdu(mypdu[i] as ByteArray, format)
                    }else{
                        //API level
                        messaage[i] = SmsMessage.createFromPdu(mypdu[i] as ByteArray)
                    }
                    msg = messaage[i]!!.messageBody
                    phoneNo = messaage[i]!!.originatingAddress.toString()
                }
                Toast.makeText(context,"message: "+msg+"\nNumber: " + phoneNo,Toast.LENGTH_LONG).show()
            }
        }
    }
}
