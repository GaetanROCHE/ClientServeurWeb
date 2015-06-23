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
            FileOutputStream fichier = new FileOutputStream(new File("page.html"));
            String requeteHTTP = "GET /"+nomFichier+" HTTP/1.1\r\n";
            String host = "Host: " + URL + "\r\n\r\n";
            /*requeteHTTP = requeteHTTP + "Accept: text/html, image/gif, image/jpeg,\r\n";
            requeteHTTP = requeteHTTP + "Accept-Language: fr\r\n";
            requeteHTTP = requeteHTTP + "\n";*/
            
            Socket sc = new Socket();
            sc.connect(new InetSocketAddress(URL, 80));
            DataOutputStream output = new DataOutputStream(sc.getOutputStream());
            System.out.println(requeteHTTP + host);
            output.writeBytes(requeteHTTP + host);
            output.flush();
            byte[] reponse = new byte[512];
            while(sc.getInputStream().read(reponse) != -1){
                fichier.write(reponse);
            }
            //fichier.write(reponse);
        }
        catch(IOException e){
            System.out.println("erreur à la con");
        }
        
    }
    
    public static void affichePage(){
        try{
            FileInputStream fichier = new FileInputStream(new File("page.html"));
            byte[] data = new byte[1];
            while(fichier.read(data) != -1){
                System.out.print(new String(data));
            }
        }
        catch(FileNotFoundException e){}
        catch(IOException e){}
    }
}
