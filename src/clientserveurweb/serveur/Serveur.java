/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserveurweb.serveur;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Gaëtan
 */
public class Serveur {
    ServerSocket server;
    Socket client;
    InputStream in;
    OutputStream out;

    public Serveur(){
        try {
            server = new ServerSocket(80);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void run(){
        try {
            client = server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            in = client.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            byte[] buffer = new byte[255];
            String data;
            int i = in.read(buffer);
            while ( i != -1) {
                System.out.println("----Code:" + i + "----");
                data = new String(buffer, "UTF-8");
                System.out.println(data);
                System.out.println("----Fin Buffer----");
                if(i < 255)
                    break;
                i = in.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String res = "Test";

        try {

            out = client.getOutputStream();
            out.write(res.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
