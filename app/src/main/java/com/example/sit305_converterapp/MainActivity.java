package com.example.sit305_converterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerCategory, spinnerFrom, spinnerTo;
    EditText editTextValue;
    Button buttonConvert;
    TextView textViewResult;

    String[] categories = {"Currency", "Fuel / Travel", "Temperature"};

    String[] currencyUnits = {"USD", "AUD", "EUR", "JPY", "GBP"};
    String[] fuelUnits = {"MPG", "km/L", "Gallon (US)", "Liter", "Nautical Mile", "Kilometer"};
    String[] temperatureUnits = {"Celsius", "Fahrenheit", "Kelvin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerFrom = findViewById(R.id.spinnerFrom);
        spinnerTo = findViewById(R.id.spinnerTo);
        editTextValue = findViewById(R.id.editTextValue);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        setupSpinner(spinnerCategory, categories);
        updateUnitSpinners("Currency");

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories[position];
                updateUnitSpinners(selectedCategory);
                textViewResult.setText(getString(R.string.result_placeholder));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editTextValue.getText().toString().trim();

                if (inputText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                    return;
                }

                double inputValue = Double.parseDouble(inputText);
                String category = spinnerCategory.getSelectedItem().toString();
                String fromUnit = spinnerFrom.getSelectedItem().toString();
                String toUnit = spinnerTo.getSelectedItem().toString();

                Double result = null;

                switch (category) {
                    case "Currency":
                        result = convertCurrency(inputValue, fromUnit, toUnit);
                        break;

                    case "Fuel / Travel":
                        result = convertFuelTravel(inputValue, fromUnit, toUnit);
                        break;

                    case "Temperature":
                        result = convertTemperature(inputValue, fromUnit, toUnit);
                        break;
                }

                if (result == null) {
                    textViewResult.setText("Invalid conversion for selected units.");
                } else {
                    textViewResult.setText("Result: " + String.format("%.2f", result) + " " + toUnit);
                }
            }
        });
    }

    private void setupSpinner(Spinner spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                items
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void updateUnitSpinners(String category) {
        switch (category) {
            case "Currency":
                setupSpinner(spinnerFrom, currencyUnits);
                setupSpinner(spinnerTo, currencyUnits);
                break;

            case "Fuel / Travel":
                setupSpinner(spinnerFrom, fuelUnits);
                setupSpinner(spinnerTo, fuelUnits);
                break;

            case "Temperature":
                setupSpinner(spinnerFrom, temperatureUnits);
                setupSpinner(spinnerTo, temperatureUnits);
                break;
        }
    }

    private double convertCurrency(double value, String from, String to) {
        if (from.equals(to)) {
            return value;
        }

        double usdValue;

        switch (from) {
            case "USD":
                usdValue = value;
                break;
            case "AUD":
                usdValue = value / 1.55;
                break;
            case "EUR":
                usdValue = value / 0.92;
                break;
            case "JPY":
                usdValue = value / 148.50;
                break;
            case "GBP":
                usdValue = value / 0.78;
                break;
            default:
                usdValue = value;
        }

        switch (to) {
            case "USD":
                return usdValue;
            case "AUD":
                return usdValue * 1.55;
            case "EUR":
                return usdValue * 0.92;
            case "JPY":
                return usdValue * 148.50;
            case "GBP":
                return usdValue * 0.78;
            default:
                return usdValue;
        }
    }

    private Double convertFuelTravel(double value, String from, String to) {
        if (from.equals(to)) {
            return value;
        }

        if (from.equals("MPG") && to.equals("km/L")) {
            return value * 0.425;
        } else if (from.equals("km/L") && to.equals("MPG")) {
            return value / 0.425;
        } else if (from.equals("Gallon (US)") && to.equals("Liter")) {
            return value * 3.785;
        } else if (from.equals("Liter") && to.equals("Gallon (US)")) {
            return value / 3.785;
        } else if (from.equals("Nautical Mile") && to.equals("Kilometer")) {
            return value * 1.852;
        } else if (from.equals("Kilometer") && to.equals("Nautical Mile")) {
            return value / 1.852;
        }

        return null;
    }

    private Double convertTemperature(double value, String from, String to) {
        if (from.equals(to)) {
            return value;
        }

        if (from.equals("Celsius") && to.equals("Fahrenheit")) {
            return (value * 1.8) + 32;
        } else if (from.equals("Fahrenheit") && to.equals("Celsius")) {
            return (value - 32) / 1.8;
        } else if (from.equals("Celsius") && to.equals("Kelvin")) {
            return value + 273.15;
        } else if (from.equals("Kelvin") && to.equals("Celsius")) {
            return value - 273.15;
        } else if (from.equals("Fahrenheit") && to.equals("Kelvin")) {
            return ((value - 32) / 1.8) + 273.15;
        } else if (from.equals("Kelvin") && to.equals("Fahrenheit")) {
            return ((value - 273.15) * 1.8) + 32;
        }

        return null;
    }
}