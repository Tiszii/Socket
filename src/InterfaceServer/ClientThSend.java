
package InterfaceServer;

import java.io.IOException;
import java.net.Socket;

public class ClientThSend implements Runnable {

    Socket server;
    Client client;

    public ClientThSend(Socket server, Client client) {
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {

            try {
                client.send(server);
            } catch (IOException ex) {
                
            }

        }
    }
}