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


        int code = 0;
        String content = new String("Test");

        try {
            String get = in.readLine();
            String str = null;
            while(!(str=in.readLine()).equals("")) {
                System.out.println(str);
            }
            /*
            if(!get.startsWith("GET"))
                code = 401;

            String[] args = get.split(" ");
            if(args[1].equals("/"))
                content = "Test";

            FileInputStream file = new FileInputStream(args[1]);
            int c;
            while((c =file.read()) != -1){
                content += c;
            }
            */
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

        String res = "HTTP/1.1 "+code+" OK\n" +
                "Date: Aujourd'hui\n" +
                "Server: Nous\n" +
                "Content-Length:"+content.length()+"\n" +
                "Connection: close\n" +
                "ContentType: text/html\n" +
                "\n" +
                content;

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
