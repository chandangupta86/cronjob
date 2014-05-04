package com.example.cronjob;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CronReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		Intent i = new Intent(context, AlarmService_Service.class);
		context.startService(i);
		
			/*long nextInvokationTime=System.currentTimeMillis()+interval;
			Intent inten=new Intent(context, CronReceiver.class);
			pendingIntent=PendingIntent.getBroadcast(context, 0, inten,PendingIntent.FLAG_CANCEL_CURRENT);
			AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);				
			alarmManager.set(AlarmManager.RTC_WAKEUP, nextInvokationTime, pendingIntent);*/
				
	}
		
}
