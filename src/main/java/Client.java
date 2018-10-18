import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Flow;

public class Client implements Flow.Subscriber, Runnable  {
    private Connector connector;
    private Messenger messenger;
    private Thread userInput;
    private ConnectionInfo connectionInfo;

    public Client(Connector connector, Messenger messenger){
        this.connector = connector;
        this.messenger = messenger;
    }

    public void run() {
        try {
            userInput.start();
            Socket socket = connector.connect(connectionInfo);
            messenger.handleMessages(socket);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onSubscribe(Flow.Subscription subscription) {

    }

    public void onNext(Object item) {
        try {
            connectionInfo = (ConnectionInfo)item;
            userInput.interrupt();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onError(Throwable throwable) {
        System.out.println("well shit, Client subscription error");
        throwable.printStackTrace();
    }

    public void onComplete() {

    }
}
