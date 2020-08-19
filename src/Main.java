/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NIASS
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        int choix = 0, ba = 0;
      //  boolean bol = true;
        Scanner sc = new Scanner(System.in);
        ContactStorageCsv csv = new ContactStorageCsv();
        File f = new File(".\\Csv\\Contact.csv");
        do {
            System.out.println("!!!-------------------!------------------CONTACTS MANAGER---------------!----------------------!!!");
            System.out.println("1---Enregrister un numero \n");
            System.out.println("2---Afficher les numeros \n");
            System.out.println("3---Rechercher contact \n");
            System.out.println("4---Arrêter les opérations\n");
            choix = csv.choice(0, 4);
            switch (choix) {
                case 1:
                    Contact cs = csv.creercontact();
                    csv.save(cs);
                    break;
                case 2:
                    ArrayList<Contact> con = new ArrayList<>();
                    if (!f.exists()) {
                        System.err.println("Il n'y a aucun contact sauvegardé");
                        System.exit(0);
                    } else {
                        con = csv.list();
                        for (int i = 0; i < con.size(); i++) {
                            Contact C1 = con.get(i);
                            System.out.println((i + 1) + "--" + C1.getName() + "  " + C1.getPhone());
                        }
                    }
                    System.out.println("Choisissez un numero pour afficher ses informations");
                    int num = 0;
                    num = csv.choice(0, con.size());
                    System.out.println("------------------------------");
                    System.out.println(con.get(num - 1).displayContact()
                            + "\n------------------------------\n"
                            + "1-- Modifier le contact \n "
                            + "2--Supprimer le contact\n"
                            + "3--Retourner a l'accueil\n"
                            + "4--Quitter");
                    int chx = 0;
                    chx = csv.choice(0, 4);
                    if (chx == 1) {
                        csv.update(con.get(num - 1), num - 1);
                    } else if (chx == 2) {
                        csv.delete(num - 1);
                    } else if (chx == 3) {

                    } else {
                        System.exit(0);
                    }

                    break;

                case 3:
                    if (!f.exists()) {

                        System.err.println("Il n'y a aucun contact sauvegardé");
                        System.exit(0);
                    } else {

                        ArrayList<Entry<Integer, Contact>> cores = new ArrayList<Entry<Integer, Contact>>();
                        System.out.println("Entrer une information sur le contact a rechercher");
                        String research;
                        research = sc.nextLine();
                        cores = csv.search(research.toLowerCase());

                        ArrayList<Contact> cof = csv.list();

                        for (int i = 0; i < cores.size(); i++) {
                            Entry<Integer, Contact> C1 = cores.get(i);
                            System.out.println(C1.getKey() + 1 + "--" + C1.getValue().getName() + " " + C1.getValue().getPhone());

                        }

                        System.out.println("Choisissez un numero pour afficher ses informations");
                        int numf = 0;
                        int chxf = 0;

                        if (cores.size() == 1) {

                            System.out.println("\n" + cores.get(0).getValue().displayContact() + "\n\n------------------------------\n"
                                    + "1-- Modifier le contact\n"
                                    + "2-- Supprimer le contact\n"
                                    + "3--Retourner a l'accueil\n"
                                    + "4--Quiiter");
                            chxf = csv.choice(0, 4);
                            csv.menu(chxf, 1, cof);
                            break;

                        } else if (cores.size() == 0) {
                            System.out.println("Aucun contact trouvé");
                            continue;
                        } else {
                            numf = csv.choice(-1, (cores.size()));
                            System.out.println(cores.get(numf - 1).getValue().displayContact() + "\n\n------------------------------\n1-- Modifier le contact\n 2- Supprimer le contact\n3--Retourner a l'accueil\n4--Quiiter");
                        }

                        chxf = csv.choice(0, 4);
                        csv.menu(chxf, numf, cof);
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vous devez choisir entre 1 et 4");
            }
            System.out.println("\nAppuyer sur 0 pour quitter et 1 pour continuer");
            ba = csv.choice(-1, 1);
            if (ba == 0) {
                System.exit(0);
            }
        } while (true);
    }

}