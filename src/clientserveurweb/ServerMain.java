package clientserveurweb;

import clientserveurweb.serveur.Serveur;

/**
 * Created by Guyl.B on 23/06/15.
 */
public class ServerMain {
    public static void main(String[] args){
        Serveur srv = new Serveur();
        srv.run();
    }
}

