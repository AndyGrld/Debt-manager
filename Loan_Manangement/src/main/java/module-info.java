module com.example.loan_manangement {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.loan_manangement to javafx.fxml;
    exports com.example.loan_manangement;
}