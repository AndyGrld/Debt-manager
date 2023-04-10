package com.example.loan_manangement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController{

    @FXML
    private Label invalidlabel;

    @FXML
    private PasswordField passfield;

    FileChooser dialogbox = new FileChooser();

    @FXML
    private TextField userfield;

    @FXML
    private TextField amountfield;

    @FXML
    private Button editbutton;

    @FXML
    private RadioButton female;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton male;

    @FXML
    private TextField name;

    @FXML
    private TextField phonefield;

    @FXML
    private TextArea rightArea;

    @FXML
    private Button saveDebtorbutton;

    @FXML
    private Button saveUpdatesbutton;

    @FXML
    private Button viewExistingbutton;

    String oldphone = "";
    String oldamount = "";
    int cursorphone = 0;
    int cursoramount = 0;

    @FXML
    void onClose(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onEdit(ActionEvent event){
        rightArea.setEditable(true);
        saveUpdatesbutton.setDisable(false);
    }

    @FXML
    void getamount(KeyEvent event) {
        oldamount = amountfield.getText();
        cursoramount = amountfield.getCaretPosition();
    }

    @FXML
    void getphone(KeyEvent event) {
        oldphone = phonefield.getText();
        cursorphone = phonefield.getCaretPosition();
    }

    @FXML
    void typedamount(KeyEvent event) {
        String typed = event.getCharacter();
        if (!typed.matches("\\d")){
            amountfield.setText(oldamount);
            amountfield.positionCaret(cursoramount);
        }
    }

    @FXML
    void typedphone(KeyEvent event) {
        String typed = event.getCharacter();
        if (!typed.matches("\\d")){
            phonefield.setText(oldphone);
            phonefield.positionCaret(cursorphone);
            phonefield.setStyle("fx:fx-border-color=red");
        }
    }


    @FXML
    void onSave(ActionEvent event) throws FileNotFoundException {
        dialogbox.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files(*.txt)","*.txt"));
        File selectedFile = dialogbox.showSaveDialog(new Stage());
        if(selectedFile==null){}
        else{
            PrintWriter output = new PrintWriter(selectedFile);
            String values = rightArea.getText();
            output.println(values);
            output.close();
        }
    }

    @FXML
    void onLogout(ActionEvent event) throws IOException {
        Stage close = (Stage) editbutton.getScene().getWindow();
        close.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("LOGIN");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onviewExisting(ActionEvent event) throws FileNotFoundException {
        rightArea.setText("");
        rightArea.setEditable(true);
        dialogbox.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files(*.txt)","*.txt"));
        File selectedFile = dialogbox.showOpenDialog(new Stage());
        if(selectedFile==null){}
        else{
            Scanner input = new Scanner(selectedFile);
            while(input.hasNextLine()){
                rightArea.appendText(input.nextLine()+"\n");
            }
            input.close();
            rightArea.setEditable(false);
            editbutton.setDisable(false);
        }
    }

    @FXML
    void onsaveDebtor(ActionEvent event) throws FileNotFoundException {
        dialogbox.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files(*.txt)","*.txt"));
        File selectedFile = dialogbox.showSaveDialog(new Stage());
        if(selectedFile==null){}
        else{
            String uname = "Name: "+name.getText()+"\n";
            String phone = "Phone: "+phonefield.getText()+"\n";
            String amount = "Amount: Gh "+amountfield.getText()+"\n";
            String gender = "Gender: None was selected\n";
            if(male.isSelected()){
                gender = "Gender: Male\n";
            }
            else if(female.isSelected()){
                gender = "Gender: Female\n";
            }
            PrintWriter output = new PrintWriter(selectedFile);
            output.println(uname+phone+amount+gender);
            output.close();
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void onLogin(ActionEvent event) throws IOException {
        String name = userfield.getText();
        String password = passfield.getText();
        if(name.equals("admin") && password.equals("12345")){
            Stage close = (Stage) userfield.getScene().getWindow();
            close.close();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainApplication.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("MAIN APPLICATION");
            stage.setScene(scene);
            stage.show();
        }
        else{
            userfield.setText("");
            passfield.setText("");
            invalidlabel.setText("Invalid Login Details");
        }

    }
}
