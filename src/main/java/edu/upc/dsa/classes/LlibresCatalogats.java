package edu.upc.dsa.classes;

import java.util.ArrayList;
import java.util.List;

public class LlibresCatalogats {
    public List<Llibre> llibres;
    private String tematica;

    // 🔧 Constructor
    public LlibresCatalogats() {
        this.llibres = new ArrayList<>();
    }

    public LlibresCatalogats(String tematica) {
        this.tematica = tematica;
        this.llibres = new ArrayList<>();
    }

    // ➕ Afegir un nou client
    public void afegirLlibre(Llibre llibre) {
        for (Llibre l : llibres) {
            if (l.getISBN().equals(llibre.getISBN())) {
                // Podríem portar un recompte d’exemplars si vols
                //📖 El llibre amb ISBN "ja està catalogat."
                l.incrementarExemplars();
                return;
            }
        }
        this.llibres.add(llibre);
    }

    // 🔍 Consultar llibres d’aquesta temàtica
    public List<Llibre> getLlibres() {
        return llibres;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

}
