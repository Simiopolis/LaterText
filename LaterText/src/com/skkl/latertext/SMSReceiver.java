package com.skkl.latertext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

	String destination, body;

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "Sending your text", Toast.LENGTH_LONG).show();
		Bundle b = intent.getExtras();
		destination = b.getString("destination");
		body = b.getString("body");

		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(destination, null, body, null, null);
		Toast.makeText(context, "Message sent!", Toast.LENGTH_LONG).show();
	}

}
