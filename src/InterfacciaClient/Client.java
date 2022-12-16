package InterfacciaClient;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public int port;
    public String host;
    public String nome;

    public Client(String host, int port,String nome){
      this.port = port;
      this.host = host;
      this.nome=nome;
    }
    
    public Socket connect() throws IOException{
        Socket Client = new Socket(InetAddress.getByName(host),port);
        return Client;
    }
    
    public void send(Socket Server,String str) throws IOException{
        DataOutputStream out = new DataOutputStream(Server.getOutputStream());
        out.writeBytes("["+nome+"] "+str+"\n");
    }
    
    public void receive(Socket Server,Stampa out)throws IOException, InterruptedException{
        Scanner scan = new Scanner(Server.getInputStream());
        String str = scan.nextLine();
        out.modifica(str);
    }
}

