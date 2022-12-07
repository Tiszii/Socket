/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThreadSocket;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThReceive implements Runnable {

    Socket server;
    Client client;
    Boolean running = true;

    public ClientThReceive(Socket server, Client client) {
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {
        while (running) {
            try {
                client.receive(server);
            } catch (IOException | java.util.NoSuchElementException ex) {
                System.out.println("[server]: Il server ha chiuso la connessione");
                running = false;
                System.exit(0);
            }

        }
    }
}
