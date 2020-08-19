/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NIASS
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactStorageCsv implements ContactStorage {

    String chemin = ".\\Csv\\";

    @SuppressWarnings("empty-statement")

    public Contact creercontact() {
        Scanner sc = new Scanner(System.in);

        String name;
        do {
            System.out.println("Entrez votre prenom et nom");
            name = sc.nextLine();
        } while (name == "");
        String phone;
        do{
            System.out.println("Entrer votre numero de telephone");
            phone = sc.nextLine();
        } while (phone == "");
        phone = Regex(phone, "[\\D]");
        System.out.println("Entrez votre email");
        String email = sc.nextLine();
        email = Regex(email, "^[\\w-\\+]+(\\.[\\w]+)@[\\w-]+(\\.[\\w]+)(\\.[a-z]{2,})$");
        System.out.println("Entrez votre adresse");
        String address = sc.nextLine();;
        Contact a = new Contact(name, phone, email, address);
        if (a.getEmail() == "") {
            a.setEmail(" ");
        }
        if (a.getAddress() == "") {
            a.setAddress(" ");
        }
        return a;
    }

    public String Regex(String ch, String pat) {
        Scanner sc = new Scanner(System.in);
        Pattern pattern = Pattern.compile(pat);
        Matcher matcher = pattern.matcher(ch);
        while (matcher.find()) {
            System.out.println("Verifier il y'a une erreur\nVeuillez entrer a nouveau ");
            ch = sc.nextLine();
            matcher = pattern.matcher(ch);
        }
        return ch;
    }

    @Override
    public void save(Contact a) {
        boolean bol = false;
        ArrayList<String[]> st = new ArrayList<>();
        String[] st1 = {a.getName(), a.getPhone(), a.getEmail(), a.getAddress()};
        st.add(st1);
        File f = new File(chemin);
        f.mkdirs();
        try (FileWriter writer = new FileWriter(chemin + "contact.csv", true)) {

            for (String[] strings : st) {
                for (int i = 0; i < strings.length; i++) {
                    writer.append(strings[i]);
                    if (i < (strings.length - 1)) {
                        writer.append(";");
                    }
                }
                writer.append(System.lineSeparator());
            }
            bol = true;
            writer.flush();
        } catch (IOException e) {
            System.err.println("Operation echouee");
            bol = false;
        }
        if (bol) {
            System.out.println("Contact enregistrer avec succés...");
        } else {
            System.err.println("Operation echouee, Le contact n'est pas enregistré");
        }
    }

    //Cette fonction est la meme que la fonction save mais n'affiche qu'un message lorsqu'il y'a une erreur 
    public void save1(Contact a) {

        ArrayList<String[]> st = new ArrayList<>();
        String[] st1 = {a.getName(), a.getPhone(), a.getEmail(), a.getAddress()};
        st.add(st1);
        File f = new File(chemin);
        f.mkdirs();

        try (FileWriter writer = new FileWriter(chemin + "contact.csv", true)) {

            for (String[] strings : st) {
                for (int i = 0; i < strings.length; i++) {
                    writer.append(strings[i]);
                    if (i < (strings.length - 1)) {
                        writer.append(";");
                    }
                }
                writer.append(System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Operation echouee");
        }

    }

    @Override
    public void update(Contact c2, int in) {
        File f = new File(chemin + "Contact.csv");
        ContactStorageCsv stodel = new ContactStorageCsv();
        ArrayList<Contact> condel = stodel.list();
        f.delete();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < condel.size(); i++) {
            if (condel.get(i) == condel.get(in)) {
                System.out.println("Veuillez entrez le nouveau nom:(" + condel.get(in).getName() + ")");
                String nom1 = sc.nextLine();
                nom1 = Regex(nom1, "[^a-zA-Z ]");
                if (nom1 == "") {

                } else {
                    condel.get(in).setName(nom1);
                }
                System.out.println("Veuillez entrez le nouveau numero:(" + condel.get(in).getPhone() + ")");
                String phone1 = sc.nextLine();
                phone1 = Regex(phone1, "[\\D]");
                if (phone1 == "") {

                } else {
                    condel.get(in).setPhone(phone1);
                }
                System.out.println("Veuillez entrez le nouveau email:(" + condel.get(in).getEmail() + ")");
                String em1 = sc.nextLine();
                em1 = Regex(em1, "([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");
                if (em1 == "") {

                } else {
                    condel.get(in).setEmail(em1);
                }
                System.out.println("Veuillez entrez la nouvelle adresse:(" + condel.get(in).getAddress() + ")");
                String add1 = sc.nextLine();
                if (add1 == "") {

                } else {
                    condel.get(in).setAddress(add1);
                }
            }

            stodel.save1(condel.get(i));
        }
        System.out.println("Les modifications ont été bien prises en compte...");
    }

    @Override
    public void delete(int in) {
        int chx = 0;
        File f = new File(chemin + "Contact.csv");
        ContactStorageCsv stodel = new ContactStorageCsv();
        ArrayList<Contact> condel = stodel.list();
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez-vous vraiment supprimer cet element   Oui(1) ou Non(2)");
        chx = stodel.choice(0, 2);

        if (chx == 1) {
            f.delete();
            for (int i = 0; i < condel.size(); i++) {
                if (condel.get(i) == condel.get(in)) {
                    condel.remove(i);

                }
            }
            for (int i = 0; i < condel.size(); i++) {
                stodel.save1(condel.get(i));
            }

        } else if (chx == 2) {
            System.out.println("Contact non supprimé");
        }
        System.out.println("Contact supprimé avec succés...");
    }

    @Override
    public ArrayList<Entry<Integer, Contact>> search(String searchTerm){
        HashMap<Integer, Contact> ContacFound = new HashMap<>();
        File f = new File(chemin + "Contact.csv");
        ContactStorageCsv stodelupd = new ContactStorageCsv();

        ArrayList<Contact> condel = stodelupd.list();
        int[] lisindex = {};
        for (int i = 0; i < condel.size(); i++) {
            Contact co = condel.get(i);
            if (co.getName().toLowerCase().contains(searchTerm) || co.getPhone().contains(searchTerm) || co.getAddress().toLowerCase().contains(searchTerm) || co.getEmail().toLowerCase().contains(searchTerm)) {
                ContacFound.put(i, co);
            }
        }
        
        ArrayList<Entry<Integer, Contact>> resultsearch = new ArrayList<>(ContacFound.entrySet());
        return resultsearch;
    }
    
    @Override
    public ArrayList<Contact> list() {
        ArrayList<Contact> licon = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(chemin + "contact.csv"))) {
            ArrayList<String[]> list = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] array = line.split(";");
                list.add(array);
            }
            for (int i = 0; i < list.size(); i++) {
                String[] st0 = list.get(i);
                Contact c2 = new Contact(st0[0], st0[1], st0[2], st0[3]);

                licon.add(c2);
            }

            return licon;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public int choice(int inf, int sup) {
        Scanner sc = new Scanner(System.in);
        int c = 0;
        do {
            try {
                c = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                System.err.println("Vous n'avez pas saisi de nombre");

            }
            if (c <= inf || c > sup) {
                System.out.println("Entrer un choix entre  " + (inf + 1) + " et " + sup);
                c = sup + 1;
            }

        } while (c <= inf || c > sup);

        return c;
    }

    public void menu(int chxf, int numf, ArrayList<Contact> cof){
        ContactStorageCsv csv = new ContactStorageCsv();
        if (chxf == 1) {
            csv.update(cof.get(numf - 1), numf - 1);
        } else if (chxf == 2) {
            csv.delete(numf - 1);
        } else if (chxf == 3) {

        } else {
            System.exit(0);
        }

    }
}
