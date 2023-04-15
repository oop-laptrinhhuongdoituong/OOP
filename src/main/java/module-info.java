module com.example.baitaplonoop {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires java.naming;
            
                            
    opens com.example.baitaplonoop to javafx.fxml;
    exports com.example.baitaplonoop;
}