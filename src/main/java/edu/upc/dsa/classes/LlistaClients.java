package edu.upc.dsa.classes;

import java.util.ArrayList;
import java.util.List;

public class LlistaClients {
    private List<Client> clients;

    // 🔧 Constructor
    public LlistaClients() {
        this.clients = new ArrayList<>();
    }

    // ➕ Afegir un nou client
    public void afegirClient(Client client) {
        this.clients.add(client);
    }

    // 🔍 Buscar un client per ID
    public Client getClientById(int id) {
        for (Client c : clients) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; // si no el troba
    }

    // 🔍 Buscar un client per DNI
    public Client getClientByDni(String dni) {
        for (Client c : clients) {
            if (c.getDni().equalsIgnoreCase(dni)) {
                return c;
            }
        }
        return null;
    }

    // 📋 Llistar tots els clients
    public List<Client> getClients() {
        return this.clients;
    }

    // ❌ Eliminar un client per ID
    public boolean eliminarClient(int id) {
        Client client = getClientById(id);
        if (client != null) {
            this.clients.remove(client);
            return true;
        }
        return false;
    }

    // 🧾 Mostrar tots els clients (útil per depurar)
    public void mostrarClients() {
        for (Client c : clients) {
            System.out.println(c);
        }
    }
}
