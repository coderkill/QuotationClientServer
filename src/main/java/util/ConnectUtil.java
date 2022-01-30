package util;

import java.io.IOException;
import java.net.Socket;

public class ConnectUtil {

    public static Socket keepConnecting(String addressLocal, int port) {
        Socket socket = null;
        while (true) {
            try {
                socket = new Socket(addressLocal, port);
                break;
            } catch (IOException e) {
                System.out.println("Trying to connect again");
            }
        }
        return socket;
    }
}
