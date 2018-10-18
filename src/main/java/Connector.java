import java.io.IOException;
import java.net.Socket;

public interface Connector {
    Socket connect(ConnectionInfo connectionInfo) throws IOException;
}
