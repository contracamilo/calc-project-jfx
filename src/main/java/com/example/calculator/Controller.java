package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;

public class Controller {

    @FXML
    private Button buttonMod;

    @FXML
    private Button buttonFour;
    @FXML
    private Button buttonSix;

    @FXML
    private Button buttonClean;

    @FXML
    private Button buttonOne;

    @FXML
    private Button buttonDot;

    @FXML
    private Button buttonDel;

    @FXML
    private Button buttonMultiplication;

    @FXML
    private Button buttonSum;

    @FXML
    private Label welcomeText;

    @FXML
    private Button buttonSeven;

    @FXML
    private Button buttonTwo;

    @FXML
    private Button buttonSubstract;

    @FXML
    private Button buttonThree;

    @FXML
    private Button buttonEight;

    @FXML
    private Button ButtonAns;

    @FXML
    private Button buttonNine;

    @FXML
    private Button ButtonEqual;

    @FXML
    private Button buttonZero;

    @FXML
    private Button buttonFive;

    @FXML
    private Button buttonDivision;

    @FXML
    private TextField calcScreen;
    @FXML
    private TextArea textareaHistory;
    private Boolean operationOn = true;
    private double lastOperation = 0;

    @FXML
    protected void cleanScreen() {
        calcScreen.setText("");
        operationOn = true;
    }

    @FXML
    void addOperation(javafx.event.ActionEvent actionEvent) {
        if (operationOn) {
            calcScreen.setText(calcScreen.getText() + ((Button) actionEvent.getSource()).getText());
            operationOn = false;
        }
    }

    @FXML
    void addValue(javafx.event.ActionEvent actionEvent) {
        calcScreen.setText(calcScreen.getText() + ((Button) actionEvent.getSource()).getText());
        operationOn = true;
    }
    @FXML
    protected void deleteValue(ActionEvent event) {
        if (!(calcScreen.getText().isEmpty())) {
            calcScreen.setText(calcScreen.getText().substring(0, calcScreen.getText().length() - 1));
        }
    }
    @FXML
    protected void getLastResult(ActionEvent event) {
        if (!(lastOperation == 0)) {
            calcScreen.setText(calcScreen.getText() + lastOperation);
        }
    }
    @FXML
    protected void makeOperation(javafx.event.ActionEvent actionEvent) {
        String operations = calcScreen.getText();

        try (Context context = Context.create()) {
            Value result = context.eval("js", calcScreen.getText().replaceAll("x", "*"));
            calcScreen.setText(result.toString());
            textareaHistory.setText(operations + " = " + calcScreen.getText() + "\n" + textareaHistory.getText());
            lastOperation = Double.parseDouble(calcScreen.getText());
        } catch (PolyglotException e) {
            calcScreen.setText("ERR");
        }

    }
}



