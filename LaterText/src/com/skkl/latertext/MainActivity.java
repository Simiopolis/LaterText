package com.skkl.latertext;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	EditText recipient, message;
	TimePicker time;
	Button submit, contacts;
	Calendar calendar;
	String destination, body;
	private PendingIntent serviceIntent;
	private PendingIntent pendingIntent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_text);
        
        recipient = (EditText)findViewById(R.id.recipient);
        message = (EditText)findViewById(R.id.message);
        time = (TimePicker)findViewById(R.id.time);
        submit = (Button)findViewById(R.id.submit);
        contacts = (Button)findViewById(R.id.contacts);
        
        submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// new intent
				Intent smsReceiverIntent = new Intent(getApplicationContext(),
						SMSReceiver.class);

				// send message to BroadCastReceiver
				destination = recipient.getText().toString();
				body = message.getText().toString();
				calendar = Calendar.getInstance();

				// set up alarm manager for time delay
				AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

				// get times for differences
				int desire_hour = time.getCurrentHour();
				int desire_min = time.getCurrentMinute();
				Calendar desired_calendar = makeCalendar(desire_hour,
						desire_min);
				Date desired = desired_calendar.getTime();
				Date now = calendar.getTime();

				// check if desire time is grater than now.
				if (desired.after(now)) {
					// passing case
					Bundle b = new Bundle();
					b.putCharSequence("destination", destination);
					b.putCharSequence("body", body);
					smsReceiverIntent.putExtras(b);

					// I am not sure exactly that this does
					serviceIntent = PendingIntent.getBroadcast(
							MainActivity.this, 0,
							smsReceiverIntent,
							PendingIntent.FLAG_UPDATE_CURRENT);

					alarmManager.set(AlarmManager.RTC_WAKEUP,
							desired_calendar.getTimeInMillis() - 10000,
							serviceIntent);

					Toast.makeText(
							getApplicationContext(),
							"Start service with \n Number = " + destination
									+ "\n Body = " + body + "\n accepted",
							Toast.LENGTH_LONG).show();
				} else {
					// failure case
					Toast.makeText(getBaseContext(),
							"Invalid time! Please enter valid time.",
							Toast.LENGTH_LONG).show();
				}

			}
		});
        
        contacts.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Android query for contacts
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	private Calendar makeCalendar(int hour, int min) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, hour);
		c.set(Calendar.MINUTE, min);
		return c;
	}

}
