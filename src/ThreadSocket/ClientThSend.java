/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ThreadSocket;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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