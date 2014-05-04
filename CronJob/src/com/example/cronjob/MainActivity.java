package com.example.cronjob;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	SharedPreferences pref ;
	Editor editor ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
		editor = pref.edit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startCron(View view)
	{
		long invocationTime = System.currentTimeMillis()+2000;
		editor.putLong("nextInvokationTime",invocationTime); // Storing long
		editor.commit(); // commit changes
		
		Intent intent=new Intent(this, CronReceiver.class);
		PendingIntent pendingIntent=PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarmMngr=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
		alarmMngr.set(AlarmManager.RTC_WAKEUP, invocationTime, pendingIntent);
	}
}
