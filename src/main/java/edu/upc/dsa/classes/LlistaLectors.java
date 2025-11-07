package edu.upc.dsa.classes;

import java.util.ArrayList;
import java.util.List;

public class LlistaLectors {
    private List<Lector> lectors;

    // 🔧 Constructor
    public LlistaLectors() {
        this.lectors = new ArrayList<>();
    }

    // ➕ Afegir un nou client
    public void afegirLector(Lector lector) {
        this.lectors.add(lector);
    }

    // 🔍 Buscar un client per ID
    public Lector getLectorByID(int id) {
        for (Lector c : lectors) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; // si no el troba
    }

    // 📋 Llistar tots els clients
    public List<Lector> getLector() {
        return this.lectors;
    }

    // ❌ Eliminar un lector per ID
    public boolean eliminarLector(int id) {
        Lector lector = getLectorByID(id);
        if (lector != null) {
            this.lectors.remove(lector);
            return true;
        }
        return false;
    }
}
