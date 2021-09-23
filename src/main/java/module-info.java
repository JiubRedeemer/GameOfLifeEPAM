module com.example.gameoflifeepam {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gameoflifeepam to javafx.fxml;
    exports com.example.gameoflifeepam;
}