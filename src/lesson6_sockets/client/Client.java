package lesson6_sockets.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private final String IP_ADDRESS;
    private final int PORT;

    private Socket socket;
    DataInputStream in;
    DataOutputStream out;
    Scanner scanner = new Scanner(System.in);

    public Client(String ip, int port) {
        IP_ADDRESS = ip;
        PORT = port;

        try {
            socket = new Socket(IP_ADDRESS, PORT);
            System.out.println();
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Thread inputThread = getInputThread();
            inputThread.start();

            Thread outputThread = getOutputThread(inputThread);
            outputThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Thread getOutputThread(Thread inputThread) {
        return new Thread(() -> {
            try {
                while (true) {
                    String str = scanner.nextLine();
                    out.writeUTF(str);
                    if (str.equals("/end")) {
                        inputThread.join();
                        break;
                    }
                }
            } catch (IOException | InterruptedException e) {
                System.out.println("Мы отключились от сервера");
                //e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Thread getInputThread() {
        return new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    String str = in.readUTF();
                    System.out.println(str);
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        });
    }
}
