package InterfacciaClient;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThReceive implements Runnable {

    Socket server;
    Client client;
    Boolean running = true;
    Stampa out;
    public ClientThReceive(Socket server, Client client,Stampa out) {
        this.server = server;
        this.client = client;
        this.out=out;
    }

    @Override
    public void run() {
        while (running) {
            try {
                client.receive(server,out);
            } catch (IOException | java.util.NoSuchElementException ex) {
                try {
                    out.modifica("[Server]: Il server ha chiuso la connessione");
                    running = false;
                    System.exit(0);
                } catch (InterruptedException ex1) {
                    Logger.getLogger(ClientThReceive.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientThReceive.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
