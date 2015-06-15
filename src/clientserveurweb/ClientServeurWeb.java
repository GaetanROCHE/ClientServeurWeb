/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserveurweb;

import clientserveurweb.serveur.Serveur;

/**
 *
 * @author Gaëtan
 */
public class ClientServeurWeb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Serveur srv = new Serveur();
        srv.run();
        
    }
    
}
