module com.example.javagame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens com.example.javagame to javafx.fxml;
    exports com.example.javagame;
}