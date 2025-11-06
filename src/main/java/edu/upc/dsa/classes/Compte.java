package edu.upc.dsa.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Compte {
    // 🧱 Atributs
    private String IBAN;
    private double saldo;
    private String tipus; // Exemple: "corrent", "estalvi"
    private List<Moviment> moviments;

    // 🔧 Constructor buit (necessari per frameworks o JSON)
    public Compte() {
        this.saldo = 0.0;
        this.moviments = new ArrayList<>();
    }

    // 🔧 Constructor complet
    public Compte(String IBAN, double saldo, String tipus) {
        this.IBAN = IBAN;
        this.saldo = saldo;
        this.tipus = tipus;
        this.moviments = new ArrayList<>();
    }

    // 💰 Mètode per fer un dipòsit
    public boolean dipositar(double importDipositat) {
        if (importDipositat > 0) {
            this.saldo += importDipositat;

            // ✳️ Crear moviment associat
            Moviment m = new Moviment();
            m.setTipus("DIPOSIT");
            m.setImportTransaccio(importDipositat);
            m.setCompteOrigen(null);
            m.setCompteDesti(null);
            m.setDatetime(LocalDateTime.now());

            afegirMoviment(m);

            return true;
        }
        else {
            return false;
        }
    }

    // 💸 Mètode per fer una retirada
    public boolean retirar(double importRetirat) {
        if (importRetirat > 0 && this.saldo >= importRetirat) {
            this.saldo -= importRetirat;

            // ✳️ Crear moviment associat
            Moviment m = new Moviment();
            m.setTipus("RETIRADA");
            m.setImportTransaccio(importRetirat);
            m.setCompteOrigen(null);
            m.setCompteDesti(null);
            m.setDatetime(LocalDateTime.now());
            afegirMoviment(m);
            return true;
        }
        else {
            return false; // si no hi ha saldo suficient
        }
    }

    // 🔁 (Opcional) Mètode per registrar una transferència sortint
    public boolean enviarTransferencia(String compteDesti, double importTransf) {
        if (importTransf > 0 && this.saldo >= importTransf) {
            this.saldo -= importTransf;

            Moviment m = new Moviment();
            m.setTipus("TRANSFERENCIA_SORTINT");
            m.setImportTransaccio(importTransf);
            m.setCompteOrigen(null);
            m.setCompteDesti(compteDesti);
            m.setDatetime(LocalDateTime.now());

            afegirMoviment(m);

            return true;
        }
        else {
            return false;
        }
    }

    // 🔁 (Opcional) Mètode per registrar una transferència rebuda
    public void rebreTransferencia(String compteOrigen,double importTransf) {
        if (importTransf > 0) {
            this.saldo += importTransf;

            Moviment m = new Moviment();
            m.setTipus("TRANSFERENCIA_ENTRANT");
            m.setImportTransaccio(importTransf);
            m.setCompteOrigen(compteOrigen);
            m.setCompteDesti(null);
            m.setDatetime(LocalDateTime.now());

            afegirMoviment(m);
        }
    }

    // 🧾 Consultar saldo
    public double consultarSaldo() {
        return this.saldo;
    }

    // 🧩 Afegir moviment a la llista
    public void afegirMoviment(Moviment moviment) {
        if (this.moviments == null) {
            this.moviments = new ArrayList<>();
        }
        this.moviments.add(moviment);
    }

    // 🧩 Getters i Setters
    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public List<Moviment> getMoviments() {
        return moviments;
    }

    public void setMoviments(List<Moviment> moviments) {
        this.moviments = moviments;
    }

    // 🧾 Representació del compte
    @Override
    public String toString() {
        return "Compte{" +
                "IBAN='" + IBAN + '\'' +
                ", saldo=" + saldo +
                ", tipus='" + tipus + '\'' +
                ", moviments=" + moviments.size() +
                '}';
    }
}
