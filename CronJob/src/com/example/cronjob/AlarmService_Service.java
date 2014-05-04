/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.cronjob;

// Need the following import to get access to the app resources, since this
// class is in a sub-package.

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class AlarmService_Service extends Service {
	long interval = 10*1000;
    @Override
    public void onCreate() {
        /*Thread thr = new Thread(null, mTask, "AlarmService_Service");
        thr.start();*/
        
    	AsyncHttpClient client = new AsyncHttpClient();
    	client.get("http://www.google.com", new AsyncHttpResponseHandler() {
    	    @Override
    	    public void onSuccess(String response) {
    	        System.out.println("response");
    	        long nextInvokationTime=System.currentTimeMillis()+interval;
    			Intent inten=new Intent(AlarmService_Service.this, CronReceiver.class);
    			PendingIntent pendingIntent=PendingIntent.getBroadcast(AlarmService_Service.this, 0, inten,PendingIntent.FLAG_CANCEL_CURRENT);
    			AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);				
    			alarmManager.set(AlarmManager.RTC_WAKEUP, nextInvokationTime, pendingIntent);
    			// Done with our work...  stop the service!
                AlarmService_Service.this.stopSelf();
    	    }
    	    
    	    @Override
		        public void onFailure(Throwable e, String response) {
    	    	e.printStackTrace();
		    	    Log.e("test","saveInvitation failure respose is "+response);
   		    	 long nextInvokationTime=System.currentTimeMillis()+interval;
     			Intent inten=new Intent(AlarmService_Service.this, CronReceiver.class);
     			PendingIntent pendingIntent=PendingIntent.getBroadcast(AlarmService_Service.this, 0, inten,PendingIntent.FLAG_CANCEL_CURRENT);
     			AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);				
     			alarmManager.set(AlarmManager.RTC_WAKEUP, nextInvokationTime, pendingIntent);
     			// Done with our work...  stop the service!
                 AlarmService_Service.this.stopSelf();
    	    }
    	});
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "finished", Toast.LENGTH_SHORT).show();
    }

    /**
     * The function that runs in our worker thread
     */
    Runnable mTask = new Runnable() {
        public void run() {
           

            
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

   
    private final IBinder mBinder = new Binder() {
        @Override
		protected boolean onTransact(int code, Parcel data, Parcel reply,
		        int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };
}

