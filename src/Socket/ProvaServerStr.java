package Socket;

import java.io.IOException;
import java.net.*;

public class ProvaServerStr {

    public static void main(String[] args) throws IOException {
       ServerStr server = new ServerStr();
       Socket Client= server.listen();
       String s = server.recive(Client);
       server.send(Client, s.toUpperCase());
    }
}
