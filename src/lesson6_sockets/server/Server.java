package lesson6_sockets.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

public class Server {
    List<ClientHandler> clients;

    ServerSocket server;
    Socket socket;

    public Server(int port) {
        int clientNumber = 1;
        clients = new Vector<>();

        try {
            server = new ServerSocket(port);
            System.out.printf("Сервер запущен на порту %d\n", port);

            while (true) {
                socket = server.accept();
                broadcastMessage(String.format("=> Подключился клиент №%d", clientNumber));
                clients.add(new ClientHandler(this, socket, clientNumber++));
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

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMessage(String message) {
        System.out.println(message);
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }
}
