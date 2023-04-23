module com.example.baitaplonoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml.schemas;
    requires org.apache.poi.ooxml;
    requires org.apache.logging.log4j;
    requires org.apache.xmlbeans;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires java.naming;
                            
    opens com.example.baitaplonoop to javafx.fxml;
    exports com.example.baitaplonoop;
    exports com.example.baitaplonoop.Gui;
    opens com.example.baitaplonoop.Gui to javafx.fxml;
    exports com.example.baitaplonoop.Model;
    opens com.example.baitaplonoop.Model to javafx.fxml;
}