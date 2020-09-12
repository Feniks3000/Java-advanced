package lesson7_chat_on_sockets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ClientHandler {
        private Server server;
        private Socket socket;
        private String login;
        private DataInputStream in;
        private DataOutputStream out;
        private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    public ClientHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                authorization(server);
                while (true) {
                    String message = in.readUTF();
                    if (message.equals("/end")) {
                        break;
                    }
                    if (message.startsWith("/for ")) {
                        String[] command = message.split("\\s", 3);
                        if (server.clientExits(command[1])) {
                            server.privateMessage(command[1], String.format("Личное сообщение от %s (%s):\t %s", login, simpleDateFormat.format(new Date()), command[2]));
                        } else {
                            sendMessage(String.format("Пользователь %s не найден. Сообщение не отправлено", command[1]));
                        }
                    } else {
                        server.broadcastMessage(String.format("%s (%s):\t %s", login, simpleDateFormat.format(new Date()), message));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    server.unsubscribe(this);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void authorization(Server server) throws IOException {
        while (true) {
            String message = in.readUTF();
            if (message.startsWith("/auth ")) {
                String[] token = message.split("\\s", 3);
                if (!server.getAuthService().loginBusy(token[1])) {
                    server.getAuthService().addUser(token[1], token[2]);
                    login = token[1];
                    server.subscribe(this);
                    sendMessage("/authOk");
                    break;
                } else if (server.getAuthService().userExist(token[1], token[2])) {
                    if (!server.clientExits(token[1])) {
                        login = token[1];
                        sendMessage("/authOk");
                        server.subscribe(this);
                        break;
                    } else {
                        System.out.printf("%s пытается открыть чат в другом окне\n", token[1]);
                        sendMessage(String.format("%s вы уже аторизованы в другом окне\n", token[1]));
                    }
                } else {
                    System.out.printf("%s ввел неверный пароль\n", token[1]);
                    sendMessage("Неверный логин / пароль");
                }
            }
        }
    }

    public String getLogin() {
        return login;
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
