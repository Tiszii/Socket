package ThreadSocket;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("> Vuoi connetterti ad un server(1) o crearne uno(2)?");
        int opzione = scanner.nextInt();
        int porta;

        switch (opzione) {
            // Client
            case 1:

                System.out.println("> Inserisci l'indirizzo del server");
                String indirizzo = scanner.next();
                System.out.println("> Inserisci la porta del server");
                porta = scanner.nextInt();

                Client Client = new Client(indirizzo, porta);
                Socket Server = Client.connect();

                Thread ThClientSend = new Thread(new ClientThSend(Server, Client));
                ThClientSend.start();
                Thread ThClientReceive = new Thread(new ClientThReceive(Server, Client));
                ThClientReceive.start();

                System.out.println("[main]: Scrivi 'close()' per chiudere la connessione");
                
                break;

            // Server
            case 2:
                System.out.println("> Inserisci la porta del server");
                porta = scanner.nextInt();

                Server server = new Server(porta);
                Thread ThServer = new Thread(new ServerTh(server));
                ThServer.start();

                System.out.println("[main]: Scrivi 'close()' per chiudere il server");
                
                String close = "";
                
                while(!close.equals("close()")){
                    close = scanner.next();
                }
                try {
                        server.server.close();
                        ThServer.stop();
                        System.out.println("Server chiuso");
                    } catch (IOException ex) {
                        Logger.getLogger(ServerTh.class.getName()).log(Level.SEVERE, null, ex);
                    }

                break;

            default:
                System.out.println("> Opzione non valida, shutting down... <");
        }
    }
}
