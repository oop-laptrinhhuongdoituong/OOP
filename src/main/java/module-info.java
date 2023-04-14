module com.example.baitaplonoop {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.baitaplonoop to javafx.fxml;
    exports com.example.baitaplonoop;
    opens com.example.baitaplonoop.Controller to javafx.fxml;
    exports com.example.baitaplonoop.Controller;
}