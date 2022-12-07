package Socket;

import java.io.IOException;
import java.net.Socket;

public class ProvaClientStr {
    public static void main(String[] args) throws IOException{
        ClientStr Client = new ClientStr();
        Socket Server = Client.connect();
        Client.send(Server);
        Client.recive(Server);
    }
    
}

