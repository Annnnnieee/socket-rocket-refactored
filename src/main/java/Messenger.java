import java.io.IOException;
import java.net.Socket;

public interface Messenger {
    void handleMessages(Socket socket) throws IOException;
}