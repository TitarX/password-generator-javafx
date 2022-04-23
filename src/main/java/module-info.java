module pro.corecode.passwordgenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens workcode.passwordgenerator to javafx.fxml;
    exports workcode.passwordgenerator;
}