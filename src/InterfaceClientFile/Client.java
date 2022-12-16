package InterfaceClientFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
  public static void main(String[] args) {
    try {
      // Crea un Socket per stabilire una connessione con il server sulla porta 8000
      Socket socket = new Socket("localhost", 8000);

      // Ottieni l'output stream dal socket
      OutputStream outputStream = socket.getOutputStream();

      // Specifica la cartella di inizio da cui prelevare i file
      File folder = new File("W:\\prova");

      // Ottieni l'elenco di tutti i file nella cartella di inizio
      File[] files = folder.listFiles();

      // Scorri l'elenco dei file e inviali al server, uno alla volta
      for (File file : files) {
        // Invia il nome del file al server
        byte[] fileNameBytes = file.getName().getBytes();
        outputStream.write(fileNameBytes.length);
        outputStream.write(fileNameBytes);

        // Apri il file da inviare
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

        // Invia il file al server, chunk per chunk
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
          outputStream.write(buffer, 0, bytesRead);
        }

        // Chiudi il file
        bufferedInputStream.close();
      }

      // Invia un segnale di fine trasmissione al server
      outputStream.write(-1);

      // Chiudi il socket
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
