module com.biblio {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires java.desktop;

    opens com.biblio to javafx.fxml;
    exports com.biblio;
    exports com.biblio.controller;
    opens com.biblio.controller to javafx.fxml;
    opens com.biblio.model to javafx.base;


}