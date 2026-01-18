module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.fasterxml.jackson.databind;
    requires org.json;
    requires java.desktop;
//    requires org.json;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;

}