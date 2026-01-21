# Google Play Launcher (Android TV)

A minimal Android TV launcher app that opens Google Play Store on Google TV devices.

## Features
- TV launcher entry point (Leanback + standard launcher)
- Tries Play Store app first, falls back gracefully if not available
- Adaptive icon with white background and your `logo.png`
- TV banner for home screen rows

## Requirements
- Android Studio (or Gradle CLI)
- Android 10+ (minSdk 29)

## Build
Debug APK:
```bash
./gradlew :app:assembleDebug
```

Release APK:
```bash
./gradlew :app:assembleRelease
```

## Release APK
After building, the release APK is copied to:
`Release/app-release.apk`

Note: The release APK is unsigned unless you add a signing config. Sign it before distributing.

## Customize Branding
- App icon foreground: `app/src/main/res/drawable/logo.png`
- Adaptive icon background: `app/src/main/res/drawable/ic_launcher_background.xml`
- TV banner: `app/src/main/res/drawable/tv_banner.xml`
