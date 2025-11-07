package edu.upc.dsa.classes;

import java.util.*;

public class Munts {
    private  List<Stack<Llibre>> munts = new ArrayList<>();

    public void emmagatzemarLlibre(Llibre llibre) {
        if (munts.isEmpty() || munts.get(munts.size() - 1).size() == 4) { // Hauria de ser size() == 10 ho faig size() == 4 per a que sigui mes facil de provar
            // Crear un nou munt.
            Stack<Llibre> nouMunt = new Stack<>();
            nouMunt.push(llibre);
            munts.add(nouMunt);
        }
        else {
            // Afegir al darrer munt
            munts.get(munts.size() - 1).push(llibre);
        }
    }

    public Llibre catalogarLlibre() {
        Stack<Llibre> primerMunt = munts.get(0);
        Llibre llibre = primerMunt.pop();

        if (primerMunt.isEmpty()) {
            munts.remove(0); // El següent munt passa a ser el primer
        }
        return llibre;
    }

    /* public Llibre catalogarLlibre() throws Exception {
        if (munts.isEmpty()) throw new Exception("No hi ha llibres pendents de catalogar");

        Stack<Llibre> primerMunt = munts.get(0);
        Llibre llibre = primerMunt.pop();

        if (primerMunt.isEmpty()) {
            munts.remove(0); // El següent munt passa a ser el primer
        }
        return llibre;
    }
     */
}

