package edu.upc.dsa;

import edu.upc.dsa.classes.*;
import edu.upc.dsa.exceptions.SaldoInsuficientException;

import java.util.List;

public interface SistemaGestioDSABANK {
    // 🔍 Consultes
    Client getClient(int id);
    List<Client> getAllClients();
    Compte getCompteClient(int clientId, String IBAN);
    Moviment getMoviment(String IBAN, int idMoviment);
    void addCompte(int clientId, String IBAN, String tipus);
    void addClient(Client c);

    // 💰 Operacions bancàries
    boolean ferDiposit(String IBAN, double import_);

    // 💸 Fer retirada

    boolean ferRetirada(String IBAN, double import_);
    boolean ferTransferencia(String IBANOrigen, String IBANDesti, double import_);
    Compte getComptePerIBAN(String IBAN);

    // 👥 Gestió de clients
    int sizeClients();
    void clear();

    // mètodes que tiren excepcions
    void addCompteThrowsException(int clientId, String IBAN, String tipus);
    void ferDipositThrowsException(String IBAN, double import_);
    void ferRetiradaThrowsException(String IBAN, double import_);
    void getCompteClientThrowsException(int clientId, String IBAN);
    void ferTransferenciaThrowsException(String IBANOrigen, String IBANDesti, double import_);
}