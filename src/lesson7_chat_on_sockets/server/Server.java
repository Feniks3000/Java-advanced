package lesson7_chat_on_sockets.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class Server {
    private List<ClientHandler> clients;
    private AuthService authService = new AuthServiceImpl();

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    ServerSocket server;
    Socket socket;

    public Server(int port) {
        clients = new Vector<>();

        try {
            server = new ServerSocket(port);
            System.out.printf("Сервер запущен на порту %d\n", port);

            while (true) {
                socket = server.accept();
                System.out.println("=> Подключился клиент");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMessage(String message) {
        System.out.println(message);
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void privateMessage(String recipient, String message) {
        getClientByLogin(recipient).sendMessage(message);
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastMessage(String.format("=> В чат вошел %s", client.getLogin()));
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastMessage(String.format("=> Чат покинул %s", client.getLogin()));
    }

    public boolean clientExits(String login) {
        for (ClientHandler client : clients) {
            if (client.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
    public ClientHandler getClientByLogin(String login) {
        for (ClientHandler client : clients) {
            if (client.getLogin().equals(login)) {
                return client;
            }
        }
        return null;
    }
}
