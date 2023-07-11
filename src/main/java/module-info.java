module org.example.multithreading {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.desktop;
    requires java.sql;
    requires org.apache.commons.io;
    requires jdk.httpserver;


    opens org.example.multithreading to javafx.fxml;
    exports org.example.multithreading;
}