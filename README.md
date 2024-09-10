# Happy Places App

A simple Android app that allows users to add and store happy places with titles, descriptions, dates, images, and location details.

## Features

- Add new happy places with:
  - Title
  - Description
  - Date
  - Location (using GPS)
  - Image (from camera or gallery)
- View and delete saved happy places
- Save images to internal storage
- Automatically fetch current location with permission

## Prerequisites

- Android Studio (latest version recommended)
- Minimum SDK: 21 (Android 5.0)
- Target SDK: 30+

## Permissions

Ensure the following permissions are declared in your `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.CAMERA" />

