# Calculator App Testing

## Before you test

### 1. Install Appium, Android sdk, UiAutomator2
Follow this [instructions](http://appium.io/docs/en/about-appium/getting-started/index.html) to setup Appium and all dependencies, complete until the end of **Starting Appium** section of the mentioned link.

### 2. Install Gradle

Install Gradle with Homebrew:  `$ brew install gradle`

You're ready to go!

Or this [link](https://gradle.org/install/) for instruction

## 3. Execute the test with Gradle

Run the test by making sure Appium is running in another terminal, and then, open the terminal at the **Automation_Framework** folder
from the command line, install all gems of the project by entering 

This only need to do once `./gradlew setupProject` 

To run the test suite, enter `./gradlew demoTestSuite`

**Note** This code has been tested on emulator device Nexus_5X_API_27 (Android 8.1), MAC OS 10.14