package edu.upc.dsa.classes;

import java.util.ArrayList;
import java.util.List;

public class Lector {

    // 🔢 Comptador d'IDs automàtic
    private static int nextId = 1;

    // 🧱 Atributs
    private int id;
    private String nom;
    private String cognom1;
    private String cognom2;
    private String dni;
    private String datanaixement;
    private String llocnaixement;
    private String adreca;

    private List<Prestec> prestecs;

    // 🔧 Constructor buit (necessari per frameworks o JSON)
    public Lector() {
        this.id = nextId++;
        this.prestecs = new ArrayList<>();
    }

    // 🔧 Constructor complet
    public Lector(String nom, String cognom1, String cognom2, String dni,String datanaixement, String llocnaixement, String adreca) {
        this.id = nextId++;
        this.nom = nom;
        this.cognom1 = cognom1;
        this.cognom2 = cognom2;
        this.dni = dni;
        this.datanaixement = datanaixement;
        this.llocnaixement = llocnaixement;
        this.adreca = adreca;
        this.prestecs = new ArrayList<>();
    }

    public void afegirPrestec(Prestec prestec) {
        this.prestecs.add(prestec);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        this.cognom1 = cognom1;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDatanaixement() {
        return datanaixement;
    }

    public void setDatanaixement(String datanaixement) {
        this.datanaixement = datanaixement;
    }

    public String getLlocnaixement() {
        return llocnaixement;
    }

    public void setLlocnaixement(String llocnaixement) {
        this.llocnaixement = llocnaixement;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    // 🧾 Representació del client
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", cognom1='" + cognom1 + '\'' +
                ", cognom2='" + cognom2 + '\'' +
                ", dni='" + dni + '\'' +
                ", datanaixement='" + datanaixement + '\'' +
                ", llocnaixement='" + llocnaixement + '\'' +
                '}';
    }
}
