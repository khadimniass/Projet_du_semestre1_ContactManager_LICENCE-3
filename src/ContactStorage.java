
import java.util.ArrayList;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NIASS
 */
public interface ContactStorage {

public void update(Contact c2,int in);
public void delete(int in);
public void save(Contact c);
public ArrayList<Contact> list();
public ArrayList<Map.Entry<Integer, Contact>> search(String searchTerm);
}
