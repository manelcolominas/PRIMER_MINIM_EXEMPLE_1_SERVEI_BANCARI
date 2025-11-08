package edu.upc.dsa.classes;

import java.util.*;

public class Munts {
    private  List<Stack<Llibre>> munts = new ArrayList<>();

    public Munts(){
        this.munts = new ArrayList<>();
    }

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

    public List<Stack<Llibre>> getMunts() {
        return munts;
    }

    public int getTotalLlibresEnMunts() {
        return munts.stream().mapToInt(Stack::size).sum();
    }

    public int getMuntCount() {
        return munts.size();
    }

    public Stack<Llibre> get(int i) {
        return munts.get(i);
    }

    public void remove(int i) {
        munts.remove(i);
    }

    public boolean isEmpty() {
        return munts.isEmpty();
    }
}

