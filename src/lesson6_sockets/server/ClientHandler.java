package lesson6_sockets.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler {
        private Server server;
        private Socket socket;
        private Integer clientNumber;
        private DataInputStream in;
        private DataOutputStream out;
        private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    public ClientHandler(Server server, Socket socket, Integer clientNumber) {
        this.server = server;
        this.socket = socket;
        this.clientNumber = clientNumber;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                while (true) {
                    String str = in.readUTF();
                    if (str.equals("/end")) {
                        break;
                    }
                    server.broadcastMessage(String.format("Клиент %d (%s):\t %s", clientNumber, simpleDateFormat.format(new Date()), str));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                server.broadcastMessage(String.format("=> Клиент %d покинул чат", clientNumber));
                try {
                    server.removeClient(this);
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
