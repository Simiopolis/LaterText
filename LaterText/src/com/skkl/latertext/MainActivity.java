package com.skkl.latertext;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.provider.ContactsContract;

public class MainActivity extends Activity {
	
	EditText recipient, message;
	TimePicker time;
	Button submit, contacts;

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
				// send message to BroadCastReceiver
				
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
    
}
