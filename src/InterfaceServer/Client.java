package InterfaceServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public int port;
    public String host;

    public Client(String host, int port){
      this.port = port;
      this.host = host;  
    }
    
    public Socket connect() throws IOException{
        Socket Client = new Socket(InetAddress.getByName(host),port);
        return Client;
    }
    
    public void send(Socket Server) throws IOException{
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        DataOutputStream out = new DataOutputStream(Server.getOutputStream());
        out.writeBytes(str+"\n");
    }
    
    public void receive(Socket Server)throws IOException{
        Scanner scan = new Scanner(Server.getInputStream());
        String str = scan.nextLine();
    }
}

