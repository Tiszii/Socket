package InterfacciaClient;

import java.util.concurrent.Semaphore;

public class Stampa {

    public String s = "";
    public javax.swing.JTextArea g;
    Semaphore sem = new Semaphore(1);

    public Stampa(javax.swing.JTextArea g) {
        this.g = g;
    }

    public Stampa(Stampa out) {
        this.s = out.s;
        this.g = out.g;
        this.sem = out.sem;
    }

    public synchronized void modifica(String str) throws InterruptedException {
        sem.acquire();
        this.s += str + "\n";
        String temp = g.getText();
        g.setText(temp+s);
        s="";
        sem.release();
    }

    public synchronized String getString() throws InterruptedException {
        return s;   
    }

}
