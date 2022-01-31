module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports org.example.gui;
    opens org.example.gui to javafx.fxml;
    exports org.example;
    opens org.example to javafx.fxml;
}
