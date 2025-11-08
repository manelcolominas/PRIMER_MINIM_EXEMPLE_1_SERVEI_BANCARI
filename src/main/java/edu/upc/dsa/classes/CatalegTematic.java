package edu.upc.dsa.classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class CatalegTematic {
    private Map<String, LlistaLlibres> catalegMap;
    public CatalegTematic() {
        this.catalegMap = new HashMap<>();
    }

    public void addLlibre(Llibre llibreNou) {
        String tematica = llibreNou.getTematica();

        // Intentem obtenir la llista de llibres per temàtica
        LlistaLlibres llistaLlibres = getLlibresByTema(tematica);

        // Si no existeix la llista, la creem i l’afegim al catàleg
        if (llistaLlibres == null) {
            llistaLlibres = new LlistaLlibres(tematica);
            catalegMap.put(tematica, llistaLlibres);
        }

        // Buscar si ja existeix un llibre amb el mateix ISBN
        for (Llibre llibre : llistaLlibres.getLlibres()) {
            if (llibre.getISBN().equals(llibreNou.getISBN())) {
                // ISBN repetit → incrementar exemplars i sortir
                llibre.incrementarExemplars();
                return;
            }
        }

        // Si no hi és, afegim el llibre nou i inicialitzem exemplars a 1
        llibreNou.incrementarExemplars();
        llistaLlibres.addLlibre(llibreNou);
    }


    public LlistaLlibres getLlibresByTema(String tematica) {
        return catalegMap.get(tematica);
    }

    public List<LlistaLlibres> getTotsElsLlibres() {
        return new ArrayList<>(this.catalegMap.values());
    }

    public List<Llibre> getTotsElsLlibresPlans() {
        List<Llibre> totsElsLlibres = new ArrayList<>();
        for (LlistaLlibres llista : catalegMap.values()) {
            totsElsLlibres.addAll(llista.getLlibres());
        }
        return totsElsLlibres;
    }


    public Map<String, LlistaLlibres> getCatalegMap() {
        return catalegMap;
    }
}
