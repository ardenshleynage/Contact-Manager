/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mod;

/**
 *
 * @author Ardenshley Nage
 */
public class Mod_mod {

    private int id;
    private String first_name;
    private String last_name;
    private int phone_number;
    private String mail_user;
    private String add_date;

    public Mod_mod(int id, String first_name, String last_name, int phone_number, String mail_user, String add_date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.mail_user = mail_user;
        this.add_date = add_date;
    }

    public Mod_mod() {

    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public String getMail_user() {
        return mail_user;
    }

    public String getAdd_date() {
        return add_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public void setMail_user(String mail_user) {
        this.mail_user = mail_user;
    }

    public void setAdd_date(String add_date) {
        this.add_date = add_date;
    }

}
