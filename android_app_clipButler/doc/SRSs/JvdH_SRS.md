# <center>COS 340 - Software Requirements Specification </center>
<center>
<b>Codename EVERGREEN</b><br>
<b>Joseph van der Harst</b><br>
<b>Current as of:</b> October 8, 2019
</center>

## <center>Disclaimer</center>
The things noted in this document are **not necessarily final**. They will change as more information is uncovered, requirements are specified, questions are answered, and development progresses. This document will contain a **"Current as of"** tag for quick reference, and otherwise this document will be kept in revision control, so that any version of it can be accessed at any time.

## <center>Motivations</center>
There are several clipboard-management applications available for Android, Linux, Mac, and newly for Windows. However, there does not appear to be such a thing for Android that does not steal application-focus from the currently active application whenever the user wants to choose a string to paste. This is the primary motivation for this project, and other clipboard managers should serve as some inspiration to get started. For communication purposes, we shall refer to the project as EVERGREEN until it receives its proper name. 

## <center>Low-Detail Overview</center>
The customer has made it clear that he does <b>NOT</b> want the user to have to interrupt their current application to paste text from EVERGREEN. As such, he recognizes that 2 entry points have to be made: the background process from which the user can fetch text (Buffer), and the full application from which the user can configure the background process (Prefs). How the Buffer and Prefs are to function will be outlined in **Buffer Requirements** and **Prefs Requirements**.

## <center>Buffer Requirements</center>
* The user should be able to access the buffer in 3 touches / swipes or fewer.
	* Whether this is through the keyboard, gestures, popups, or mapping a button is up to the developers, with approval from the customer.
* The Buffer is **NOT** to steal application focus, as in opening a new application on top of the current one.
	* In other words, the Buffer should be unobtrusive to the current activities.
* The Buffer is to retrieve data through the system's clipboard, and it shall **only** store text.
* The Buffer should not "be a drain" on the device's power. 
	* The only feasible way to do this is to order the system to activate the process.
* The Buffer should, if reasonable, use system notifications or floating menus to give the user access to its contents.

## <center>Prefs Requirements</center>
* Prefs shall be its own fully-fleshed application, opening and taking primary focus.
* Prefs shall allow the user to change at least 1 option.
	* The customer has required that there be a "NumClips" option, indicating the maximum number of strings in the buffer. Other options may be added at the approval of the customer.

## <center>It Would Be Nice If...</center>
* If reasonable, there should be an option to "pin" strings.
	* A user may want to keep his/her email address pinned, so that it is never lost.
	* The amount of pinned strings shall not infringe on NumClips.

## <center>Answered Questions</center>
* Should there be a "maximum string length" on any given string in the buffer?
	* Not artificially. If anything is to limit it, let it be available RAM or the OS.
* What is the status bar?
	* The indicators at the top of the screen, before dragging down.
* You want us to "truncate" strings so that they are viewable. What does that mean?
	* Make enough of the string (~16 or more characters) visible from the buffer, so that the user knows which is which.
* Is NumClips = 3 a good starting point?
	* On desktop, the customer prefers to start with 10, but the developers shall be the deciders of the optimal maximum number of clips by default.
* When the user pastes from the buffer, does that string become the "most recently used?"
	* No. The buffer should be first in, first out. Strings are to be "most recently copied," not "most recently used."

## <center>Questions For The Customer</center>
* How should we refer to "fully-fleshed" applications, such as the one that opens with the browser?
* What is the way / are the ways the end-user is to access the buffer?
	* Click + Hold > Buffer > choose from buffer?
	* Drag from top?
* Should a notification be thrown when the user copies a string, indicating the Buffer successfully received it?
* Should the user be able to open the Prefs application from the Buffer?
* Should "Evergreen," as the code name, inspire the look and feel of the final product?
	* Should the application's primary color be green, or its logo be an evergreen tree?
* Would it be preferable for copied items to have a timestamp attached to them?

## <center>Developer Notes</center>
* How do callbacks work in Android-Java?
* How we sync the layout and the code to Git?
	* I (Joseph) tried to put my first app on Git for testing, and found that the layout did not transfer as I wanted.
* We know there is a clipboard-manager API for Android. We should definitely use it.
* Seeing as there are lots of unanswered questions, I don't believe we are ready to begin designing our first iteration.

<center><h1><b>END OF SOFTWARE REQUIREMENTS SPECIFICATION</b></h1></center>
