package workcode.passwordgenerator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

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

            ArrayList<String> charsForPasswordList = new ArrayList<>();
            charsForPasswordList.add(digits);

            if (withLetters) {
                charsForPasswordList.add(lettersLow);
                charsForPasswordList.add(lettersUp);
            }

            if (withSpecialCharacters) {
                charsForPasswordList.add(specialCharacters);
            }

            int passwordCharsSize = charsForPasswordList.size();
            StringBuilder passwordBuilder = new StringBuilder();
            for (int i = 0; i < passwordLength; i++) {
                // Случайный выбор типа символа
                int randomForSpecy = (int) (Math.random() * passwordCharsSize);
                String charsSet = charsForPasswordList.get(randomForSpecy);
                int charsSetLength = charsSet.length();

                // Случайный выбор символа
                int randomForChar = (int) (Math.random() * charsSetLength);
                char charForPassword = charsSet.charAt(randomForChar);
                passwordBuilder.append(charForPassword);
            }
            String passwordString = passwordBuilder.toString();

            boolean isPasswordCorrect = passwordCheck(passwordString, charsForPasswordList);
            if (isPasswordCorrect) {
                // Пароль верный, выводим
                this.resultTextArea.setStyle("-fx-text-fill: black;");
                this.resultTextArea.setText(passwordString);
            } else {
                recursiveCallsCount++;
                if (recursiveCallsCount <= RECURSIVE_CALLS_COUNT_MAX) {
                    passwordGenerate(recursiveCallsCount);
                } else {
                    // Число попыток генерации пароля превысило лимит, выводим "Failed to generate password"
                    this.resultTextArea.setStyle("-fx-text-fill: red;");
                    this.resultTextArea.setText("Failed to generate password");
                }
            }
        }
    }

    private boolean passwordCheck(String passwordString, ArrayList<String> charsForPasswordList) {
        char[] passwordCharsArray = passwordString.toCharArray();

        AtomicBoolean passwordCorrect = new AtomicBoolean(true);
        charsForPasswordList.forEach((charsForPassword) -> {
            boolean charNotFound = true;
            for (char passwordChar : passwordCharsArray) {
                int charIndex = charsForPassword.indexOf(passwordChar);
                if (charIndex > -1) {
                    charNotFound = false;
                    break;
                }
            }

            if (charNotFound) {
                passwordCorrect.set(false);
            }
        });

        return passwordCorrect.get();
    }
}
