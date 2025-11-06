package edu.upc.dsa;

import edu.upc.dsa.classes.*;
import edu.upc.dsa.exceptions.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SistemaGestioDSABANKTest {
    SistemaGestioDSABANK sistemaGestioDSABANK;

    @Before
    public void setUp() {
        this.sistemaGestioDSABANK = SistemaGestioDSABANKImpl.getInstance();

        Client client1 = new Client(1, "Manel", "Colominas", "manelcolominas@gmail.com", "12345678A", "Castelldefels");
        this.sistemaGestioDSABANK.addClient(client1);

        this.sistemaGestioDSABANK.addCompte(1, "ES111", "corrent");
        this.sistemaGestioDSABANK.addCompte(1, "ES222", "estalvi");

        this.sistemaGestioDSABANK.ferDiposit("ES111", 500.0);
        this.sistemaGestioDSABANK.ferDiposit("ES222", 1000.0);
    }

    @After
    public void tearDown() {
        this.sistemaGestioDSABANK.clear();
    }

    // 🧩 TESTOS BÀSICS -----------------------------------------------------

    @Test
    public void testAddClient() {
        Client client = new Client(2, "Carla", "Serra", "carla@dsa.com", "11223344C", "Girona");
        this.sistemaGestioDSABANK.addClient(client);
    }

    @Test
    public void testAddCompte() {
        this.sistemaGestioDSABANK.addCompte(2, "ES333", "estalvi");
    }

    @Test
    public void testAddCompte_ClientNoExisteix() {
        Assert.assertThrows(ClientNotFoundException.class, () ->
                this.sistemaGestioDSABANK.addCompteThrowsException(99, "ES999", "corrent"));
    }

    // 💰 DIPÒSIT ----------------------------------------------------------

    @Test
    public void testDipositar() {
        this.sistemaGestioDSABANK.ferDiposit("ES111", 200.0);
        Compte compte = this.sistemaGestioDSABANK.getCompteClient(1, "ES111");
        Assert.assertEquals(700.0, compte.getSaldo(), 0.001);
    }

    @Test
    public void testDipositar_CompteNoExisteix() {
        Assert.assertThrows(CompteNotFoundException.class, () ->
                this.sistemaGestioDSABANK.ferDipositThrowsException("ES999", 100.0));
    }

    // 💸 RETIRADA ---------------------------------------------------------

    @Test
    public void testRetirar() {
        this.sistemaGestioDSABANK.ferRetirada("ES222", 300.0);
        Compte compte = this.sistemaGestioDSABANK.getCompteClient(1, "ES222");
        Assert.assertEquals(700.0, compte.getSaldo(), 0.001);
    }

    @Test
    public void testRetirada_CompteNoExisteix() throws Exception {
        Assert.assertThrows(CompteNotFoundException.class, () ->
                this.sistemaGestioDSABANK.ferRetiradaThrowsException("ES999", 50.0));
    }

    @Test
    public void testRetirada_SaldoInsuficient() throws Exception {
        Assert.assertThrows(CompteNotFoundException.class, () ->
                this.sistemaGestioDSABANK.ferRetiradaThrowsException("ES111", 9999.0));
    }

    // 🔁 TRANSFERÈNCIA ----------------------------------------------------

    @Test
    public void testTransferencia() {
        this.sistemaGestioDSABANK.ferTransferencia("ES111", "ES222", 200.0);
        Compte compteOrigen = this.sistemaGestioDSABANK.getCompteClient(1, "ES111");
        Compte compteDesti = this.sistemaGestioDSABANK.getCompteClient(1, "ES222");
        Assert.assertEquals(300.0, compteOrigen.getSaldo(), 0.001);
        Assert.assertEquals(1200.0, compteDesti.getSaldo(), 0.001);
    }

    @Test
    public void testTransferencia_SaldoInsuficient() {
        Assert.assertThrows(SaldoInsuficientException.class, () ->
                this.sistemaGestioDSABANK.ferTransferenciaThrowsException("ES111", "ES222", 9999.0));
    }

    // 🧾 MOVIMENTS --------------------------------------------------------

    @Test
    public void testAfegirMovimentsAutomaticament() {
        this.sistemaGestioDSABANK.ferDiposit("ES111", 100.0);
        this.sistemaGestioDSABANK.ferRetirada("ES111", 50.0);

        Compte compte = this.sistemaGestioDSABANK.getCompteClient(1, "ES111");
        List<Moviment> moviments = compte.getMoviments();
        Assert.assertEquals(3, moviments.size());
    }

    // 📋 CONSULTES --------------------------------------------------------

    @Test
    public void testGetCompte() {
        Compte compte = this.sistemaGestioDSABANK.getCompteClient(1, "ES111");
        Assert.assertNotNull(compte);
    }

    @Test
    public void testGetAllClients() {
        List<Client> clients = this.sistemaGestioDSABANK.getAllClients();
        Assert.assertFalse(clients.isEmpty());
    }

    // 🧹 RESET ------------------------------------------------------------

    @Test
    public void testClear() {
        this.sistemaGestioDSABANK.clear();
        Assert.assertTrue(this.sistemaGestioDSABANK.getAllClients().isEmpty());
    }
}