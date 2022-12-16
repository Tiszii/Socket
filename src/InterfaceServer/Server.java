package InterfaceServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {   
    public int port;
    public ServerSocket server;
    public Stampa out;

    public Server(int port,Stampa out) throws IOException{
      this.port = port;
      server = new ServerSocket(port);
      this.out = new Stampa(out);
    }
  
    public Socket listen() throws IOException, InterruptedException{
        Socket Client = null;
        Client = server.accept();
        out.modifica("Nuova connessione da " + Client.getInetAddress());
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
