package lesson4_chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    @FXML
    public TextArea history;
    @FXML
    public TextField message;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    public void sendMessage(ActionEvent actionEvent) {
        if (message.getText().length() != 0) {
            String newMessage = String.format("%s:\t\t%s\n\n", simpleDateFormat.format(new Date()), message.getText());
            history.appendText(newMessage);
            message.clear();
        }
    }
}
