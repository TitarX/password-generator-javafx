package workcode.passwordgenerator;

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

        if (passwordLength > 0) {
            String digits = "0123456789";
            String lettersLow = "abcdefghijklmnopqrstuvwxyz";
            String lettersUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String specialCharacters = "~!@#$%^&*()";

            int passwordCharsSpeciesIndex = 0;
            String[] passwordChars = new String[4];
            passwordChars[passwordCharsSpeciesIndex] = digits;

            if (withLetters) {
                passwordCharsSpeciesIndex++;
                passwordChars[passwordCharsSpeciesIndex] = lettersLow;
                passwordCharsSpeciesIndex++;
                passwordChars[passwordCharsSpeciesIndex] = lettersUp;
            }

            if (withSpecialCharacters) {
                passwordCharsSpeciesIndex++;
                passwordChars[passwordCharsSpeciesIndex] = specialCharacters;
            }

            for (int i = 0; i < passwordLength; i++) {
                // Случайный выбор типа символа
                int randomForSpecy = (int) (Math.random() * (passwordCharsSpeciesIndex + 1));
                String charsSet = passwordChars[randomForSpecy];
                int charsSetLength = charsSet.length();

                // Случайный выбор символа
                int randomForChar = (int) (Math.random() * charsSetLength);
                char charForPassword = charsSet.charAt(randomForChar);
            }
        }
    }
}
