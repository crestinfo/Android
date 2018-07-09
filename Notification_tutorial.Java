Step - 1 CREATE NOTIIFCATION

	NotificationCompact.Builder notifBuilder = New NotificationCompact(this);


Step - 2  SETTING NOTIICATION PROPERTIES 

	Once you have the Builder Object then you can set its properties but in this case you need to mandatory set the following properties

	notifBuilder.setSmallIcon(R.drawable.notification_icon);
	notifBuilder.setContentTitle("Notificaiton Alert, Click Me !");
	notifBuilder.setContentText("Hey this is an Android Notificaiton Detail.");

	You have planty of other Notification properties you can learn from Refearance Document NotificationCompact.Builder


Step - 3 ATTECH ACTION

	This is an optional part and required if you want to attach an action with the notification.An action allows users to
	go directly from the notification to an Activity in your application, where they can look at one or more events or do
	further work.

	The action is defined by a PendingIntent containing an Intent that starts an Activity in your application

	We take help of stack builder object which will contain an artificial back stack for the started Activity. This ensures
	that navigating backward from the Activity leads out of your application to the Home screen.

	Intent resultIntent =  new Intent(This, ResultActivity.class)
	TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
	stackBuilder.addParentStack(ResultActivity.class);
	// Adds the Intent that starts the Activity to the top of the stack
	stackBuilder.addNextIntent(resultIntent);
	PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT );
	mBuilder.setContentIntent(resultPendingIntent);


Step - 4 ISSUE THE NOTIICATION

	Finally, you pass the Notification object to the system by calling NotificationManager.notify() to send your
	notification. Make sure you call NotificationCompat.Builder.build() method on builder object before notifying it.
	This method combines all of the options that have been set and return a new Notificationobject.

	
	NotificationManager mNotificationManager =  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	// notificationID allows you to update the notification later on.
	mNotificationManager.notify(notificationID, mBuilder.build());


ACTIONS ON NOTIIFICAITON (Buttons like Accept/Reject/Apply/Archive etc. . . )


	You first need to set addAction() method to add some action on the notifBuilder Object 

	- - - - - - - - - - - - - - -
	//Accept intent
	Intent acceptReceive = new Intent();  
	acceptReceive.setAction(ACCEPT_ACTION);
	PendingIntent pendingIntentAaccept = PendingIntent.getBroadcast(this, 12345, acceptReceive, PendingIntent.FLAG_UPDATE_CURRENT);
	mBuilder.addAction(R.drawable.accept_icon, "Accept", pendingIntentAaccept);

	//Deny intent
	Intent denyReceive = new Intent();  
	denyReceive.setAction(DENY_ACTION);
	PendingIntent pendingIntentDeny = PendingIntent.getBroadcast(this, 12345, denyReceive, PendingIntent.FLAG_UPDATE_CURRENT);
	mBuilder.addAction(R.drawable.deny_icon, "Deny", pendingIntentDeny);
	
	- - - - - - - - - - - - - - -
	You can get the click action on the Broadcast Listener Click evet

	@Override
	public void onReceive(Context context, Intent intent) {
	    String action = intent.getAction();	
	    if(ACCEPT_ACTION.equals(action)) {
	        Log.v("testAction","Pressed Accept");
	    } else if(DENY_ACTION.equals(action)) {
	        Log.v("testAction","Pressed Deny");
	    } 
	}  	
	