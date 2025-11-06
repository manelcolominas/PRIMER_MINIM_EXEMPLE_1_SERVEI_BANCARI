package edu.upc.dsa.classes;

import java.util.ArrayList;
import java.util.List;

public class Client {
    // 🧱 Atributs
    private int id;
    private String nom;
    private String cognom;
    private String email;
    private String dni;
    private String adreca;

    private List<Compte> comptes;

    // 🔧 Constructor buit (necessari per frameworks o JSON)
    public Client() {
        this.comptes = new ArrayList<>();
    }

    // 🔧 Constructor complet
    public Client(int id, String nom, String cognom, String email, String dni, String adreca) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.email = email;
        this.dni = dni;
        this.adreca = adreca;
        this.comptes = new ArrayList<>();
    }

    // ➕ Mètode per afegir un compte
    public void afegirCompte(Compte compte) {
        this.comptes.add(compte);
    }

    // 🧩 Getters i Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    // 🧾 Representació del client
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", email='" + email + '\'' +
                ", dni='" + dni + '\'' +
                ", adreca='" + adreca + '\'' +
                ", comptes=" + comptes.size() +
                '}';
    }
}
