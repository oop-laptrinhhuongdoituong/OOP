module com.example.baitaplonoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.naming;
    requires java.sql;
            
                            
    opens com.example.baitaplonoop to javafx.fxml;
    exports com.example.baitaplonoop;
    opens com.example.baitaplonoop.Controller to javafx.fxml;
    exports com.example.baitaplonoop.Controller;
}