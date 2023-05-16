module com.example.baitaplonoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;
    requires java.sql;
    requires org.apache.poi.ooxml.schemas;
    requires org.apache.xmlbeans;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.poi;
    requires org.apache.logging.log4j;
    requires java.desktop;
    requires itextpdf;

    requires de.jensd.fx.glyphs.fontawesome;
    opens com.example.baitaplonoop to javafx.fxml;
    exports com.example.baitaplonoop;
    exports com.example.baitaplonoop.controller;
    opens com.example.baitaplonoop.controller to javafx.fxml;
    exports com.example.baitaplonoop.model;
    opens com.example.baitaplonoop.model to javafx.fxml;
    exports com.example.baitaplonoop.sql;
    opens com.example.baitaplonoop.sql to javafx.fxml;
    exports com.example.baitaplonoop.util;
    opens com.example.baitaplonoop.util to javafx.fxml;


}