# Quick-Attendance-Project

A desktop and mobile App developed with JavaFx, Android, MySQL and firebase.
With which a student can mark his presence in a course by scanning a Qr code at the start of a lesson.

**Note: Check the Project_Report.pdf  for more informations.**

## Installation

Open terminal and type the following commands: 

```bash
git clone https://github.com/SkanderMarnissi/Quick-Attendance-Project/
```
After downloading:

```bash
cd Quick-Attendance-Project
```

Then follow the steps:

### Step 1: Install JSE IDLE(eclipse, netbeans...) to run the desktop app and android studio to run the mobile app:

You can download JSE from: https://www.java.com/fr/download/manual.jsp
You can download Android studio from: https://developer.android.com/studio?hl=fr

### Step 2: Install JavaFX plugin.

### Step 3: Install all the dependencies located in the Pom.xml file.

**Note: You can just run the maven Pom for step 3 under ./mavenproject2 directory.**


## How it works?

There are two directories:

**mavenproject2 directory: contain the desktop app**
**mobile-vx directory: contain the mobile app**

The desktop app is a management application there 3 different sessions:
**TopAdmin session: which can manage all the other entities.**
**Admin session: which can manage students and professors.**
**Professor session: which can manage students during the course.**

At the beginning of a lecture, the professor generates a Qr code on his desktop app with Zxing Api and presents it to the students.

Now Students have to scan it with their mobile app to notice their presence to the professor.

After the Qr code scan the data will be sent to firebase, at the end of a the students who checked their presence is shown in a data table on the desktop app, sot that the professor can submit the list to the administration other wise it is automatically done by the program it self. 

## Usage 
In order to process the program and make it work you need to:

### Step 1: Install the required softwares/packages/libraries.

### Step 2: Open your Java IDLE ans run the desktop application.

### Step 3: Log in the as TopAdmin and add administration, professors and students users:
*Default login="topadmin" password="topadmin"*

### Step 4: Log in the with the professor session to generate a QR Code.

### Step 5: Open android studio and run the mobile application or install it on your devise.

### Step 6: Log in and go scan the generated QR Code.

### Step 7: Now you will see the student displayed into a data table in the desktop app.

### Step 8: Submit the list to the administration.

### Step 9: Log in as an Administrator on the desktop app to see the students which were present in the lecture.



**Note: it is better to install the mobile app on a device so that you can easily scan the generated Qr code.**

*SKANDER MARNISSI COPYRIGHT © 2018 - ALL RIGHTS RESERVED*