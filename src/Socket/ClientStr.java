package Socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientStr {
    public static final int PORT = 6789;
    public static final String HOST = "localhost";
    
    public Socket connect() throws IOException{
        Socket Client = new Socket(InetAddress.getByName(HOST),PORT);
        System.out.println("Connesso al server");
        return Client;
    }
    
    public void send(Socket Server) throws IOException{
        System.out.println("Inserisci la stringa da convertire in maiuscolo");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        scan.close();
        
        DataOutputStream out = new DataOutputStream(Server.getOutputStream());
        out.writeBytes(str+"\n");
        System.out.println("String sent");
    }
    
    public void recive(Socket Server)throws IOException{
        Scanner scan = new Scanner(Server.getInputStream());
        System.out.println("Waiting for a string from the server");
        String str= scan.nextLine();
        System.out.println("Stringa ricevuta : "+str);
    }
}