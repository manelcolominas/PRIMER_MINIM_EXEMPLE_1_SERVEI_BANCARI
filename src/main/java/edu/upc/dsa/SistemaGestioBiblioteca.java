package edu.upc.dsa;

import edu.upc.dsa.classes.*;
import edu.upc.dsa.exceptions.CanNotStoreBook;
import edu.upc.dsa.exceptions.LectorNotFoundException;
import edu.upc.dsa.exceptions.NotEnoughCopies;

import java.util.List;

public interface SistemaGestioBiblioteca {

    Prestec ferPrestec(int idLector, Llibre llibre, String dataPrestec, String dataDevolucio);
    Llibre getLlibreCatalogatByISBN(String isbn);
    void addLector(Lector l);
    void addLlibre(Llibre l);
    Munts getMunts();
    void setMunts(Munts munts);
    int sizeLectors();
    Lector getLector(int id);
    Llibre catalogarLlibre();

    void clear();
    void actualitzarDadesLector(int i, String anna, String pérez, String garcía, String s, String s1, String barcelona, String s2);

    //TEST EXCEPTIONS
    void getLectorException(int id) throws LectorNotFoundException;
    void addLlibreException(Llibre llibre) throws CanNotStoreBook;
    void catalogarLlibreException() throws CanNotStoreBook;

    void ferPrestecException(int i, Llibre llibre, String date, String date1)  throws NotEnoughCopies;
    void actualitzarDadesLectorException(int i, String nom, String cognom, String cognom2, String dni, String date, String lloc, String adreça) throws LectorNotFoundException;
}