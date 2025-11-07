package edu.upc.dsa;

import edu.upc.dsa.classes.*;

import java.util.*;

import edu.upc.dsa.exceptions.ClientNotFoundException;
import edu.upc.dsa.exceptions.CompteNotFoundException;
import edu.upc.dsa.exceptions.SaldoInsuficientException;
import org.apache.log4j.Logger;

public class SistemaGestioBibliotecaImpl implements SistemaGestioBiblioteca {
    private static SistemaGestioBibliotecaImpl instance;

    private LlistaLectors llistaLectors;  // Llista principal de lectors
    private Munts munts;
    public LlibresCatalogats llibresCatalogats;

    final static Logger logger = Logger.getLogger(SistemaGestioBibliotecaImpl.class);

    private SistemaGestioBibliotecaImpl() {
        this.llistaLectors = new LlistaLectors();
        this.munts = new Munts();
        this.llibresCatalogats = new LlibresCatalogats();
    }

    public static SistemaGestioBibliotecaImpl getInstance() {
        if (instance == null) {
            instance = new SistemaGestioBibliotecaImpl();
        }
        return instance;
    }

    public Llibre getLlibreCatalogat(String isbn) {
        for (Llibre l : this.llibresCatalogats.getLlibres()) {
            if (l.getISBN().equals(isbn)) {
                return l;
            }
        }
        return null; // no trobat
    }

    public Prestec ferPrestec(int idLector, int idLlibre, String dataPrestec, String dataDevolucio) {
        Prestec prestec = new Prestec(idLector, idLlibre, dataPrestec, dataDevolucio);
        return prestec;
    }

    // 👥 Afegir client
    public void addLector(Lector l) {
        if (l != null) {
            this.llistaLectors.afegirLector(l);
        }
    }

    // Emmagatzemar llibre
    public void addLlibre(Llibre l) {
        if (l != null) {
            this.munts.emmagatzemarLlibre(l);
        }
    }

    public Munts getMunts() {
        return munts;
    }

    public void setMunts(Munts munts) {
        this.munts = munts;
    }

    public int sizeClients() {
        return this.llistaLectors.getClients().size();
    }

    // 🔍 Obtenir lector per ID
    public Lector getLector(int id) {
        return this.llistaLectors.getLectorByID(id);
    }

    public List<Lector> getAllClients() {
        return new ArrayList<>(this.llistaClients.getClients());
    }

    // 🔍 Obtenir un compte d’un client concret
    public Compte getCompteClient(int clientId, String IBAN) {
        Lector c = getClient(clientId);
        if (c == null || c.getComptes() == null) return null;
        for (Compte compte : c.getComptes()) {
            if (compte.getIBAN().equals(IBAN)) return compte;
        }
        return null;
    }

    @Override
    public void getCompteClientThrowsException(int clientId, String IBAN) throws ClientNotFoundException {
        Lector c = getClient(clientId);
        if (c == null || c.getComptes() == null){
            throw new ClientNotFoundException("Client not found");
        }
        for (Compte compte : c.getComptes()) {
            if (compte.getIBAN().equals(IBAN));
        }
    }

    // 🔍 Obtenir moviment
    public Moviment getMoviment(String IBAN, int idMoviment) {
        for (Lector c : this.llistaClients.getClients()) {
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
        for (Lector c : this.llistaClients.getClients()) {
            if (c.getId() == clientId) {
                Compte compte = new Compte(IBAN, 0,tipus);
                c.afegirCompte(compte);
            }
        }
    }

    @Override
    public void addCompteThrowsException(int clientId, String IBAN, String tipus) throws ClientNotFoundException {
        boolean trobat = false;

        for (Lector c : this.llistaClients.getClients()) {
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
        for (Lector c : this.llistaClients.getClients()) {
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
