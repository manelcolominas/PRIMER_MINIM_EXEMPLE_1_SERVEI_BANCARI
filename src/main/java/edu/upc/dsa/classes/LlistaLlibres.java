package edu.upc.dsa.classes;

import java.util.ArrayList;
import java.util.List;

public class LlistaLlibres {
    private String tematica;
    private List<Llibre> llibres;

    public LlistaLlibres() {
        this.llibres = new ArrayList<>();
    }

    public LlistaLlibres(String tematica) {
        this.tematica = tematica;
        this.llibres = new ArrayList<>();
    }

    public void addLlibre(Llibre llibre) {
        if (llibre != null && llibre.getTematica().equals(this.tematica)) {
            this.llibres.add(llibre);
        }
    }

    public List<Llibre> getBooks() {
        return llibres;
    }

    public String getTematica() {
        return tematica;
    }

    public void setLlibres(List<Llibre> llibres) {
        this.llibres = llibres;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public int getLLibresSize() {
        return llibres.size();
    }

    public List<Llibre> getLlibres() {
        return llibres;
    }

    @Override
    public String toString() {
        return "BookList{" +
                "tematica='" + tematica + '\'' +
                ", llibresSize=" + llibres.size() +
                '}';
    }
}
