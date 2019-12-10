# Background Process Notes

> [Creating Background Service](https://developer.android.com/training/run-background-service/create-service)  
> [General Background Tutorial](https://developer.android.com/training/best-background)
> [Notification Tutorial](https://developer.android.com/training/notify-user/build-notification)

* NotificationCompat is the API that allows for backwards compatibility to Android 4.0  
	* Create a NotificationCompat.Builder object to create a notification
	* You can set its features, then use a NotificationManagerCompat object to show it
		* Buttons, icons, text, and status can all be set