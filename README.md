# Android Fragment Demo Project

This project demonstrates the use of Android Fragments to insert and display information within an application, incorporating a specific fragment for date selection. It is structured to provide an example of effective communication between fragments, showcasing best practices in Android development. User has also the hability to turn the display without loosing data.

## Project Setup

### Views

#### InsertInfoFragment

![01](https://github.com/felp99/MOBAPP/assets/76445505/19fa146d-556d-4afa-ba3b-fc893b86d599)

#### DatePickerFragment

![04](https://github.com/felp99/MOBAPP/assets/76445505/3b501f86-f0f8-43b5-af62-a6ca7cd11653)

#### ShowInfoFragment

![02](https://github.com/felp99/MOBAPP/assets/76445505/ca571d2c-91e9-49b6-8ccf-23e32198fb34)
![03](https://github.com/felp99/MOBAPP/assets/76445505/53e4d380-d90a-4c65-ae39-35213843a072)

### Prerequisites

- Android Studio 4.0 or higher
- JDK 8 or higher
- Android SDK with a target API level of 30 (Android 11.0)
- Minimum SDK level supported: API level 21 (Android 5.0)

### Importing Project

1. Open Android Studio.
2. Select `File > New > Project from Version Control...`
3. Enter the project's Git URL and click `Clone`.
4. Once the project has been cloned, Android Studio should automatically set it up. If not, open the project directory via `File > Open...`.

### Running the Application

1. Open the AVD Manager by navigating to `Tools > AVD Manager`.
2. Create a virtual device that closely matches or resembles the Huawei P8 Lite 2017 specifications for testing. See the detailed guide below on configuring the virtual device.
3. Start the emulator and run the application.

## Configuring the Emulator to Match Huawei P8 Lite 2017 Specs

To enhance testing accuracy, configure your AVD (Android Virtual Device) to mimic the Huawei P8 Lite 2017, which features:

- **Display:** 5.2 inches, 1080 x 1920 pixels
- **CPU:** HiSilicon Kirin 655, ARM-based
- **Memory:** 3GB RAM
- **Storage:** 16GB internal storage
- **Android Version:** Initially shipped with Android 7.0 (Nougat)

Follow the setup instructions provided in the "Creating a New Virtual Device" section of this README to match these specifications as closely as possible.

## Project Structure

- `MainActivity.java` - Hosts the fragment containers and manages fragment transactions.
- `DatePickerFragment.java` - A fragment for selecting dates.
- `InsertInfoFragment.java` - Allows users to input information.
- `ShowInfoFragment.java` - Displays the information inputted from the `InsertInfoFragment` and selected date from `DatePickerFragment`.

## Using Fragments

This project makes extensive use of Fragments to create a dynamic and flexible UI. It demonstrates how to pass data between fragments using interfaces and shows how fragments can be reused across different activities.

## Contributing

We welcome contributions! Please read our contributing guidelines before making a pull request. For major changes, please open an issue first to discuss what you would like to change.

## License

[MIT License](LICENSE.md)
