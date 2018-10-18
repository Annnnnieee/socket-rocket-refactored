import java.net.InetAddress;

public class ConnectionInfo {
    private int port;
    private InetAddress ip;

    public ConnectionInfo(int port, InetAddress ip){
        this.port = port;
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }
}
