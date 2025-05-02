package com.biblio.util;

import javafx.scene.control.Alert;

public class FXUtil {
    //вывод ошибки
    public void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
