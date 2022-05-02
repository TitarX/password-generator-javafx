package workcode.passwordgenerator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
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

    private static final int RECURSIVE_CALLS_COUNT_MAX = 100;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lengthSelect.getItems().addAll(8, 16, 32, 64);
        lengthSelect.setPromptText("Length");
        lengthSelect.setValue(16);

        this.passwordGenerate(0);
    }

    private void passwordGenerate(int recursiveCallsCount) {
        boolean withLetters = lettersCheckBox.isSelected();
        boolean withSpecialCharacters = specialCharactersCheckBox.isSelected();
        int passwordLength = lengthSelect.getValue();

        if (passwordLength > 0) {
            String digits = "0123456789";
            String lettersLow = "abcdefghijklmnopqrstuvwxyz";
            String lettersUp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String specialCharacters = "~!@#$%^&*()";

            ArrayList<String> passwordCharsList = new ArrayList<>();
            passwordCharsList.add(digits);

            if (withLetters) {
                passwordCharsList.add(lettersLow);
                passwordCharsList.add(lettersUp);
            }

            if (withSpecialCharacters) {
                passwordCharsList.add(specialCharacters);
            }

            int passwordCharsSize = passwordCharsList.size();
            StringBuilder passwordBuilder = new StringBuilder();
            for (int i = 0; i < passwordLength; i++) {
                // Случайный выбор типа символа
                int randomForSpecy = (int) (Math.random() * passwordCharsSize);
                String charsSet = passwordCharsList.get(randomForSpecy);
                int charsSetLength = charsSet.length();

                // Случайный выбор символа
                int randomForChar = (int) (Math.random() * charsSetLength);
                char charForPassword = charsSet.charAt(randomForChar);
                passwordBuilder.append(charForPassword);
            }
            String passwordString = passwordBuilder.toString();

            boolean isPasswordCorrect = passwordCheck(passwordString, passwordCharsList);
            if (isPasswordCorrect) {
                // Пароль верный, выводим
            } else {
                recursiveCallsCount++;
                if (recursiveCallsCount <= RECURSIVE_CALLS_COUNT_MAX) {
                    passwordGenerate(recursiveCallsCount);
                } else {
                    // Число попыток генерации пароля превысило лимит, выводим "Failed to generate password"
                }
            }
        }
    }

    private boolean passwordCheck(String passwordString, ArrayList<String> passwordCharsList) {
        //

        return true;
    }
}
