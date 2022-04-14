package pro.corecode.passwordgenerator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private CheckBox lettersCheckBox;
    @FXML
    private CheckBox specialCharactersCheckBox;
    @FXML
    private ComboBox<Integer> lengthSelect;
    @FXML
    private Button generateButton;
    @FXML
    private TextArea resultTextArea;
    @FXML
    private Label clickHintLabel;

    private String digits = "0123456789";
    private String lettersLow = "abcdefghijklmnopqrstuvwxyz";
    private String lettersUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String specialCharacters = "~!@#$%^&*()";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lengthSelect.getItems().addAll(8, 16, 32, 64);
        lengthSelect.setPromptText("Length");
        lengthSelect.setValue(16);

        this.passwordGenerate();
    }

    private void passwordGenerate() {
        boolean withLetters = lettersCheckBox.isSelected();
        boolean withSpecialCharacters = specialCharactersCheckBox.isSelected();
        int passwordLength = lengthSelect.getValue();

        //
    }
}
