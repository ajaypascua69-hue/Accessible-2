# Accessible

An experimental Android screen-reader project.

## Build an installable test APK

1. Open the **Actions** tab in this repository.
2. Select **Build Accessible APK** and choose **Run workflow**, or push a commit to `main`.
3. Wait for the workflow to finish successfully.
4. Open the completed workflow run and download the `Accessible-debug-apk` artifact.
5. Extract the ZIP and install `app-debug.apk` on your Android phone. Android may ask you to allow installation from that source.
6. Open Accessible, choose **Open Accessibility settings**, then enable **Accessible Screen Reader**.

## Important testing notice

This is an early development build, not a finished TalkBack replacement. It currently provides a basic accessibility service that speaks text from accessibility events. Gesture navigation, reliable one-finger two-dimensional scrolling, image descriptions, advanced focus navigation, multilingual tuning, release signing, and full device testing still need implementation and verification.

Keep your existing screen reader available while testing. Do not enable two screen readers simultaneously unless you understand how their speech and touch exploration may interact.

## Build locally

Requires JDK 17, Android SDK 36, and Gradle 8.13:

```sh
gradle :app:assembleDebug
```

The APK is generated at `app/build/outputs/apk/debug/app-debug.apk`.


Build workflow trigger check.


The APK workflow has been updated to install Android SDK packages directly before building.
