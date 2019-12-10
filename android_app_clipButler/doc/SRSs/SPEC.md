Project ClipButler Software Requirement Specification
====================================================
	Team Elephant
	Chris Maxel, Caleb Anthony, Shane Wozniak, and Joseph van der Harst


Introduction
============
	Motivation/purpose
	------------------
		The purpose of this document is to clearly communicate the requirements of ClipButler.  The intended audiences are all stakeholders involved.
	
	Scope
	-------------
		The code-name of the project is ClipButler.  This project will be developed in an agile scrum way continually reviewing every iteration with the customer.  Benefits of this software include(but are not limited to): ease of access when attempting to transfer multiple string items from one location to the next. 
	
	Definitions
	-----------
		In this section we will be defining terms throughout the document to be as clear and concise as possible.
		- Status Bar -> The bar at the top of the device where battery status, network connection, time, etc. are located.
		- Stakeholders -> All parties involved in the project.  Including(but not limited to): The **Customer**(Dr. Stanley), the **Developers**(Chris Maxel, Caleb Anthony, Shane Wozniac, Joseph van der Harst), and any **Team lead** if there is one appointed.
		- Iteration -> A one week interval in which the team completes and tests a working piece of software that is ready to be presented to the customer.

Product Description
===================
	Product Perspective
	-------------------
		The application will be in use across other applications anytime there is a text field available and therefore will not be self-contained.  The application will have access to the system interfaces allowing for multiple pop-ups to appear on the screen.  The application will need to use a portion of memory to store recent strings and user configurations alongside the initial download and install of the application.

	PREFS
	-------------
		PREFS is a configuration interface that allows the user to adjust settings for the application to help tailor the product to the users preference.  
		PREFS will:
			- Take over the screen interface.
			- Contain a switch to enable/disable ClipButler
			- Be an application (with a custom logo) that will be accessible from the phone app libraries.  
			- Display all of the current copied strings in a scrollable list, allowing for modifications.

	BUFFER
	-------------
		BUFFER is the functional portion of the software.  In this mode the user is able to select a text box and then press the ClipButler pop up button to display the list of recent copied strings.  Once a user selects the string they would like to paste and confirms, ClipButler automatically inserts the desired string at the cursor location.

	User Characteristics
	--------------------
		The user of the application must have access to a smart device and a network connection to download and install the application.  The intended audience of the software is for a middle-school eduction level or greater, with multiple years of experience using smart devices, without significant technical expertise.

	Assumptions
	-------------
		Team Elephant will assume the following:
		- That 10 recent copies will satisfy the customers need by default.
		- The application will be developed solely for android.
		- The application is currently intended for mobile use only.

Specific Requirements
=====================
	The software/application will:
	- Have a non-clearable icon when the application is running to let the user know that it is active in the status bar.
	- Have an icon where the apps are stored that allows for users to open the PREFS page by selecting the ClipButler app.
	- Allow BUFFER to be accessed in three touches or fewer.
		+ Step one: open interface
		+ Step two: select BUFFER item
		+ Step three: confirm selection (options here: paste at cursor, update clipboard, cancel)
	- Run on Android 4.2 or higher.
	- Run as a background process with no noticeable inefficiencies, such as battery drain, system slow down, etc.
	- Have two primary interfaces, PREFS and BUFFER, outlined in the product description section of the SPEC.
	- Be accessible on the Android Market.
	- Be professionally styled to the customers desires (items to be approved by the customer: app icon, app name, Android Market screen shots, and the Android Market description).
	- Run on mobile sized devices.
	- Have a minimum of one configuration option in PREFS.
	- Closely mimic the configuration of other Android applications (needs customer approval).
	- Be developed using the native Android libraries.
	- Not be required to be run in a browser.
	- Not require network access and should run off line (except for initial download).
	- Require the fewest permissions possible.
	- Enable storage of the maximum length string allowed by JDK.
	- Be written in the coding language of Java.

Future Steps
============
	- Compatibility on other smart devices.
	- Pinned strings
	- Time stamp of clipboard items
