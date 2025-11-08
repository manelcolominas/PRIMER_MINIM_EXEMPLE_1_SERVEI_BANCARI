package edu.upc.dsa.classes;

import java.util.ArrayList;

public class Llibre {

    private static int nextId = 1;
    // 🧱 Atributs
    private int id;
    private String ISBN;
    private String titol;
    private String editorial;
    private String anypublicacio;
    private String numeroedicio;
    private String autor;
    private String tematica;
    private int numexemplars;

    // 🔧 Constructor buit (necessari per frameworks o JSON)
    public Llibre() {
        this.id = nextId++;
    }

    // 🔧 Constructor complet
    public Llibre(String ISBN, String titol,String editorial, String anypublicacio, String numeroedicio, String autor, String tematica) {
        this.id = nextId++;
        this.ISBN = ISBN;
        this.titol = titol;
        this.editorial = editorial;
        this.anypublicacio = anypublicacio;
        this.numeroedicio = numeroedicio;
        this.autor = autor;
        this.tematica = tematica;
        this.numexemplars = 0;
    }

    public void incrementarExemplars() {
        numexemplars++;
    }

    public void decrementarExemplars() {
        numexemplars--;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAnypublicacio() {
        return anypublicacio;
    }

    public void setAnypublicacio(String anypublicacio) {
        this.anypublicacio = anypublicacio;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNumeroedicio() {
        return numeroedicio;
    }

    public void setNumeroedicio(String numeroedicio) {
        this.numeroedicio = numeroedicio;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public int getNumexemplars() {
        return numexemplars;
    }

    public void setNumexemplars(int numexemplars) {
        this.numexemplars = numexemplars;
    }

    // 🧾 Representació del client
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", titol='" + titol + '\'' +
                ", editorial='" + editorial + '\'' +
                ", anypublicacio='" + anypublicacio + '\'' +
                ", numeroedicio='" + numeroedicio + '\'' +
                ", autor='" + autor + '\'' +
                ", tematica='" + tematica + '\'' +
                ", numexemplars=" + numexemplars +
                '}';
    }
}

