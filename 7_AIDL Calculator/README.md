# Android Interface Definition Language (AIDL)
AIDL (Android Interface Definition Language) is similar to other IDLs you might have worked with. It allows you to define the programming interface that both the client and service agree upon in order to communicate with each other using interprocess communication (IPC). On Android, one process cannot normally access the memory of another process. So to talk, they need to decompose their objects into primitives that the operating system can understand, and marshall the objects across that boundary for you. The code to do that marshalling is tedious to write, so Android handles it for you with AIDL.

## Problem Statement
Create an .aidl service to do add, subtraction and multiplication and create another application with two buttons to read the inputs and three button add,subtract and multiply to call add,subtract and multiply operation on .aidl service.

## Screenshots
### My Calculator
![Alt text](screenshots/7a.png?raw=true)
![Alt text](screenshots/7b.png?raw=true)
![Alt text](screenshots/7c.png?raw=true)
![Alt text](screenshots/7d.png?raw=true)

## Setup
There are 2 Apps required to demonstrate Remote AIDL service implementation
1. **AIDL Service:** which has only one service to which our Calculator app shall connect.
2. **My Calculator:** which has an activity and will try to connect to the remote service of AIDL Service app.

### AIDL Service
1. Create a New Android Project by the name **AIDL Service** and resulting package name **com.bharathksunilk.aidlservice**
2. Let the App have an Empty Launcher Activity with no layout like [MainActivity.java](java/com/bharathksunilk/aidlservice/MainActivity.java)
3. Create a java class extending Service [MyCalService.java](java/com/bharathksunilk/aidlservice/MyCalService.java), this is our remote service.
4. Add the following in the [AndroidManifest.xml](xml/AIDLService/AndroidManifest.xml):
```
<service android:name=".MyCalService">
	<intent-filter>
		<action android:name="com.bharathksunilk.aidlservice.service.calculate" />
	</intent-filter>
</service>
```
5. Lets create an .aild file: (methods are different for **Eclipse IDE** and **Android Studio**)
   - **Eclipse IDE**
     - Create a New > File in the package **com.bharathksunilk.aidlservice** and name it [IMyAidlInterface.aidl](aidl/com/bharathksunilk/aidlservice/IMyAidlInterface.aidl).
	 - Write the interface in it as provided in IMyAidlInterface.aidl .
   - **Android Studio**
     - Goto File > New > AIDL > AIDL File and name it [IMyAidlInterface.aidl](aidl/com/bharathksunilk/aidlservice/IMyAidlInterface.aidl).
	 - Write the interface in it as provided in IMyAidlInterface.aidl .
	 - **NOTE:** In android studio the aidl file is created in a separate directory **aidl/**

#### My Calculator
1. Create a New Android Project by the name **My Calculator** and resulting package name **com.bharathksunilk.mycalculator**
2. Let the App have a Launcher Activity, copy contents of [activity_main.xml](xml/MyCalculator/activity_main.xml) and [MainActivity.java](java/com/bharathksunilk/mycalculator/MainActivity.java)
3. Create the .aidl file: (methods are different for **Eclipse IDE** and **Android Studio**)
   - **Eclipse IDE**
     - Create a New > Package **com.bharathksunilk.aidlservice**
     - Create a New > File in the package **com.bharathksunilk.aidlservice** and name it [IMyAidlInterface.aidl](aidl/com/bharathksunilk/aidlservice/IMyAidlInterface.aidl).
	 - Write the interface in it as provided in IMyAidlInterface.aidl .
   - **Android Studio**
     - Goto File > New > AIDL > AIDL File and name it [IMyAidlInterface.aidl](aidl/com/bharathksunilk/aidlservice/IMyAidlInterface.aidl).
	 - Write the interface in it as provided in IMyAidlInterface.aidl .
	 - **IMPORTANT** Create a New > Package : **com.bharathksunilk.aidlservice**
	 - Drag and drop the IMyAidlInterface.aidl from the default package **com.bharathksunilk.mycalculator** to the new package and click refactor when prompted.
	 - Now You may Right click on the **com.bharathksunilk.mycalculator** and press **delete**.

## Run
- Install and run the **AIDL Service** app.
- Then Install and run the **My Calculator** app, you should recieve a toast saying "Service Connected"
- Now you may enter the values and Add, Subtract or Multiply values.