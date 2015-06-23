package clientserveurweb;

import clientserveurweb.client.Client;
import sun.misc.Cleaner;

import java.util.Scanner;

/**
 * Created by Guyl.B on 08/06/15.
 */

public class Console {

    private static String command;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isRunning = true;
    private static String[] args;

    public static void run(){

        System.out.println("\n<===== Bienvenue sur votre client web ! =====>\n");
        onHelp();

        while(isRunning){
            args = null;
            System.out.print("$ ");
            command = scanner.nextLine();
            if      (command.matches("quit(.*)"))   { onQuit(); }
            else if (command.matches("help(.*)"))   { onHelp(); }
            else if (command.matches("print(.*)"))  { onPrint(); }
            else if (command.matches("get(.*)"))    { onGet(); }
            else { System.out.println("Commande non reconnue"); }
        }
    }

    private static void onGet(){
        args = command.split(" ");
        if(args.length < 2) {
            System.out.print("Erreur: Trop peu d'arguments\n" +
                    "Requis: 1 - Passés: " + (args.length - 1) + "\n" +
                    "Usage : get <addresse serveur> <fichier distant>\n");
            return;
        }
        if(args.length < 3)
            Client.demandeWeb(args[1], "");
        else
            Client.demandeWeb(args[1], args[2]);
    }

    private static void onPrint(){
        args = command.split(" ");
        if(args.length < 2) {
            System.out.print("Erreur: Trop peu d'arguments\n" +
                    "Requis: 1 - Passés: " + (args.length - 1) + "\n" +
                    "Usage : print <fichier local>\n");
            return;
        }
        Client.affichePage(args[1]);
    }

    private static void onHelp(){
        System.out.print("get : Récupère le fichier demandé\n"+
                "print : Affiche le fichier local reçu\n"+
                "quit : Ferme le programme\n"+
                "help : Liste les commandes du programme\n");
    }


    private static void onQuit(){ isRunning = false; }

}
