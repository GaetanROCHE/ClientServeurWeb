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
        String nameFile = "index.html";
        FileInputStream file = null;

        try {
            String get = in.readLine();
            System.out.println(get);
            String str = null;
            while(!(str=in.readLine()).equals("")) {
                System.out.println(str);
            }

            String[] args = get.split(" ");

            if(!get.startsWith("GET")) {
                code = 401;
                nameFile = "error.html";
            }

            else{
                nameFile = args[1].substring(1);
                if(nameFile.equals(""))
                    nameFile = "index.html";
            }

            file = new FileInputStream(nameFile);

        }
        catch (IOException e1) {
            e1.printStackTrace();
        }

        String header = null;
        try {
            header = "HTTP/1.1 "+code+" OK\n" +
                    "Date: Aujourd'hui\n" +
                    "Server: Nous\n" +
                    "Content-Length:"+file.available()+"\n" +
                    "Connection: close\n" +
                    "ContentType: text/html\n" +
                    "\n";
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            out = client.getOutputStream();
            out.write(header.getBytes());
            while(file.available() > 0){
                out.write(file.read());
            }
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
