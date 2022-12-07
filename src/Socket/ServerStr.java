package Socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class ServerStr {   
    public static final int PORT = 6789;
    
    public Socket listen() {
        Socket Client = null;
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server started, port : " + PORT);
            Client = server.accept();
            System.out.println("Client connected");
            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return Client;
    }
    
    public String recive(Socket Client) throws IOException {
        Scanner scan = new Scanner(Client.getInputStream());
        System.out.println("System is waiting for a string");
        String str = scan.nextLine();
        System.out.println("Recived string " + str);
        return str;
    }
    
    public void send(Socket Client, String str) throws IOException {
        DataOutputStream out = new DataOutputStream(Client.getOutputStream());
        System.out.println("Sending string : " + str);
        out.writeBytes(str + "\n");
        System.out.println("String sent");
        Client.close();          
    }
}