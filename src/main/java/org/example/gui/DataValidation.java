package org.example.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    public static boolean validateEmail(TextField tf){
        //String regex  = "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z+])+";
        //String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(tf.getText());
        if(m.find() && m.group().equals(tf.getText())){
            return true;
        }else {
            /*Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Vaile Email");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });*/
            return false;
        }
    }
    public static boolean validateMobile(TextField tf){
        String regex = "^\\d{9}$";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(tf.getText());
        if(m.find() && m.group().equals(tf.getText())){
            return true;
        }else {
            return false;
        }
    }
    public static boolean validatePostcode(TextField tf){
        String regex = "^\\d{2}-\\d{3}";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(tf.getText());
        if(m.find() && m.group().equals(tf.getText())){
            return true;
        }else {
            return false;
        }
    }
    public static boolean validateLazy(TextField tf){
        String regex = "^\\w{3,}";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(tf.getText());
        if(m.find() && m.group().equals(tf.getText())){
            return true;
        }else {
            return false;
        }
    }

}
