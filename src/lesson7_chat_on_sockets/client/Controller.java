package lesson7_chat_on_sockets.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public HBox authPanel;
    @FXML
    public HBox sendPanel;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passField;
    @FXML
    public TextField messageField;
    @FXML
    public TextArea history;

    private boolean authenticated;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            stage = (Stage) messageField.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                if (socket != null && !socket.isClosed()) {
                    try {
                        out.writeUTF("/end");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        });
        setAuthenticated(false);
    }
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);
        sendPanel.setVisible(authenticated);
        sendPanel.setManaged(authenticated);

        if (authenticated) {
            setTitle("Simple chat for " + loginField.getText().trim().toLowerCase());
        } else {
            setTitle("Simple chat");
            history.clear();
        }
    }

    private void setTitle(String title) {
        Platform.runLater(() -> {
            stage.setTitle(title);
        });
    }

    public void tryToAuth(ActionEvent actionEvent) {
        if(socket == null || socket.isClosed()){
            connectTo("localhost", 8189);
        }

        try {
            String login = loginField.getText().trim().toLowerCase();
            String pass = passField.getText().trim();
            if (StringUtils.isNotEmpty(login) && StringUtils.isNotEmpty(pass)) {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    String passHash = Arrays.toString(messageDigest.digest(pass.getBytes(StandardCharsets.UTF_8)));
                    out.writeUTF(String.format("/auth %s %s", login, passHash));
                    passField.clear();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } else {
                printMessage("Соединение не установлено. Логин или пароль пусты или состоят из пробелов");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void connectTo(String ip, int port){
        try {
            socket = new Socket(ip, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String message = in.readUTF();
                        if (message.equals("/authOk")) {
                            setAuthenticated(true);
                            break;
                        }
                        printMessage(message);
                    }
                    while (true) {
                        String message = in.readUTF();
                        if (message.equals("/end")) {
                            printMessage("Чат завершен");
                            break;
                        }
                        printMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Мы отключились от сервера");
                    setAuthenticated(false);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        try {
            String message = messageField.getText();
            if (message.length() > 0) {
                if (message.startsWith("/note ")) {
                    if (message.length() > 6) {
                        printMessage(String.format("Личная заметка: %s", messageField.getText().substring(6)));
                    }
                } else {
                    out.writeUTF(message);
                    messageField.clear();
                    messageField.requestFocus();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printMessage(String message) {
        if (message.length() > 0) {
            history.appendText(String.format("%s\n\n", message));
        }
    }

}
