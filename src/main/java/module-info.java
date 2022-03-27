module pro.corecode.passwordgenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens pro.corecode.passwordgenerator to javafx.fxml;
    exports pro.corecode.passwordgenerator;
}