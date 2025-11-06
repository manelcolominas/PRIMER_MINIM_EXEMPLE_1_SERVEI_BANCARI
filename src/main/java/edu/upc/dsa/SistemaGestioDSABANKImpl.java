package edu.upc.dsa;

import edu.upc.dsa.classes.*;

import java.util.*;

import edu.upc.dsa.exceptions.ClientNotFoundException;
import edu.upc.dsa.exceptions.CompteNotFoundException;
import edu.upc.dsa.exceptions.SaldoInsuficientException;
import org.apache.log4j.Logger;

public class SistemaGestioDSABANKImpl implements SistemaGestioDSABANK {
    private static SistemaGestioDSABANKImpl instance;

    private LlistaClients llistaClients;  // Llista principal de clients
    final static Logger logger = Logger.getLogger(SistemaGestioDSABANKImpl.class);

    private SistemaGestioDSABANKImpl() {
        this.llistaClients = new LlistaClients();
    }

    public static SistemaGestioDSABANKImpl getInstance() {
        if (instance == null) {
            instance = new SistemaGestioDSABANKImpl();
        }
        return instance;
    }

    // 👥 Afegir client
    public void addClient(Client c) {
        if (c != null) {
            this.llistaClients.afegirClient(c);
        }
    }

    public int sizeClients() {
        return this.llistaClients.getClients().size();
    }

    // 🔍 Obtenir client per ID
    public Client getClient(int id) {
        return this.llistaClients.getClientById(id);
    }

    public List<Client> getAllClients() {
        return new ArrayList<>(this.llistaClients.getClients());
    }

    // 🔍 Obtenir un compte d’un client concret
    public Compte getCompteClient(int clientId, String IBAN) {
        Client c = getClient(clientId);
        if (c == null || c.getComptes() == null) return null;
        for (Compte compte : c.getComptes()) {
            if (compte.getIBAN().equals(IBAN)) return compte;
        }
        return null;
    }

    @Override
    public void getCompteClientThrowsException(int clientId, String IBAN) throws ClientNotFoundException {
        Client c = getClient(clientId);
        if (c == null || c.getComptes() == null){
            throw new ClientNotFoundException("Client not found");
        }
        for (Compte compte : c.getComptes()) {
            if (compte.getIBAN().equals(IBAN));
        }
    }

    // 🔍 Obtenir moviment
    public Moviment getMoviment(String IBAN, int idMoviment) {
        for (Client c : this.llistaClients.getClients()) {
            if (c.getComptes() != null) {
                for (Compte compte : c.getComptes()) {
                    if (compte.getIBAN().equals(IBAN) && compte.getMoviments() != null) {
                        for (Moviment m : compte.getMoviments()) {
                            if (m.getId() == idMoviment) return m;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void addCompte(int clientId, String IBAN, String tipus) {
        for (Client c : this.llistaClients.getClients()) {
            if (c.getId() == clientId) {
                Compte compte = new Compte(IBAN, 0,tipus);
                c.afegirCompte(compte);
            }
        }
    }

    @Override
    public void addCompteThrowsException(int clientId, String IBAN, String tipus) throws ClientNotFoundException {
        boolean trobat = false;

        for (Client c : this.llistaClients.getClients()) {
            if (c.getId() == clientId) {
                Compte compte = new Compte(IBAN, 0, tipus);
                c.afegirCompte(compte);
                trobat = true;
                break; // ja hem trobat el client
            }
        }
        if (!trobat) {
            throw new ClientNotFoundException("Client not found");
        }
    }

    // 💰 Fer dipòsit
    public boolean ferDiposit(String IBAN, double import_) {
        Compte compte = getComptePerIBAN(IBAN);
        if (compte == null || import_ <= 0) return false;
        compte.dipositar(import_);
        return true;
    }

    @Override
    public void ferDipositThrowsException(String IBAN, double import_) throws CompteNotFoundException {
        Compte compte = getComptePerIBAN(IBAN);
        if (compte == null || import_ >= 0){
            throw new CompteNotFoundException("Account not found");
        }
        else {
            compte.dipositar(import_);
        }
    }

    public boolean ferRetirada(String IBAN, double import_) {
        Compte compte = getComptePerIBAN(IBAN);
        if (compte == null || import_ <= 0 || compte.getSaldo() < import_) {
            return false;
        }
        else {
            compte.retirar(import_);
            return  true;
        }
    }

    // 💸 Fer retirada
    @Override
    public void ferRetiradaThrowsException(String IBAN, double import_) throws CompteNotFoundException {
        Compte compte = getComptePerIBAN(IBAN);
        if (compte == null || import_ <= 0 || compte.getSaldo() < import_) {
            throw new CompteNotFoundException("Account not found");
        }
        else {
            compte.retirar(import_);
        }
    }

    // 🔁 Fer transferència
    public boolean ferTransferencia(String IBANOrigen, String IBANDesti, double import_) {

        Compte compteRemitent = getComptePerIBAN(IBANOrigen);
        compteRemitent.enviarTransferencia(IBANOrigen,import_);

        Compte compteBeneficiari = getComptePerIBAN(IBANDesti);
        compteBeneficiari.rebreTransferencia(IBANDesti,import_);

        return true;
    }

    public void ferTransferenciaThrowsException(String IBANOrigen, String IBANDesti, double import_) throws SaldoInsuficientException {

        Compte compteRemitent = getComptePerIBAN(IBANOrigen);
        if (compteRemitent.getSaldo() < import_) {
            throw new SaldoInsuficientException("Insufficient balance");
        }
        else {
            compteRemitent.enviarTransferencia(IBANOrigen,import_);

            Compte compteBeneficiari = getComptePerIBAN(IBANDesti);
            compteBeneficiari.rebreTransferencia(IBANDesti,import_);
        }
    }

    // 🧭 Buscar compte per IBAN dins de tots els clients
    public Compte getComptePerIBAN(String IBAN) {
        for (Client c : this.llistaClients.getClients()) {
            if (c.getComptes() != null) {
                for (Compte compte : c.getComptes()) {
                    if (compte.getIBAN().equals(IBAN)) return compte;
                }
            }
        }
        return null;
    }

    // 🧹 Reiniciar sistema (útil per tests)
    public void clear() {
        this.llistaClients = new LlistaClients();
    }
}
