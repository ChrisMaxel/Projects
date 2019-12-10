<meta charset="utf-8">

**Software Requirement Specification**

By Caleb Anthony

Introduction 
================

The customer (Dr. Stanley) has a desire for a clipboard buffer application. The project is codenamed Evergreen. (Not to be confused with Everdeen). 
The stakeholders in this project are: Dr. Stanley, and our radical team of four novice software engineers.


Purpose
--------------
The purpose of this SRS is to define clearly the requirements of this project and clarify any specific details of the software.
Anyone may read this SRS, but only our customer, Dr. Stanley, and our Elite Team of Novice Software Engineers (ETNSE) will be able to deeply understand it.

Scope
--------------
The software in question, codenamed Evergreen, will be a clipboard buffer named "Paste-it". A clipboard buffer is not to be confused with a clipboard manager.
<br>"Paste-it" will be a backgroundrunning appication that will allow users to retrieve a list of copied text for quick and accessible pasting. The buffer will allow users to copy multiple selections of text and have them stored in a buffer. Upon the tapping of a button, the software will display a list of all of the user's copied text.
This application will allow users to quickly find and paste previously copied information, saving a lot of time depending on the user's finger speed and fast-twitch apptitude. The goal of this application is to create a world where a user does not have to switch back and forward between multiple windows in order to copy and paste texts.


Definitions And Acronyms
--------------
**ETNSE:** Elite Team of Novice Software Engineers <br>
**SRS:** Software Requirement Specification

References
--------------
None so far but stay tuned!

Overview
--------------
The rest of this srs contains:
+ The description of the requirements
+ Perspective of the produce
+ A list of system and user interfaces
+ Any hardware and software interfaces
+ Product functions
+ Constraints
+ Dependencies
+ Future requirements
+ Specific Requirements
+ External interfaces
+ Functional requirements
+ Performance requirement
<br>
And much more! Only for $19.95

Product Description
===============

Description of the product is as follows:

Product Perspective
---------------

        The perspective of these requiremetns from a customer who may not know exactly what they want. 
        However, the perspective of the product is independent and self contained, running as an individual app and background process <br>
        System interface
        --------------
        The interface for the "Paste-it" application will be for android mobile devices only.
        <br>
        Operations:
        --------------
        There will be two operational states of the application: <br>

            1. **Preferences Page:** This will be launched upon the opening of the application <br>
            2. **Background Process:** This app will be constantly ran in the background, collecting clipboard information
        
Product Functions
--------------
    + **Set custom Preferences:** The software shall allow a user to set certain preferences for the clipboard buffer, including the ammount of clippings to show 
    + **Read user clipboard:** The software shall be reading constantly for when a user copies any text from any application on their mobile device.
    + **Store data in buffer:** The software shall store the user's copied text into a date sorted buffer, allowing the user to retrieve and paste the text quickly.

    
Dependencies
--------------
The only assumtion so far is that the application is ran on an Android device. Why? Because thats what our customer wants.


Future Requirements
--------------
    0. Pinning certain items in a clipboard to a list that will constantly be easy to acces no matte how many items the user may have copied


Specific requirements
===============
    +**The software shall be an application designed for Android mobile phones** <br>
        + This should be pretty straight forward, but feel free to ask if you don't know what a phone is.
    + **The software shall have a preference interface**
        + The preference interface shall allow users to choose settings for the application.
    + **The software shall run in the background**
        + While the application can be directly downloaded and installed, it's primary function is to run as a non intrusive backgroun process. 
    + **The software shall have a name and icon**
        + The name "Paste-it" and a beautiful icon shall be assined to the application upon the software's completion.
    + **The software shall not be hard to access**
        + For useability and good user interface, access to the clipboard shall not be more than three touches on the screen.
    + **The software shall store copied text into a buffer**
        + Any time a user copies text, the software shall take the text input and store it into a list. Said list (buffer) shall contain any other selections of text the user has copied.
    + **The software shall allow users to select from a list of copied text for pasting**
        + This buffer that contains the text from the user shall be easy to access and also upon the selection of an item, the buffer will then output the text into whatever field the user is inputing into.
    + **The software shall ask for a confirmation before pasting any text**
        + To prevent accidental pasting, the software shall notify the user and wait for confirmation before outputing the selected list item into the text field.
    + **The software shall support Android devices with version 4.2 and higher**
        + This is something that can be discused by the ETNSE.
    + **The usage of the software shall not intrude on the user experience**
        + This is to specify that opening the buffer for pasting will not open a new application but an overlay on the current screen.
    




Conclusion
==============
As this is just apreliminary SRS, much more details are to be added so stay tuned!




    

<!--    Leave the content below     -->

<!-- The script and style below are added for clarity and to workaround a bug -->
<script>
    // this is a hack to workaround a bug in Markdeep+Mathjax, where
    // &#36; is automatically converted to \( and \) too soon.
    // the following code will replace the innerHTML of all elements
    // with class "dollar" with a dollar sign.
    setTimeout(function() {
        var dollars = document.getElementsByClassName('dollar');
        for(var i = 0; i < dollars.length; i++) {
            dollars[i].innerHTML = '&#' + '36;';
        }
    }, 1000);
</script>
<style>
    /* adding some styling to <code> tags (but not <pre><code> coding blocks!) */
    :not(pre) > code {
        background-color: rgba(0,0,0,0.05);
        outline: 1px solid rgba(0,0,0,0.15);
        margin-left: 0.25em;
        margin-right: 0.25em;
    }
</style>


<!-- This template is based on the Princeton COS 423 LaTeX assignment template -->
<!-- Markdeep: --><style class="fallback">body{visibility:hidden;white-space:pre;font-family:monospace}</style><script src="markdeep.min.js"></script><script src="https://casual-effects.com/markdeep/latest/markdeep.min.js"></script><script>window.alreadyProcessedMarkdeep||(document.body.style.visibility="visible")</script>