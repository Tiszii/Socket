/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InterfaceServer;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerThClient implements Runnable {

    Socket c1;
    Server server;
    Socket[] clients;
    public Boolean running = true;

    public ServerThClient(Server server, Socket[] clients, Socket c1) {
        this.clients = clients;
        this.server = server;
        this.c1 = c1;
    }

    @Override
    public void run() {
        while (running) {
            try {
                String s = server.receive(c1);

                if ("close()".equals(s)) {
                    server.out.modifica("[ClientThread]: Connessione chiusa da " + c1.getInetAddress());
                    running = false;

                    for (int i = 0; clients[i] != null; i++) {

                        if (clients[i].equals(c1)) {
                            try {
                                clients[i].close();

                                for (int j = i; clients[j + 1] != null; j++) {
                                    clients[j] = clients[j + 1];
                                    clients[j + 1] = null;
                                }

                            } catch (IOException ex) {
                            }
                        }
                    }
                } else {
                    server.out.modifica("[ClientThread]: " + c1.getInetAddress() + ": " + s);
                    for (int i = 0; clients[i] != null; i++) {
                        if (!clients[i].equals(c1)) {
                            server.send(clients[i], s);
                        }
                    }
                }
            } catch (IOException | ThreadDeath | java.util.NoSuchElementException ex) {
                running = false;
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerThClient.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
