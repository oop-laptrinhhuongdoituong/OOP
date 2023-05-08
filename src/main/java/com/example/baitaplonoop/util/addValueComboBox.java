package com.example.baitaplonoop.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import java.text.NumberFormat;
import java.util.Locale;

public class addValueComboBox {

    public static void addValue(ComboBox<String> comboBox){
        comboBox.getItems().addAll("100%", "90%", "83.3333%", "80%", "75%", "70%", "66.6667%", "60%", "50%", "40%", "33.3333%", "30%", "25%", "20%", "16.6667%", "14.28571%");
    }
    public static double convertStringToDouble(ComboBox<String> stringComboBox){
        String selectedValue = stringComboBox.getValue();
        double percentageGrade = Double.parseDouble(selectedValue.replace("%", ""))/100.0;
        return percentageGrade;
    }
}
