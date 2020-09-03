package lesson4_calc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    public TextField firstValue;
    @FXML
    public TextField secondValue;
    @FXML
    public TextField resultValue;
    @FXML
    public Button additionButton;
    @FXML
    public Button subtractionButton;
    @FXML
    public Button multiplicationButton;
    @FXML
    public Button divisionButton;
    @FXML
    public Button specButton1;
    @FXML
    public Button specButton2;
    @FXML
    public Button specButton3;
    @FXML
    public Button specButton4;
    @FXML
    public TextArea history;

    private String lastOperation;

    public void additionOperation(ActionEvent actionEvent) {
        try {
            lastOperation = "+";
            Double firstValue = getFirstValue();
            Double secondValue = getSecondValue();
            if (firstValue != null && secondValue != null) {
                resultValue.setText(String.format("%.4f", firstValue + secondValue));
                saveHistory();
            }
        } catch (Exception ex) {
            resultValue.setText("Вводи цифры, а не буквы");
            saveHistory();
        }
    }

    public void subtractionOperation(ActionEvent actionEvent) {
        try {
            lastOperation = "-";
            Double firstValue = getFirstValue();
            Double secondValue = getSecondValue();
            if (firstValue != null && secondValue != null) {
                resultValue.setText(String.format("%.4f", firstValue - secondValue));
                saveHistory();
            }
        } catch (Exception ex) {
            resultValue.setText("Вводи цифры, а не буквы");
            saveHistory();
        }
    }

    public void multiplicationOperation(ActionEvent actionEvent) {
        try {
            lastOperation = "*";
            Double firstValue = getFirstValue();
            Double secondValue = getSecondValue();
            if (firstValue != null && secondValue != null) {
                resultValue.setText(String.format("%.4f", firstValue * secondValue));
                saveHistory();
            }
        } catch (Exception ex) {
            resultValue.setText("Вводи цифры, а не буквы");
            saveHistory();
        }
    }

    public void divisionOperation(ActionEvent actionEvent) {
        try {
            lastOperation = "/";
            Double firstValue = getFirstValue();
            Double secondValue = getSecondValue();
            if (firstValue != null && secondValue != null) {
                resultValue.setText(String.format("%.4f", firstValue / secondValue));
                saveHistory();
            }
        } catch (Exception ex) {
            resultValue.setText("Вводи цифры, а не буквы");
            saveHistory();
        }
    }

    public void specOperation1(ActionEvent actionEvent) {
        try {
            lastOperation = "=> 1/x";
            Double firstValue = getFirstValue();
            if (firstValue != null) {
                this.secondValue.setText("");
                resultValue.setText(String.format("%.4f", 1 / firstValue));
                saveHistory();
            }
        } catch (Exception ex) {
            resultValue.setText("Вводи цифры, а не буквы");
            saveHistory();
        }
    }

    public void specOperation2(ActionEvent actionEvent) {
        try {
            lastOperation = "=> ^2";
            Double firstValue = getFirstValue();
            if (firstValue != null) {
                this.secondValue.setText("");
                resultValue.setText(String.format("%.4f", Math.pow(firstValue, 2)));
                saveHistory();
            }
        } catch (Exception ex) {
            resultValue.setText("Вводи цифры, а не буквы");
            saveHistory();
        }
    }

    public void specOperation3(ActionEvent actionEvent) {
        try {
            lastOperation = "=> ^0.5";
            Double firstValue = getFirstValue();
            if (firstValue != null) {
                this.secondValue.setText("");
                resultValue.setText(String.format("%.4f", Math.sqrt(firstValue)));
                saveHistory();
            }
        } catch (Exception ex) {
            resultValue.setText("Вводи цифры, а не буквы");
            saveHistory();
        }
    }

    public void specOperation4(ActionEvent actionEvent) {
        try {
            lastOperation = "=> !";
            Double firstValue = getFirstValue();
            if (firstValue != null) {
                this.secondValue.setText("");
                if (firstValue < 0 || firstValue > 25) {
                    resultValue.setText("Недопустимое значение");
                    saveHistory();
                } else {
                    resultValue.setText(String.format("%d", factorial(firstValue.intValue())));
                    saveHistory();
                }
            }
        } catch (Exception ex) {
            resultValue.setText("Вводи цифры, а не буквы");
            saveHistory();
        }
    }

    private Double getFirstValue() {
        return firstValue.getText().equals("") ? null : preparationValue(firstValue.getText());
    }

    private Double getSecondValue() {
        return secondValue.getText().equals("") ? null : preparationValue(secondValue.getText());
    }

    private Double preparationValue(String value) {
        return Double.parseDouble(value.replace(",", "."));
    }

    public static long factorial(int value) {
        long result = 1;
        for (int i = 1; i <= value; i++) {
            result *= i;
        }
        return result;
    }

    public void saveHistory() {
        history.appendText(String.format("%s %s %s = %s\n", firstValue.getText(), lastOperation, secondValue.getText(), resultValue.getText()));
    }
}
