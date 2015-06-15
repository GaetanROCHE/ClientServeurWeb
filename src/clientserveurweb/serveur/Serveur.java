/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserveurweb.serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Gaëtan
 */
public class Serveur {
    ServerSocket server;
    Socket client;
    DataInputStream in;
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
            in = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            String str;
            while(!(str=in.readLine()).equals("")) {
                System.out.println(str);
            }
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

        String res = "HTTP/1.1 200 OK\n" +
                "Date: Aujourd'hui\n" +
                "Server: Nous\n" +
                "Content-Length:13\n" +
                "Connection: close\n" +
                "ContentType: text/html\n" +
                "\n" +
                "<h1>Test</h1>";

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
