module org.example.multithreading {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.desktop;
    requires java.sql;
    requires org.apache.commons.io;
    requires jdk.httpserver;
    requires net.bytebuddy;
    requires spring.batch.infrastructure;
    requires spring.batch.core;
    requires spring.context;
    requires spring.core;
    requires spring.web;
    requires spring.integration.core;
    requires spring.boot.autoconfigure;
    requires spring.integration.file;
    requires spring.messaging;
    requires java.persistence;
    requires spring.security.core;
    requires spring.security.config;
    requires spring.security.crypto;
    requires spring.beans;
    requires spring.jms;
    requires jakarta.jms.api;
    requires reactor.core;
    requires spring.boot;
    requires java.instrument;
    requires java.annotation;


    opens org.coursesandsandbox.multithreading to javafx.fxml;
    exports org.coursesandsandbox.multithreading;
}