package Censura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProvaCens {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String percorso1 = "W:\\Tipsit\\Socket\\src\\Censura\\paroleDaCensurare.txt", percorso2 = "W:\\Tipsit\\Socket\\src\\Censura\\stringaDaLeggere.txt", percorso3 = "W:\\Tipsit\\Socket\\src\\Censura\\output.txt";

        List<String> paroleDaCensurare = new ArrayList<String>();
        String stringaDaConvertire = "";
        /*
        System.out.print("Inserisci il percorso del file per le parole da censurare : ");
        percorso1 = scan.nextLine();
        System.out.print("\nInserisci il percorso del file ove censurare le parole : ");
        percorso2 = scan.nextLine();
        System.out.print("\nInserisci il percorso del file ove scrivere l'output : ");
        percorso3 = scan.nextLine();
         */

        
        //leggo le parole da censurare 
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(percorso1));
            String line;
            try {
                line = reader.readLine();
                while (line != null) {
                    paroleDaCensurare.add(line);
                    line = reader.readLine();
                }
            } catch (IOException ex) {
                System.err.println("Errore nella lettura del file");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Errore nell'apertura del file");
        }

        
        
        //leggo la stringa da convertire
        try {
            reader = new BufferedReader(new FileReader(percorso2));
            String line;
            try {
                line = reader.readLine();
                while (line != null) {
                    stringaDaConvertire+=line+"\n";
                    line = reader.readLine();
                }
            } catch (IOException ex) {
                System.err.println("Errore nella lettura del file");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Errore nell'apertura del file");
        }
        
        
        for(String x : paroleDaCensurare){
            if(stringaDaConvertire.contains(x)){
                String s ="";
                for(int i =0;i<x.length();i++){
                    s+="*";
                }
                stringaDaConvertire=stringaDaConvertire.replaceAll(x, s);
            }
        }

        
        
        
        //scrivo nel file la stringa censurata
        //scrivendo con questo metodo , il contenuto del file viene eliminato totalmente prima della scrittura
        System.out.print(stringaDaConvertire);
        PrintWriter out = null;
        try {
            out = new PrintWriter(percorso3);
            out.print(stringaDaConvertire);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}
