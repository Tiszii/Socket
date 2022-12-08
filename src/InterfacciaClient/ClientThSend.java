/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfacciaClient;

import java.io.IOException;
import java.net.Socket;

public class ClientThSend implements Runnable {

    Socket server;
    Client client;
    String sendStr;

    public ClientThSend(Socket server, Client client, String sendStr) {
        this.server = server;
        this.client = client;
        this.sendStr = sendStr;
    }

    @Override
    public void run() {
        while (true) {
            try {
                client.send(server,sendStr);
            } catch (IOException ex) {
            }
        }
    }
    
    public void invia() throws IOException{
        client.send(server,sendStr);
    }
}
