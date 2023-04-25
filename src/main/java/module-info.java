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
            
                            
    opens com.example.baitaplonoop to javafx.fxml;
    exports com.example.baitaplonoop;
    opens com.example.baitaplonoop.controller to javafx.fxml;
    exports com.example.baitaplonoop.controller;
}