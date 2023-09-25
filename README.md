# Art Gallery Viewer App

an android app demo to view artwork from various artist using API from  [Art Institute of Chicago API](https://api.artic.edu/docs/)

this is my approach for Telkomsel's Use Case Test Challange by making an android application based on the requirement as the solution

## Features
1. view artwork from various artist using API from  [Art Institute of Chicago API](https://api.artic.edu/docs/)
2. Search artwork by title from [Art Institute of Chicago API](https://api.artic.edu/docs/)

## Supported Device
- Android device with minimum API 21 **(Nougat)**

## Build App requirements
- Recomended using Android Studio Giraffe | 2022.3.1 Patch 1
- Using Kotlin 1.9.0

## Instructions
1. Clone from this repository
    - Copy repository url
    - Open your Android Studio
    - New -> Project from Version Control..
    - Paste the url, click OK
2. Prepare the Android Virtual Device or real device
3. Build and deploy the app module

## Code Design & Structure
This project is using MVVM design pattern. The project directory consist of  directories:
1. **data**: The M (Model) in MVVM. Where we perform data operations and dependency injection with the help of [Hilt](https://dagger.dev/hilt/) v2.46
3. **screen**: User Interface directory for Activities and ViewModels helping to display data to the user.
4. **utils**: Urilities directory for helper classes and functions.