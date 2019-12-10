# Project EVERGREEN Software Requirement Specification
#### Shane Wozniak


## General Overview

While most Operating Systems have implemented a clipboard, to allow for quick copying and pasting of text, images, and the like, these implementations are usually limited to one item at a time. This limitation can make the task of copying and pasting several items one at a time seem very tedious. 


## Background

This document is a Software Requirements Specification for an Android application codenamed EVERGREEN. EVERGREEN is a non-intrusive clipboard buffering application that allows the user to access a history of strings from the operating system clipboard.


# Operation
## Operation Overview
As previously mentioned, the user interface should be as simple as possible and should be minimally invasive; as screen real estate is particularly valuable in the case of mobile software implementations, the clipboard application should take up very little of the screen. The clipboard access should also be straight forward, with full operation taking no more than 3 touches (the fewer the better). While many standard clipboard implementations allow support for non-string items, our focus with EVERGREEN will be string items
## Operation Features
The application will be split into two primary user interfaces: Clipboard access (BUFFER) and Preferences (PREFS).
### Clipboard Access (BUFFER)
This portion of the application refers to the primary way that the user interacts with the software. The clipboard access consists of the way in which users carry out copy and paste actions as well as rearranging the clipboard elements. There are a few potential approaches to how the user could access the clipboard manager, such as:
* Gesture based
* Directly on keyboard
* Pop-ups during text edits
* Press and hold menu button.

### Preferences (PREFS)
This portion of the application consists of preferential settings that will Characterize
how a user interacts with the clipboared access feature. Preferences would open from launcher application (main downloaded application with more comprehensive interface). Example preferences could be: 
* Toggle between 'auto-paste' and confirmation for larger texts
* sorting preferences of clipboard elements
* Type of clipboard access interface 

## Potential Features
There are several convenience features that we view as valuable that we would like to implement, such as: 
* Pinning functinality
* Timestamp of clipboard items

# Technical Requirements

### Intended Device Support
Our target is modern Android devices, with Android 4.2 and higher. Support for tablets may be added in a future iteration of the application.
## BUFFER
### BUFFER Item Support
Our BUFFER will only consist of string items that have been copied. The string items will be reduced and reformatted to allow for better view in the BUFFER menu.

### Performance
EVERGREEN is intended to run in the background and therefore should not result in noticable inefficiencies, such as system slowdown, excessive battery drain, etc.

## Preferences (PREFS)
### Interface

The PREFS interface is to be more comprehensive, stealing primary application focus. It will consist of several configuration options, to help tailor the product to users' habits. Configuration options should consist of a maximum number of clippings to store in the BUFFER, with a resonable value set by default. This option may be affected by the method of BUFFER menu display.

## Development

EVERGREEN should be developed using native Android libraries, should be Android specific, and should not have to be run in a browser window. EVERGREEN is intended to be a client-only application and therfore should not require access to an external network. It should require minimal permissions from the Android operating system and should request only neccessary permissions required for proper operation.