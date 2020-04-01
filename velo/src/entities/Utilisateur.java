/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;





/**
 *
 * @author ONS
 */
public class Utilisateur {

    private int id_user;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private int age;
    private Sexe sexe;
    private Role role;
    private String date_creation;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String prenom, String login, String password, int age, Sexe sexe, Role role, String date_creation) {
        this.id_user = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.age = age;
        this.sexe = sexe;
        this.role = role;
        this.date_creation = date_creation;
    }

    public int getId() {
        return id_user;
    }

    public void setId(int id) {
        this.id_user = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", password=" + password + ", age=" + age + ", sexe=" + sexe + ", role=" + role + ", date_creation=" + date_creation + '}';
    }

}
