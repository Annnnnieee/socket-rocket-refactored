import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class Server implements Flow.Publisher, Runnable{
    private Connector connector;
    private Messenger messenger;
    private List<Flow.Subscriber> subscribers;

    private ConnectionInfo connectionInfo;

    public Server(Connector connector, Messenger messenger){
        this.connector = connector;
        this.messenger = messenger;
        subscribers = new ArrayList<>();
    }

    public void subscribe(Flow.Subscriber subscriber) {
        subscribers.add(subscriber);
    }


    public void run() {
        try {
            Socket socket = connector.connect(connectionInfo);
            subscribers.forEach((subscriber)->subscriber.onNext(new ConnectionInfo(socket.getPort(), socket.getInetAddress())));
            messenger.handleMessages(socket);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

