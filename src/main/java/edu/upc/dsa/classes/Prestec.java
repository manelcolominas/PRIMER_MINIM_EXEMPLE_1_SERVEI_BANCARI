package edu.upc.dsa.classes;

public class Prestec {

    private static int nextId = 1;
    // 🧱 Atributs
    private int idPrestec;
    private int idLector;
    private int idLlibre;
    private String dataPrestec;
    private String dataDevolucio;
    private boolean entramit;

    // 🔧 Constructor buit (necessari per frameworks o JSON)
    public Prestec() {
        this.idPrestec = nextId++;
    }

    // 🔧 Constructor complet
    public Prestec(int idLector, int idLlibre, String dataPrestec, String dataDevolucio) {
        this.idPrestec = nextId++;
        this.idLector = idLector;
        this.idLlibre = idLlibre;
        this.dataPrestec = dataPrestec;
        this.dataDevolucio = dataDevolucio;
        this.entramit = false;
    }

    public int getIdPrestec() {
        return idPrestec;
    }

    public void setIdPrestec(int idPrestec) {
        this.idPrestec = idPrestec;
    }

    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }

    public int getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public String getDataPrestec() {
        return dataPrestec;
    }

    public void setDataPrestec(String dataPrestec) {
        this.dataPrestec = dataPrestec;
    }

    public String getDataDevolucio() {
        return dataDevolucio;
    }

    public void setDataDevolucio(String dataDevolucio) {
        this.dataDevolucio = dataDevolucio;
    }

    public boolean isEntramit() {
        return entramit;
    }
    public void setEntramit(boolean entramit) {
        this.entramit = entramit;
    }

    // 🧾 Representació del client
    @Override
    public String toString() {
        return "Client{" +
                "idPrestec=" + idPrestec +
                ", idLector=" + idLector +
                ", idLlibre=" + idLlibre +
                ", dataPrestec='" + dataPrestec + '\'' +
                ", dataDevolucio='" + dataDevolucio + '\'' +
                '}';
    }
}

