package com.skkl.latertext;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

public class LaterAlarmService extends Service {

	String destination, body;

	@Override
	public IBinder onBind(Intent arg0) {
		Toast.makeText(this, "onBind()", Toast.LENGTH_LONG).show();
		return null;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public boolean stopService(Intent name) {
		return super.stopService(name);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG)
				.show();
		return super.onUnbind(intent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Bundle bundle = intent.getExtras();
		destination = (String) bundle.getCharSequence("extraSmsNumber");
		body = (String) bundle.getCharSequence("extraSmsText");

		Toast.makeText(
				this,
				"LaterAlarmService.onStart() with \n" + "Destination = "
						+ destination + "\n" + "Body = " + body + "\n"
						+ "let's send text", Toast.LENGTH_LONG).show();

		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(destination, null, body, null, null);

		return super.onStartCommand(intent, flags, startId);
	}

}
