# ClipboardManager API Notes

> [ClipboardManager Reference: _Use Me!_](https://developer.android.com/reference/android/content/ClipboardManager)  
> [Copy and Paste Reference](https://developer.android.com/guide/topics/text/copy-paste)  
> References to other classes are included in the given links

* public class ClipboardManager extends ClipboardManager
	* ClipboardManager is a global, uninstantiated class
	* **Important:** We get a reference to it by using
		```java
		getSystemService(CLIPBOARD_SERVICE);
		```
		* This is probably the pattern used to access other services as well

* Data for the clipboard is stored in ClipData objects
	* ClipData objects have a ClipDescription object, metadata about the clip
	* They have 1 or more ClipData.Item objects, used to store "text, URI, or Intent" data
		* The reference claims that **Text is stored in a CharSequence object.**
	* This is used for the application to store text to the clipboard
	* Pasting works the same way, but it is for the application to do, not to be updated

* In ClipboardManager, there is a method
	```java
	ClipboardManager.addPrimaryClipChangedListener(ClipboardManager.OnPrimaryClipChangedListener what)
	```
	* I believe this is what we use if we want the OS to ping our application when the clipboard is updated
	* We can get the "primary clip" using getPrimaryClip()
		* **Important:** "If the application is not the default IME or does not have input focus this returns null"
		* The listener says nothing about having to be the app in focus, so this should not be too difficult