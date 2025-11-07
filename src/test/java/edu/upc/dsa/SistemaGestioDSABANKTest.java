package edu.upc.dsa;

import edu.upc.dsa.classes.*;
import edu.upc.dsa.exceptions.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SistemaGestioDSABANKTest {
    SistemaGestioBiblioteca sistemaGestioBiblioteca;

    @Before
    public void setUp() {
        this.sistemaGestioBiblioteca = SistemaGestioDSABANKImpl.getInstance();

        Lector lector1 = new Lector("Manel", "Colominas", "manelcolominas@gmail.com", "12345678A", "Castelldefels");
        this.sistemaGestioBiblioteca.addClient(lector1);

        this.sistemaGestioBiblioteca.addCompte(1, "ES111", "corrent");
        this.sistemaGestioBiblioteca.addCompte(1, "ES222", "estalvi");

        this.sistemaGestioBiblioteca.ferDiposit("ES111", 500.0);
        this.sistemaGestioBiblioteca.ferDiposit("ES222", 1000.0);
    }

    @After
    public void tearDown() {
        this.sistemaGestioBiblioteca.clear();
    }

    // 🧩 TESTOS BÀSICS -----------------------------------------------------

    @Test
    public void testAddClient() {
        Lector lector = new Lector(2, "Carla", "Serra", "carla@dsa.com", "11223344C", "Girona");
        this.sistemaGestioBiblioteca.addClient(lector);
    }

    @Test
    public void testAddCompte() {
        this.sistemaGestioBiblioteca.addCompte(2, "ES333", "estalvi");
    }

    @Test
    public void testAddCompte_ClientNoExisteix() {
        Assert.assertThrows(ClientNotFoundException.class, () ->
                this.sistemaGestioBiblioteca.addCompteThrowsException(99, "ES999", "corrent"));
    }

    // 💰 DIPÒSIT ----------------------------------------------------------

    @Test
    public void testDipositar() {
        this.sistemaGestioBiblioteca.ferDiposit("ES111", 200.0);
        Compte compte = this.sistemaGestioBiblioteca.getCompteClient(1, "ES111");
        Assert.assertEquals(700.0, compte.getSaldo(), 0.001);
    }

    @Test
    public void testDipositar_CompteNoExisteix() {
        Assert.assertThrows(CompteNotFoundException.class, () ->
                this.sistemaGestioBiblioteca.ferDipositThrowsException("ES999", 100.0));
    }

    // 💸 RETIRADA ---------------------------------------------------------

    @Test
    public void testRetirar() {
        this.sistemaGestioBiblioteca.ferRetirada("ES222", 300.0);
        Compte compte = this.sistemaGestioBiblioteca.getCompteClient(1, "ES222");
        Assert.assertEquals(700.0, compte.getSaldo(), 0.001);
    }

    @Test
    public void testRetirada_CompteNoExisteix() throws Exception {
        Assert.assertThrows(CompteNotFoundException.class, () ->
                this.sistemaGestioBiblioteca.ferRetiradaThrowsException("ES999", 50.0));
    }

    @Test
    public void testRetirada_SaldoInsuficient() throws Exception {
        Assert.assertThrows(CompteNotFoundException.class, () ->
                this.sistemaGestioBiblioteca.ferRetiradaThrowsException("ES111", 9999.0));
    }

    // 🔁 TRANSFERÈNCIA ----------------------------------------------------

    @Test
    public void testTransferencia() {
        this.sistemaGestioBiblioteca.ferTransferencia("ES111", "ES222", 200.0);
        Compte compteOrigen = this.sistemaGestioBiblioteca.getCompteClient(1, "ES111");
        Compte compteDesti = this.sistemaGestioBiblioteca.getCompteClient(1, "ES222");
        Assert.assertEquals(300.0, compteOrigen.getSaldo(), 0.001);
        Assert.assertEquals(1200.0, compteDesti.getSaldo(), 0.001);
    }

    @Test
    public void testTransferencia_SaldoInsuficient() {
        Assert.assertThrows(SaldoInsuficientException.class, () ->
                this.sistemaGestioBiblioteca.ferTransferenciaThrowsException("ES111", "ES222", 9999.0));
    }

    // 🧾 MOVIMENTS --------------------------------------------------------

    @Test
    public void testAfegirMovimentsAutomaticament() {
        this.sistemaGestioBiblioteca.ferDiposit("ES111", 100.0);
        this.sistemaGestioBiblioteca.ferRetirada("ES111", 50.0);

        Compte compte = this.sistemaGestioBiblioteca.getCompteClient(1, "ES111");
        List<Moviment> moviments = compte.getMoviments();
        Assert.assertEquals(3, moviments.size());
    }

    // 📋 CONSULTES --------------------------------------------------------

    @Test
    public void testGetCompte() {
        Compte compte = this.sistemaGestioBiblioteca.getCompteClient(1, "ES111");
        Assert.assertNotNull(compte);
    }

    @Test
    public void testGetAllClients() {
        List<Lector> lectors = this.sistemaGestioBiblioteca.getAllClients();
        Assert.assertFalse(lectors.isEmpty());
    }

    // 🧹 RESET ------------------------------------------------------------

    @Test
    public void testClear() {
        this.sistemaGestioBiblioteca.clear();
        Assert.assertTrue(this.sistemaGestioBiblioteca.getAllClients().isEmpty());
    }
}