# File Handling
Android uses a file system that's similar to disk-based file systems on other platforms. A File object is suited to reading or writing large amounts of data in start-to-finish order without skipping around. For example, it's good for image files or anything exchanged over a network.
All Android devices have two file storage areas: "internal" and "external" storage. These names come from the early days of Android, when most devices offered built-in non-volatile memory (internal storage), plus a removable storage medium such as a micro SD card (external storage). Some devices divide the permanent storage space into "internal" and "external" partitions, so even without a removable storage medium, there are always two storage spaces and the API behavior is the same whether the external storage is removable or not. The following lists summarize the facts about each storage space.

#### Internal Storage:
- It's always available.
- Files saved here are accessible by only your app.
- When the user uninstalls your app, the system removes all your app's files from internal storage.
Internal storage is best when you want to be sure that neither the user nor other apps can access your files.

#### External Storage:
- It's not always available, because the user can mount the external storage as USB storage and in some cases remove it from the device.
- It's world-readable, so files saved here may be read outside of your control.
- When the user uninstalls your app, the system removes your app's files from here only if you save them in the directory from getExternalFilesDir().
External storage is the best place for files that don't require access restrictions and for files that you want to share with other apps or allow the user to access with a computer.

## Problem Statement
Write a program to create an activity with a text box and three buttons (save, open and create) open must allow to browse the text file from sdcard and must display the contents of the file on textbox, save button must save the contents of text box to file, create button must allow file user to create a new file and save the entered contents of the textbox.

## Screenshots
![Alt text](screenshots/2a.png?raw=true)
![Alt text](screenshots/2b.png?raw=true)
![Alt text](screenshots/2c.png?raw=true)

## Setup
This project has one Activity and a dialog layout
1. Create a New Android Project by the name **Text Creator** and resulting package name **com.bharathksunilk.textcreator**
2. Create a New layout file [dialog_filename.xml](xml/dialog_filename.xml) for the AlertDialoge.
3. Let the App have an Activity, copy contents of [activity_main.xml](xml/activity_main.xml) and [MainActivity.java](java/com/bharathksunilk/textcreator/MainActivity.java).
4. Add the permission to the [AndroidManifest.xml](xml/AndroidManifest.xml) :
```
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
### Note:
The MainActivity.java class contains both, methods to access the internal storage or the external. Make chenges wherever necessary to suite your needs.

## Known Issue
External Storage version could not be tested on an emulator but worked perfectly on a Real Phone. If someone knows the reason why kindly let me know.
