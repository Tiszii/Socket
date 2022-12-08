package InterfaceServer;


import java.io.IOException;
import java.net.Socket;

public class ServerTh implements Runnable {

    Server server;
    Socket[] clients;
    int nClients;
    public Boolean running = true;

    public ServerTh(Server server) {
        clients = new Socket[999];
        this.server = server;
        nClients = 0;
    }

    @Override
    public void run() {
        Socket Client;
        while (running) {
            Client = null;
            try {
                
                Client = server.listen();
                clients[nClients] = Client;
                nClients++;
                ServerThClient th = new ServerThClient(server, clients, Client);
                Thread ThClientSend = new Thread(th);
                ThClientSend.start();
                
            } catch (ThreadDeath | Exception e) {
                running = false;
                
                for (int i = 0; clients[i] != null; i++) {
                    try {
                        clients[i].close();
                    } catch (IOException ex) {
                    }
                }
                System.exit(0);
            }

        }

    }
    

}
