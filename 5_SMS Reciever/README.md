# Broadcast Reciever
**Broadcast Receivers** simply respond to broadcast messages from other applications or from the system itself. These messages are sometime called events or intents. For example, applications can also initiate broadcasts to let other applications know that some data has been downloaded to the device and is available for them to use, so this is broadcast receiver who will intercept this communication and will initiate appropriate action.

## Problem Statement
Create a program to receive the incoming SMS to the phone and put a notification on screen, on clicking the notification it must display sender number and message content on screen.

## Screenshots
![Alt text](screenshots/5a.png?raw=true)
![Alt text](screenshots/5b.png?raw=true)
![Alt text](screenshots/5c.png?raw=true)

## Setup
1. Create a New Android Project by the name **SMS Reciever** and resulting package name **com.bharathksunilk.smsreceiver**
2. Let the App have a Launcher Activity, copy contents of [activity_main.xml](xml/activity_main.xml) and [MainActivity.java](java/com/bharathksunilk/smsreceiver/MainActivity.java)
3. Create a java class [MySMSReceiver.java](java/com/bharathksunilk/smsreceiver/MySMSReceiver.java)and extend BroadcastReciever and implement the methods.
4. Update the [AndroidManifest.xml](xml/AndroidManifest.xml):
```
<receiver android:name=".MySMSReceiver">
	<intent-filter>
		<action android:name="android.provider.Telephony.SMS_RECEIVED"/>
	</intent-filter>
</receiver>
```

## Run
- Install and run the app once.
- If testing on a real phone send an sms to self
- If testing on an emulator:
  - **Telnet**
    - Open console/command prompt.
	- Type ```telnet localhost <port>``` Replace <port> with the emulator port(can be found on top of the emulator or in the logcat)
	- Usually the port is 5554
  - **DDMS on Eclipse IDE**
    - Goto DDMS on the top right of Eclipse IDE
	- Select your emulator on the left pane and then in the right pane you will get Emulator Control->Telephony action.
	- Type the Incoming Number and the message and hit send.
  - **On Android Studio**
    - Click the ... button on the emulator options to open Extended Controls
	- Click on the Phone option and change the Phone No and the Message and click send 
- You will recieve the sms in your emulator and a notification will be displayed.
- Click on the notification to see the message on our activity.