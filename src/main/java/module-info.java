module org.example {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;
    requires log4j;
    requires json.simple;

    exports org.example.gui;
    opens org.example.gui to javafx.fxml;
    exports org.example.maps;
    opens org.example.maps to javafx.graphics;
    exports org.example;
    opens org.example to javafx.fxml;
}
