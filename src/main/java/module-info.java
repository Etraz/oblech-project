module com.example.oblechproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oblechproject to javafx.fxml;
    exports com.example.oblechproject;
}