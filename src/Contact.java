/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author NIASS
 */
public class Contact {
private String name;
private String phone;
private String email;
private String address;

    public Contact(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
      public String displayContact(){
        
          if(this.address==" " && (this.email=="") ){
            return "Nom : "+this.name+"\nPhone : "+this.phone+"";
            }
        else if(this.address=="" && ( this.email!=null))
        {
            return "Nom : "+this.name+"\nPhone : "+this.phone+"\nEmail :"+this.email+"";
        }
        else if(this.address!=null && (this.email==""))
        {
            return "Nom : "+this.name+"\nPhone : "+this.phone+"\nAdresse : "+this.address+"";
        }
        else
            return "Nom : "+this.name+"\nPhone : "+this.phone+"\nEmail :"+this.email+"\nAdresse : "+this.address+"";      
        } 
 }