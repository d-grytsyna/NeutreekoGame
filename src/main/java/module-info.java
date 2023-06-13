module com.example.neutreeko {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.neutreeko to javafx.fxml;
    exports com.example.neutreeko;
}