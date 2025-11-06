package edu.upc.dsa.services;

import edu.upc.dsa.classes.Client;
import edu.upc.dsa.classes.Compte;
import edu.upc.dsa.classes.Moviment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.upc.dsa.SistemaGestioDSABANKImpl;

import java.util.List;

@Api(value = "/DSABANK", description = "Endpoint de DSABANK Service")
@Path("/DSABANK")
public class SistemaGestionDSABANKService {

    private SistemaGestioDSABANKImpl sistema;

    public SistemaGestionDSABANKService() {
        // Use the singleton instance
        this.sistema = SistemaGestioDSABANKImpl.getInstance();

        // Initialize with test data if empty
        if (sistema.sizeClients() == 0) {

            // Client 1
            Client client1 = new Client(1, "Manel", "Colominas", "manelcolominas@gmail.com", "12345678A", "Castelldefels");
            sistema.addClient(client1);
            sistema.addCompte(1, "ES111", "corrent");
            sistema.addCompte(1, "ES222", "estalvi");
            sistema.ferDiposit("ES111", 500.0);
            sistema.ferDiposit("ES111", 1500.0);
            sistema.ferDiposit("ES111", 1000);
            sistema.ferDiposit("ES222", 1000.0);

            // Client 2
            Client client2 = new Client(2, "Maria", "Garcia", "maria.garcia@gmail.com", "87654321B", "Barcelona");
            sistema.addClient(client2);
            sistema.addCompte(2, "ES333", "corrent");
            sistema.addCompte(2, "ES444", "estalvi");
            sistema.ferDiposit("ES333", 750.0);
            sistema.ferDiposit("ES444", 1250.0);

            // Client 3
            Client client3 = new Client(3, "Carlos", "Martinez", "carlos.martinez@gmail.com", "56781234C", "Sant Cugat");
            sistema.addClient(client3);
            sistema.addCompte(3, "ES555", "corrent");
            sistema.addCompte(3, "ES666", "estalvi");
            sistema.ferDiposit("ES555", 300.0);
            sistema.ferDiposit("ES666", 700.0);

            // Client 4
            Client client4 = new Client(4, "Ana", "Lopez", "ana.lopez@gmail.com", "43218765D", "Badalona");
            sistema.addClient(client4);
            sistema.addCompte(4, "ES777", "corrent");
            sistema.addCompte(4, "ES888", "estalvi");
            sistema.ferDiposit("ES777", 900.0);
            sistema.ferDiposit("ES888", 1500.0);


        }
    }

    @GET
    @ApiOperation(value = "Obtenir informació d’un client", notes = "Inclou els comptes i moviments de cada compte")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client trobat", response = Client.class),
            @ApiResponse(code = 404, message = "Client no trobat")
    })
    @Path("/{clientID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientInformation(@PathParam("clientID") int clientID) {
        Client client = sistema.getClient(clientID);
        if (client == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(client).build();
    }

    @GET
    @ApiOperation(value = "Obtenir tots els clients", notes = "Mostra informació de tots els clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llista retornada correctament", response = Client.class, responseContainer = "List")
    })
    @Path("/clients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientsInformation() {
        List<Client> clients = sistema.getAllClients();
        GenericEntity<List<Client>> entity = new GenericEntity<List<Client>>(clients) {};
        return Response.ok(entity).build();
    }


    @GET
    @ApiOperation(value = "Obtenir informació d’un compte", notes = "Mostra saldo, tipus i moviments")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Compte trobat", response = Compte.class),
            @ApiResponse(code = 404, message = "Compte no trobat")
    })
    @Path("/{clientID}/{IBAN}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientAccountInformation(@PathParam("clientID") int clientID, @PathParam("IBAN") String IBAN) {
        Compte compte = sistema.getCompteClient(clientID, IBAN);
        if (compte == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(compte).build();
    }


    @GET
    @ApiOperation(value = "Obtenir informació d’un moviment", notes = "Mostra detalls d’un moviment concret d’un compte")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Moviment trobat", response = Moviment.class),
            @ApiResponse(code = 404, message = "Moviment no trobat")
    })
    @Path("/{clientID}/{IBAN}/{movementID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientAccountMovementInformation(@PathParam("clientID") int clientID, @PathParam("IBAN") String IBAN,@PathParam("movimentID") int movimentID) {
        Moviment moviment = sistema.getMoviment(IBAN, movimentID);
        if (moviment == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(moviment).build();
    }

    @POST
    @ApiOperation(value = "Fer un dipòsit", notes = "Afegeix diners al compte indicat i registra un moviment")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Dipòsit realitzat correctament"),
            @ApiResponse(code = 400, message = "Error en el dipòsit")
    })
    @Path("/{IBAN}/deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response depositar(@PathParam("IBAN") String IBAN, @QueryParam("import") double import_) {
        boolean ok = sistema.ferDiposit(IBAN, import_);
        if (!ok)
            return Response.status(Response.Status.BAD_REQUEST).entity("Error en el dipòsit").build();
        return Response.status(Response.Status.CREATED).build();
    }


    @POST
    @Path("/{IBAN}/refund")
    @ApiOperation(value = "Fer una retirada", notes = "Resta diners del compte indicat i registra moviment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retirar(@PathParam("IBAN") String IBAN, @QueryParam("import") double import_) {
        boolean ok = sistema.ferRetirada(IBAN, import_);
        if (!ok)
            return Response.status(Response.Status.BAD_REQUEST).entity("Saldo insuficient").build();
        return Response.status(Response.Status.CREATED).build();
    }


    @POST
    @Path("/{IBANOrigen}/{IBANDesti}/transfer")
    @ApiOperation(value = "Fer una transferència", notes = "Transfereix diners entre dos comptes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response transferir(@PathParam("IBANOrigen") String IBANOrigen, @PathParam("IBANDesti") String IBANDesti, @QueryParam("import") double import_) {
        boolean ok = sistema.ferTransferencia(IBANOrigen, IBANDesti, import_);
        if (!ok)
            return Response.status(Response.Status.BAD_REQUEST).entity("Error en la transferència").build();
        return Response.status(Response.Status.CREATED).build();
    }

}