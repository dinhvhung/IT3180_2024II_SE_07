module com.example.service_apa.demo.xsx {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires static lombok;
    requires spring.web;
    requires spring.data.jpa;
    requires jakarta.persistence;
    requires spring.data.commons;
    requires java.validation;
    requires java.net.http;

    opens com.example.service_apa.demo.xsx to spring.core, spring.beans, spring.context, javafx.fxml, javafx.controls;

    exports com.example.service_apa.demo.xsx;
}
