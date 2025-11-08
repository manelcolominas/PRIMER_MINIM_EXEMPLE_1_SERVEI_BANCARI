package edu.upc.dsa;

import edu.upc.dsa.classes.*;
import edu.upc.dsa.exceptions.LectorNotFoundException;
import edu.upc.dsa.exceptions.CanNotStoreBook;
import edu.upc.dsa.exceptions.NotEnoughCopies;
import org.apache.log4j.Logger;

import java.util.Stack;

// Per a que en faci les traces li he passat al chat i m'ho ha fet autotamaticament ell

public class SistemaGestioBibliotecaImpl implements SistemaGestioBiblioteca {
    private static SistemaGestioBibliotecaImpl instance;

    private LlistaLectors llistaLectors;
    private Munts munts;
    private CatalegTematic catalegTematic;

    final static Logger logger = Logger.getLogger(SistemaGestioBibliotecaImpl.class);

    private SistemaGestioBibliotecaImpl() {
        this.llistaLectors = new LlistaLectors();
        this.munts = new Munts();
        this.catalegTematic = new CatalegTematic();
        logger.info("Constructor SistemaGestioBibliotecaImpl inicialitzat");
    }

    public static SistemaGestioBibliotecaImpl getInstance() {
        logger.info("Inici getInstance()");
        if (instance == null) {
            instance = new SistemaGestioBibliotecaImpl();
            logger.warn("Instancia creada");
        }
        logger.info("Fi getInstance() -> " + instance);
        return instance;
    }

    public Llibre catalogarLlibre() {
        logger.info("Inici catalogarLlibre()");
        if (munts == null || munts.isEmpty()) {
            logger.warn("Munt/Pila està buit");
            return null;
        }
        Stack<Llibre> primerMunt = munts.get(0);
        Llibre llibre = primerMunt.pop();

        if (primerMunt.isEmpty()) {
            munts.remove(0);
            logger.warn("Primera Munt/Pila està buida");
        }

        if (catalegTematic != null) {
            catalegTematic.addLlibre(llibre);
            logger.info("Llibre catalogat: " + llibre);
        }

        logger.info("Fi catalogarLlibre() -> Retorna: " + llibre);
        return llibre;
    }

    public void catalogarLlibreException() throws CanNotStoreBook{
        if (munts == null || munts.isEmpty()) {
            throw new CanNotStoreBook("No s'ha pogut catalogar el llibre");
        }
    }

    public Llibre getLlibreCatalogatByISBN(String ISBN) {
        logger.info("Inici getLlibreCatalogatByISBN(ISBN=" + ISBN + ")");
        for (LlistaLlibres llista : catalegTematic.getCatalegMap().values()) {
            for (Llibre llibre : llista.getLlibres()) {
                if (llibre.getISBN().equals(ISBN)) {
                    logger.info("Fi getLlibreCatalogatByISBN() -> Trobat: " + llibre);
                    return llibre;
                }
            }
        }
        logger.info("Fi getLlibreCatalogatByISBN() -> No trobat");
        return null;
    }

    public Prestec ferPrestec(int idLector, Llibre llibre, String dataPrestec, String dataDevolucio) {
        logger.info("Inici ferPrestec(idLector=" + idLector + ", llibre=" + llibre + ", dataPrestec=" + dataPrestec + ", dataDevolucio=" + dataDevolucio + ")");
        int idLlibre = llibre.getId();
        Prestec prestec = new Prestec(idLector, idLlibre, dataPrestec, dataDevolucio);
        Lector lector = llistaLectors.getLectorByID(idLector);
        llibre.decrementarExemplars();
        lector.afegirPrestec(prestec);
        logger.info("Fi ferPrestec() -> Retorna: " + prestec);
        return prestec;
    }

    public void ferPrestecException(int idLector, Llibre llibre, String dataPrestec, String dataDevolucio) throws NotEnoughCopies {
        if (llibre.getNumexemplars() < 1){
            throw new NotEnoughCopies("No hi ha suficients Exemplars");
        }
    }

    public void addLector(Lector l) {
        logger.info("Inici addLector(" + l + ")");
        if (l != null) {
            this.llistaLectors.afegirLector(l);
            logger.info("Fi addLector() -> Lector afegit: " + l);
        } else {
            logger.warn("Intent d’afegir lector nul");
        }
    }

    public void actualitzarDadesLector(int idLector, String nom, String cognom1, String cognom2, String dni, String datanaixement, String llocnaixement, String adreca) {
        logger.info("Inici actualitzarDadesLector(idLector=" + idLector + ", nom=" + nom + ", cognoms=" + cognom1 + " " + cognom2 + ")");
        Lector lector = llistaLectors.getLectorByID(idLector);
        lector.setAllAtributs(nom, cognom1, cognom2, dni, datanaixement, llocnaixement, adreca);
        logger.info("Fi actualitzarDadesLector() -> Lector actualitzat: " + lector);
    }

    public void actualitzarDadesLectorException(int idLector, String nom, String cognom1, String cognom2, String dni, String datanaixement, String llocnaixement, String adreca) throws LectorNotFoundException{
        Lector lector = llistaLectors.getLectorByID(idLector);
        if (lector == null) {
            throw new LectorNotFoundException("Lector amb id " + idLector + " no trobat");
        }
    }

    public LlistaLectors getLlistaLectors() {
        logger.info("Inici getLlistaLectors()");
        logger.info("Fi getLlistaLectors() -> Retorna: " + llistaLectors);
        return this.llistaLectors;
    }

    public void addLlibre(Llibre l) {
        logger.info("Inici addLlibre(" + l + ")");
        if (l != null) {
            this.munts.emmagatzemarLlibre(l);
            logger.info("Fi addLlibre() -> Llibre afegit: " + l);
        } else {
            logger.warn("Intent d’afegir llibre nul");
        }
    }

    public void addLlibreException(Llibre l) throws CanNotStoreBook{
        if (l != null) {
            this.munts.emmagatzemarLlibre(l);
        }
        else {
            throw new CanNotStoreBook("No s'ha pogut emmagatzemar el llibre");
        }
    }

    public Munts getMunts() {
        logger.info("Inici getMunts()");
        logger.info("Fi getMunts() -> Retorna: " + munts);
        return munts;
    }

    public void setMunts(Munts munts) {
        logger.info("Inici setMunts(" + munts + ")");
        this.munts = munts;
        logger.info("Fi setMunts()");
    }

    public int sizeLectors() {
        logger.info("Inici sizeLectors()");
        int size = this.llistaLectors.size();
        logger.info("Fi sizeLectors() -> Retorna: " + size);
        return size;
    }

    public Lector getLector(int id) {
        logger.info("Inici getLector(id=" + id + ")");
        Lector lector = llistaLectors.getLectorByID(id);
        logger.info("Fi getLector() -> Retorna: " + lector);
        return lector;
    }

    @Override
    public void getLectorException(int id) throws LectorNotFoundException {
        Lector lector = llistaLectors.getLectorByID(id);
        if (lector == null) {
            throw new LectorNotFoundException("Lector amb id " + id + " no trobat");
        }
    }

    public CatalegTematic getCatalegTematic() {
        logger.info("Inici getCatalegTematic()");
        logger.info("Fi getCatalegTematic() -> Retorna: " + catalegTematic);
        return catalegTematic;
    }

    public void clear() {
        logger.info("Inici clear()");
        this.llistaLectors = new LlistaLectors();
        logger.info("Fi clear() -> Llista de lectors buidada");
    }
}
