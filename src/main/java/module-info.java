module org.example {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;

    exports org.example.gui;
    opens org.example.gui to javafx.fxml;
    exports org.example.googleMaps;
    opens org.example.googleMaps to javafx.graphics;
    exports org.example;
    opens org.example to javafx.fxml;
}
