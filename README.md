# PSUInterface

Android user interface for a programmable power supply unit.

## Currently able to:
* Navigate between different views, display fragments for Status (of currently active power supply ports) and Profiles (for CC/CV/charging, can be connfigured, but not yet saved).
* Receive data in JSON format, parse them into model objects, then convert for display, and display in a more userfriendly way.

## App architecture:
https://drive.google.com/drive/folders/1eTlyPxmxy4LhenANXnILYtnRvPMb_cSC

## UXPin interactive graphical prototype:
https://preview.uxpin.com/4c9e837b0ef1bba68c0adfca1ccbbbdf9e6c7e97#/pages/126619289/simulate/no-panels

## Future plans:
* Bluetooth Low Energy conncetion. BLE is supported since Android 4.3, but due to stability upgrades the author has chosen to support Android version 5.0 (Lollipop) minimum.
* Missing views, being able to control the power supply unit by sending user defined profiles to it.

Created by Airiin Kadakmaa
