package edu.upc.dsa.classes;

import java.time.LocalDateTime;

public class Moviment {
    // 🧱 Atributs
    private int id;
    private String tipus;
    private double importTransaccio;
    private String compteOrigen;
    private String compteDesti;
    private LocalDateTime datetime;

    // 🔧 Constructor buit (necessari per frameworks o JSON)
    public Moviment() {
    }

    // 🔧 Constructor complet
    public Moviment(int id, String tipus, double importTransaccio, String compteOrigen, String compteDesti, LocalDateTime datetime) {
        this.id = id;
        this.tipus = tipus;
        this.importTransaccio = importTransaccio;
        this.compteOrigen = compteOrigen;
        this.compteDesti = compteDesti;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public double getImportTransaccio() {
        return importTransaccio;
    }

    public void setImportTransaccio(double importTransaccio) {
        this.importTransaccio = importTransaccio;
    }

    public String getCompteOrigen() {
        return compteOrigen;
    }

    public void setCompteOrigen(String compteOrigen) {
        this.compteOrigen = compteOrigen;
    }

    public String getCompteDesti() {
        return compteDesti;
    }

    public void setCompteDesti(String compteDesti) {
        this.compteDesti = compteDesti;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

}

