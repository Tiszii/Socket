package ThreadSocket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {   
    public int port;
    public ServerSocket server;

    public Server(int port) throws IOException{
      this.port = port;
      server = new ServerSocket(port);
    }
  
    public Socket listen() throws IOException{
        Socket Client = null;
        Client = server.accept();
        System.out.println("[ClientThread]: Nuova connessione da " + Client.getInetAddress());
        return Client;
    }
    
    public String receive(Socket Client) throws IOException {
        Scanner scan = new Scanner(Client.getInputStream());
        String str = scan.nextLine();
        return str;
    }
    
    public void send(Socket Client, String str) throws IOException {
        DataOutputStream out = new DataOutputStream(Client.getOutputStream());
        out.writeBytes(str + "\n"); 
    }
}
