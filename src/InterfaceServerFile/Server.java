package InterfaceServerFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) {
    try {
      // Crea un ServerSocket in ascolto sulla porta 8000
      ServerSocket serverSocket = new ServerSocket(8000);

      // Accetta una connessione da un client
      Socket clientSocket = serverSocket.accept();

      // Ottieni l'input stream dal socket del client
      InputStream inputStream = clientSocket.getInputStream();

      // Ricevi i file dal client, uno alla volta, fino a quando non viene ricevuto un segnale di fine trasmissione
      while (true) {
        // Ricevi il nome del file
        int fileNameLength = inputStream.read();
        byte[] fileNameBuffer = new byte[fileNameLength];
        inputStream.read(fileNameBuffer);
        String fileName = new String(fileNameBuffer);

        // Apri il file in cui verrà salvato il file ricevuto
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

        // Ricevi il file dal client, chunk per chunk, fino a quando non viene ricevuto un segnale di fine trasmissione
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1 && buffer[0] != -1) {
          bufferedOutputStream.write(buffer, 0, bytesRead);
        }
        // Chiudi il file
        bufferedOutputStream.close();

        // Verifica se è stato ricevuto un segnale di fine trasmissione
        if (buffer[0] == -1) {
          break;
        }
      }

      // Chiudi il socket del client
      clientSocket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
