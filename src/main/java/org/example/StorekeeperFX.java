package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.PT.Storekeeper;

import java.io.IOException;

public class StorekeeperFX {
    Storekeeper storekeeper = new Storekeeper();

    @FXML
    private Button btnexit;

    @FXML
    private Button btnpackageaccepted;

    @FXML
    private Button btnreportdamage;

    @FXML
    private TextField tfpackagenumberS;

    @FXML
    void packageaccepted(ActionEvent event) {
        if(!tfpackagenumberS.getText().isEmpty()) {
            long code = Long.parseLong(tfpackagenumberS.getText());
            storekeeper.acceptParcel(code);
        }
    }

    @FXML
    void reportdamage(ActionEvent event) {
        if(!tfpackagenumberS.getText().isEmpty()) {
            long code = Long.parseLong(tfpackagenumberS.getText());
            storekeeper.reportTheDamage(code);
        }
    }

    @FXML
    void switchtoWorkerpanel(ActionEvent event)throws IOException {
            App.setRoot("WorkerPanel");
    }

}
