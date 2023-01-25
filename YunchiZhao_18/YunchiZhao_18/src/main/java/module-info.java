module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires jlayer;
    opens com.example.controller to javafx.fxml;
    exports com.example.controller;
    exports com.example.code;
    opens com.example.code to javafx.fxml;
}