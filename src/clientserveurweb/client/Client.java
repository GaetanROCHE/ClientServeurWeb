/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserveurweb.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author Gaëtan
 */
public class Client {
    
    
    public static void demandeWeb(String URL, String nomFichier){
        try{
            String requeteHTTP = "GET /"+nomFichier+" HTTP/1.1\r\n";
            String host = "Host: " + URL + "\r\n\r\n";

            if(nomFichier == null || nomFichier == "")
                nomFichier = "index.html";
            FileOutputStream fichier = new FileOutputStream(new File("responses/"+nomFichier));

            Socket sc = new Socket();
            sc.connect(new InetSocketAddress(URL, 80));
            DataOutputStream output = new DataOutputStream(sc.getOutputStream());
            System.out.println(requeteHTTP + host);
            output.writeBytes(requeteHTTP + host);
            output.flush();
            byte[] reponse = new byte[512];
            sc.getInputStream().read(reponse);
            reponse = new byte[1];
            while(sc.getInputStream().read(reponse) != -1){
                fichier.write(reponse);
                reponse =  new byte[1];
            }
            fichier.close();
            sc.close();
        }
        catch(IOException e){
            System.out.println("Server not responding");
        }
        
    }
    
    public static void affichePage(String nomFichier){
        try{
            FileInputStream fichier = new FileInputStream(new File("responses/"+nomFichier));
            byte[] data = new byte[1];
            while(fichier.read(data) != -1){
                System.out.print(new String(data));
            }
            fichier.close();
        }
        catch(FileNotFoundException e){}
        catch(IOException e){}

    }
}
