# Threads and UI
When an application is launched, the system creates a thread of execution for the application, called **"main"** This thread is very important because **it is in charge of dispatching events to the appropriate user interface widgets, including drawing events. It is also the thread in which your application interacts with components from the Android UI toolkit** (components from the android.widget and android.view packages). As such, the main thread is also sometimes called the **UI thread.**  
**The code within a separate thread must never, under any circumstances, directly update any aspect of the user interface.** Any changes to the user interface must always be performed from within the main thread.  
In the event that the code executing in a thread needs to interact with the user interface, it must do so by synchronizing with the main UI thread. This is achieved by creating a **handler** within the main thread, which, in turn, receives messages from another thread and updates the user interface accordingly.  
A **Handler** allows you to send and process Message and Runnable objects associated with a thread's MessageQueue. Each Handler instance is associated with a single thread and that thread's message queue. When you create a new Handler, it is bound to the thread / message queue of the thread that is creating it -- from that point on, it will deliver messages and runnables to that message queue and execute them as they come out of the message queue.

## Problem Statement
Create a program to create an activity with two buttons start and stop. On pressing start button the program must start the counter and must keep on counting until stop button is pressed.

## Screenshots
![Alt text](screenshots/4a.png?raw=true)
![Alt text](screenshots/4b.png?raw=true)
![Alt text](screenshots/4c.png?raw=true)

## Setup
1. Create a New Android Project by the name **Counter App** and resulting package name **com.bharathksunilk.counterapp**
2. Let the App have an Activity, copy contents of [activity_main.xml](xml/activity_main.xml) and [MainActivity.java](java/com/bharathksunilk/counterapp/MainActivity.java)

## Run
Press the **Start Counter** Button to start the thread, and press the **Stop Counter** to stop the thread