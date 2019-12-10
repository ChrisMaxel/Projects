                        **Software Requirements Specifications of EverGreen**
                                            Chris Maxel
                                            Team Elephant

Introduction
============
    Purpose
    --------
        The purpose of this document is to clearly communicate the requirements of EverGreen.  The intended audiences are all [stakeholders](./Software_Requirement_Specifications_Team_Elephant_Draft1.html#Definitions) involved.

    Overview of the Document
    -------------------------
        The rest of this document outlines what the applications intended purpose is based on the given requirements.  The customers requirements are also laid out in this document.

    Scope
    --------
        The name of the project is EverGreen.  This project will be developed in an agile scrum way continually reviewing every ([sprint](./Software_Requirement_Specifications_Team_Elephant_Draft1.html#Definitions)) with the customer.  Benefits of this software include(but are not limited to): ease of access when attempting to transfer multiple string items from one location to the next.  

        On a high level, this application should have two primary modes (edit, and active).  `Edit` is the mode where settings of how the software will preform can be configured.  Edit mode will be an application that can be added to the home screen of the device.  `Active` mode allow for the [user](./Software_Requirement_Specifications_Team_Elephant_Draft1.html#Definitions) to select a text box and then press the EverGreen pop up button to activate the list of recent copied strings to pop up.  Once a user selects the string they would like to paste, EverGreen automatically fills in the text field with the string selected from the pop up.

    Definitions
    -------------
        1. Stakeholders -> All parties involved in the project.  Including(but not limited to): The **Customer**(Dr. Stanley), the **Developers**(Chris Maxel, Caleb Anthony, Shane Wozniac, Joseph van der Harst), and any **Team lead** if there is one appointed.

        2. Sprint -> A multi-week interval in which the team completes and tests a working piece of software that is ready to be presented to the customer. (assuming 2 week sprints)

        3. User -> Someone who has downloaded and installed the application onto their smart phone.  


Overall Description
===================
    Project Perspective
    -------------------
        The application will be in use across other applications anytime there is a text field available and therefore will not be self-contained.  The application will have access to the system interfaces allowing for multiple pop-ups to appear on the screen.  The application will need to use a portion of memory to store recent strings and user configurations alongside the initial download and install of the application.
        

    User Characteristics
    --------------------
        The user of the application must have access to a smart device and an internet connection to download and install the application.  The intended audience of the software is for a high-school eduction level, with multiple years use of smart devices, and a low level of technical expertise.

    Assumptions & Dependencies
    ---------------------------
        - Team Elephant will assume that 10 recent copies will satisfy the customers need.

Specific Requirements
=====================
    The customer desires an android application that when information (in a string format) is selected (or highlighted), a pop up will appear asking the user if they would like to copy the information.  Once copied, the user can press and hold for a moment causing a pop up to appear allowing for the user to review things they have previously copied.

    1. There will be a non-clearable notification when the application is running to let the user know that it is active and running.
    2. When the application is running, it will not use an excess of processor power (hindering the ability to utilize other programs) or drain the battery an excessive amount.
    3. Two primary interfaces will be created (Edit and Active modes, outlined in the scope).
    4. To paste a string should be three or less clicks (taps on a smart device).
    5. Findable on the Android Market.
    6. Professionally styled to the customers need.
    7. Evergreen should run on modern Android devices (4.2 and higher).
    8. Application will run on mobile sized devices.
    9. A minimum of one configuration option in the configuration application (Edit mode).
    10. Evergreen should closely mimic the configuration of other Android applications.
    11. Developed using the native Android libraries.
    12. Evergreen will not be created in HTML or using a cross-platform framework such as Titanium.
    13. Evergreen will not require network access and should run off line.
    14. Evergreen should require the least permissions possible.
    15. The maximum length of a stored string will be as much as JDK allows.
    16. The software will be written in the coding language of Java.


Appendices
============
    N/A

Index
============
    N/A