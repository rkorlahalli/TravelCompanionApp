# Unit Converter App

## Overview
This project is an Android Studio application developed using **Java** and **XML**. The app allows users to convert values across three categories:

- Currency
- Fuel / Travel
- Temperature

## Features
- Dropdown menu to select the conversion category
- Dropdown menu to select the source unit
- Dropdown menu to select the destination unit
- Input field for entering the value to be converted
- Convert button to perform the calculation
- Result display area for showing the converted value

## Supported Conversions

### Currency
- USD
- AUD
- EUR
- JPY
- GBP

### Fuel / Travel
- MPG
- km/L
- Gallon (US)
- Liter
- Nautical Mile
- Kilometer

### Temperature
- Celsius
- Fahrenheit
- Kelvin

## Project Structure
- `activity_main.xml` — defines the user interface layout
- `MainActivity.java` — contains the application logic and conversion methods
- `strings.xml` — stores text values used in the interface

## How the App Works
The user selects a conversion category, chooses the source and destination units, enters a  value, and presses the Convert button.

The application then processes the request in `MainActivity.java` and displays the converted result on screen.

## Author
Rohan Korlahalli
