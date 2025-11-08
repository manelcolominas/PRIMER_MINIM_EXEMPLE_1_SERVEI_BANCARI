package edu.upc.dsa;

import edu.upc.dsa.exceptions.*;


import edu.upc.dsa.classes.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SistemaGestioBibliotecaTest {

    SistemaGestioBiblioteca sistemaGestioBiblioteca;

    @Before
    public void setUp() {
        this.sistemaGestioBiblioteca = SistemaGestioBibliotecaImpl.getInstance();
        this.sistemaGestioBiblioteca.clear();
    }

    @After
    public void tearDown() {
        this.sistemaGestioBiblioteca.clear();
    }

    // -----------------------------
    // ADD LECTOR
    // -----------------------------
    @Test
    public void testAddLector() {
        Lector lector = new Lector(1);
        this.sistemaGestioBiblioteca.addLector(lector);

        Assert.assertEquals(1, this.sistemaGestioBiblioteca.sizeLectors());
        Assert.assertEquals(lector,this.sistemaGestioBiblioteca.getLector(1));
    }

    @Test
    public void testAddLectorException() throws LectorNotFoundException{
        this.sistemaGestioBiblioteca.addLector(null);
        this.sistemaGestioBiblioteca.getLectorException(1);
    }


    // -----------------------------
    // ACTUALITZAR LECTOR
    // -----------------------------
    @Test
    public void testActualitzarDadesLector() {
        Lector lector = new Lector(1);
        this.sistemaGestioBiblioteca.addLector(lector);

        this.sistemaGestioBiblioteca.actualitzarDadesLector(1,"Anna", "Pérez", "García","12345678A", "01/01/2000", "Barcelona", "C/ Major 5");

        Lector actualitzat = this.sistemaGestioBiblioteca.getLector(1);
        Assert.assertEquals("Anna", actualitzat.getNom());
    }

    @Test
    public void testActualitzarDadesLectorException() throws LectorNotFoundException {
        this.sistemaGestioBiblioteca.actualitzarDadesLectorException(999, "Nom", "Cognom", "Cognom2","DNI", "2000-01-01", "Lloc", "Adreça");
    }


    // -----------------------------
    // ADD LLIBRE
    // -----------------------------
    @Test
    public void testAddLlibre() {
        Llibre llibre = new Llibre("9788437604947", "Don Quijote", "Cátedra","1605", "5a", "Miguel de Cervantes", "Clàssic");
        this.sistemaGestioBiblioteca.addLlibre(llibre);
        Assert.assertFalse(this.sistemaGestioBiblioteca.getMunts().isEmpty());
    }

    @Test
    public void testAddLlibreException() throws CanNotStoreBook{
        this.sistemaGestioBiblioteca.addLlibreException(null);
    }


    // -----------------------------
    // CATALOGAR LLIBRE
    // -----------------------------
    @Test
    public void testCatalogarLlibre() {
        Llibre llibre = new Llibre("9788499890944", "Sàpiens: Una breu història de la humanitat", "Debate", "2011", "2a", "Yuval Noah Harari", "Clàssic");
        this.sistemaGestioBiblioteca.addLlibre(llibre);
        Llibre catalogat = this.sistemaGestioBiblioteca.catalogarLlibre();
        Assert.assertNotNull(catalogat);
        Assert.assertEquals("Sàpiens: Una breu història de la humanitat", catalogat.getTitol());
    }

    @Test
    public void testCatalogarLlibreException() throws CanNotStoreBook{
        // No hi ha llibres als munts, hauria de fallar
        this.sistemaGestioBiblioteca.catalogarLlibreException();
    }

    // -----------------------------
    // FER PRESTEC
    // -----------------------------
    @Test
    public void testFerPrestec() {
        Lector lector = new Lector(1);
        this.sistemaGestioBiblioteca.addLector(lector);

        Llibre llibre = new Llibre("123", "El Principito", "Reynal & Hitchcock","1943", "1a", "Antoine de Saint-Exupéry", "Infantil");
        this.sistemaGestioBiblioteca.addLlibre(llibre);
        this.sistemaGestioBiblioteca.catalogarLlibre();

        Prestec prestec = this.sistemaGestioBiblioteca.ferPrestec(1, llibre, "2025-01-01", "2025-01-15");

        Assert.assertNotNull(prestec);
        Assert.assertEquals(0, llibre.getNumexemplars());
    }


    @Test
    public void testFerPrestecException() throws NotEnoughCopies{
        Lector lector = new Lector(1);
        this.sistemaGestioBiblioteca.addLector(lector);

        Llibre llibre = new Llibre("123", "El Principito", "Reynal & Hitchcock","1943", "1a", "Antoine de Saint-Exupéry", "Infantil");
        this.sistemaGestioBiblioteca.addLlibre(llibre);
        this.sistemaGestioBiblioteca.catalogarLlibre();

        Prestec prestec1 = this.sistemaGestioBiblioteca.ferPrestec(1, llibre, "2025-01-01", "2025-01-15");
        this.sistemaGestioBiblioteca.ferPrestecException(1, llibre, "2025-01-01", "2025-01-15");

        Assert.assertEquals(0, llibre.getNumexemplars());
    }
}
