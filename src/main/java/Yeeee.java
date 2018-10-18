import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Yeeee {
    public static void main(String[] args) throws IOException {
        Connector serverConnector = connectionInfo -> {
            ServerSocket server = new ServerSocket(connectionInfo.getPort());
            Socket client = server.accept();
            System.out.println("server connected to: " + client.getInetAddress());
            return client;
        };
        Connector clientConnector = connectionInfo -> {
            Socket socket = new Socket(connectionInfo.getIp(), connectionInfo.getPort());
            System.out.println("Client connected to: " + connectionInfo.getIp());
            return socket;
        };

        Messenger serverMessenger = socket -> {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                System.out.println(in.readLine());
            }
        };
        Messenger clientMessenger = socket -> {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner reader = new Scanner(System.in);
            while(true){
                String message = reader.nextLine();
                out.println(message);
            }
        };

        Server sender = new Server(serverConnector, serverMessenger);
        Client receiver = new Client(clientConnector, clientMessenger);
        Thread senderThread = new Thread(sender);
        Thread receiverThread = new Thread (receiver);
        senderThread.start();
        receiverThread.start();
    }
}
