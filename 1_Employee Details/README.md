# SQLite Database
SQLite is a opensource SQL database that stores data to a text file on a device. Android comes in with built in SQLite database implementation. SQLite supports all the relational database features. In order to access this database, you don't need to establish any kind of connections for it like JDBC,ODBC e.t.c

## Problem Statement
Write a program to create an Activity to read Employee Details (EmpId, Name, Age, Address) from user and store to database and create a menu with menu item (Show Details) on pressing menu details it must go to another activity with employee id search box and search button and display the employee details on the screen.

## Screenshots
![Alt text](screenshots/1a.png?raw=true)
![Alt text](screenshots/1b.png?raw=true)
![Alt text](screenshots/1c.png?raw=true)
![Alt text](screenshots/1d.png?raw=true)
![Alt text](screenshots/1e.png?raw=true)
![Alt text](screenshots/1f.png?raw=true)

## Setup
This app has 2 Activities one menu and one Database Helper class
1. Create a New Android Project by the name **Employee Details** and resulting package name **com.bharathksunilk.employeedatabase**
2. Create the Launcher Activity, copy contents of [activity_main.xml](xml/activity_main.xml) and [MainActivity.java](java/com/bharathksunilk/employeedatabase/MainActivity.java).
3. Create a java class [MyDatabase.java](java/com/bharathksunilk/employeedatabase/MyDatabase.java) for managing Database.
4. Create the search activity and copy the contents [activity_search_employee.xml](xml/activity_search_employee.xml) and [SearchEmployee.java](java/com/bharathksunilk/employeedatabase/SearchEmployee.java).

## Run
Run the application and enter the employee details and after entering press the content menu button and press "Show Details" this will transfer you to the new Activity where you can search the employee.
