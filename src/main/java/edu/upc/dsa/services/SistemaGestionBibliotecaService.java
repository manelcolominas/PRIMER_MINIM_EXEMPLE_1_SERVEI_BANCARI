package edu.upc.dsa.services;

import edu.upc.dsa.classes.Lector;
import edu.upc.dsa.classes.Llibre;
import edu.upc.dsa.classes.Munts;
import edu.upc.dsa.classes.Prestec;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.upc.dsa.SistemaGestioBibliotecaImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Api(value = "/biblioteca", description = "Endpoint de biblioteca Service")
@Path("/biblioteca")
public class SistemaGestionBibliotecaService {

    private SistemaGestioBibliotecaImpl sistema;

    public SistemaGestionBibliotecaService() {
        // Use the singleton instance
        this.sistema = SistemaGestioBibliotecaImpl.getInstance();

        // Initialize with test data if empty
        if (sistema.sizeClients() == 0) {

            //Munt

            Munts munts = new Munts();

            Llibre llibre1 = new Llibre("9788497592208", "El nom de la rosa", "Edicions 62", "1980", "1a", "Umberto Eco", "Novel·la històrica");
            Llibre llibre2 = new Llibre("9788466340533", "Cien años de soledad", "Debolsillo", "1967", "3a", "Gabriel García Márquez", "Realisme màgic");
            Llibre llibre3 = new Llibre("9788426415664", "1984", "Edhasa", "1949", "2a", "George Orwell", "Distopia");
            Llibre llibre4 = new Llibre("9788491041689", "La plaça del Diamant", "Club Editor", "1962", "4a", "Mercè Rodoreda", "Novel·la catalana");

            munts.emmagatzemarLlibre(llibre1);
            munts.emmagatzemarLlibre(llibre2);
            munts.emmagatzemarLlibre(llibre3);
            munts.emmagatzemarLlibre(llibre4);

            Llibre llibre5 = new Llibre("9788437604947", "Don Quijote de la Mancha", "Cátedra", "1605", "5a", "Miguel de Cervantes", "Clàssic");
            Llibre llibre6 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Filosofia infantil");
            Llibre llibre7 = new Llibre("9788498387544", "Harry Potter i la pedra filosofal", "Empúries", "1997", "10a", "J.K. Rowling", "Fantasia");
            Llibre llibre8 = new Llibre("9788478888566", "El senyor dels anells", "Minotauro", "1954", "8a", "J.R.R. Tolkien", "Fantasia èpica");

            munts.emmagatzemarLlibre(llibre5);
            munts.emmagatzemarLlibre(llibre6);
            munts.emmagatzemarLlibre(llibre7);
            munts.emmagatzemarLlibre(llibre8);

            Llibre llibre9 = new Llibre("9788499890944", "Sàpiens: Una breu història de la humanitat", "Debate", "2011", "2a", "Yuval Noah Harari", "Assaig històric");
            Llibre llibre10 = new Llibre("9788497930802", "Crim i càstig", "Edicions 62", "1866", "3a", "Fiódor Dostoievski", "Novel·la psicològica");
            Llibre llibre11 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Filosofia infantil");
            Llibre llibre12 = new Llibre("9788437604947", "Don Quijote de la Mancha", "Cátedra", "1605", "5a", "Miguel de Cervantes", "Clàssic");

            munts.emmagatzemarLlibre(llibre9);
            munts.emmagatzemarLlibre(llibre10);
            munts.emmagatzemarLlibre(llibre11);
            munts.emmagatzemarLlibre(llibre12);

            Llibre llibre13 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Filosofia infantil");
            Llibre llibre14 = new Llibre( "9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Filosofia infantil");
            Llibre llibre15 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Filosofia infantil");
            Llibre llibre16 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Filosofia infantil");

            munts.emmagatzemarLlibre(llibre13);
            munts.emmagatzemarLlibre(llibre14);
            munts.emmagatzemarLlibre(llibre15);
            munts.emmagatzemarLlibre(llibre16);

        }
    }

    @GET
    @ApiOperation(value = "Obtenir informació d’un client", notes = "Inclou els comptes i moviments de cada compte")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client trobat", response = Lector.class),
            @ApiResponse(code = 404, message = "Client no trobat")
    })
    @Path("/{clientID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientInformation(@PathParam("clientID") int clientID) {
        Lector lector = sistema.getClient(clientID);
        if (lector == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(lector).build();
    }

    @GET
    @ApiOperation(value = "Obtenir tots els clients", notes = "Mostra informació de tots els clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llista retornada correctament", response = Lector.class, responseContainer = "List")
    })
    @Path("/clients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientsInformation() {
        List<Lector> lectors = sistema.getAllClients();
        GenericEntity<List<Lector>> entity = new GenericEntity<List<Lector>>(lectors) {};
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

    /*

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

     */

    // AFEGIR UN NOU LECTOR
    @POST
    @Path("lector/{nom}/{cognom1}/{cognom2}/{dni}/{datanaixement}/{llocnaixement}/{adreca}")
    @ApiOperation(value = "Afegir un nou Lector", notes = "Afegir un nou Lector")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response afegirLector(@PathParam("nom") String nom, @PathParam("cognom1") String cognom1, @QueryParam("cognom2") String cognom2, @QueryParam("dni") String dni, @QueryParam("datanaixement") String datanaixement, @QueryParam("llocnaixement") String llocnaixement, @QueryParam("adreca") String adreca) {
        Lector lector = new Lector(nom,cognom1, cognom2, dni,datanaixement,llocnaixement,adreca);
        sistema.addLector(lector);
        return Response.status(Response.Status.CREATED).build();
    }

    // EMMGATZEMAR UN NOU LLIBRE
    @POST
    @Path("llibre/emmagatzemar/{ISBN}/{titol}/{editorial}/{anypublicacio}/{numeroedicio}/{autor}/{tematica}")
    @ApiOperation(value = "Afegir un nou Lector", notes = "Afegir un nou Lector")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response emmagatzemarLlibre(@PathParam("ISBN") String ISBN, @PathParam("titol") String titol,@PathParam("editorial") String editorial, @QueryParam("anypublicacio") String anypublicacio, @QueryParam("numeroedicio") String numeroedicio, @QueryParam("autor") String autor, @QueryParam("tematica") String tematica) {
        Llibre llibre = new Llibre(ISBN, titol, editorial, anypublicacio, numeroedicio, autor, tematica);
            sistema.addLlibre(llibre);
        return Response.status(Response.Status.CREATED).build();
    }

    // CATALOGAR UN LLIBRE
    @GET
    @ApiOperation(value = "Catalogar un libre", notes = "Mostra el libre catalogat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Moviment trobat", response = Moviment.class),
            @ApiResponse(code = 404, message = "Moviment no trobat")
    })
    @Path("llibre/catalogar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response catalogarLlibre() {
        Munts munts = sistema.getMunts();
        Llibre llibre = munts.catalogarLlibre();
        if (llibre == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(llibre).build();
    }

    @POST
    @Path("/llibre/prestar/{idLector}/{ISBN}")
    @ApiOperation(value = "Prestar un llibre", notes = "Crea un préstec per un lector si hi ha exemplars disponibles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response prestarLlibre(@PathParam("idLector") int idLector, @PathParam("ISBN") String ISBN) {
        Lector lector = sistema.getLector(idLector);
        Llibre llibre = sistema.getLlibreCatalogat(ISBN);
        int idLlibre = llibre.getId();
        LocalDate dataPrestec = LocalDate.now();
        LocalDate dataDevolucio = dataPrestec.plusMonths(1);
        String dataPrestecString = dataPrestec.toString();       // "2025-11-07"
        String dataDevolucioString = dataDevolucio.toString();   // "2025-12-07"

        Prestec prestec = new Prestec(idLector, idLector,dataPrestecString, dataDevolucioString);

        lector.afegirPrestec(prestec);

        // 7️⃣ Decrementar exemplars disponibles
        llibre.setExemplarsDisponibles(llibre.getExemplarsDisponibles() - 1);

        // 8️⃣ Retornar èxit amb el préstec creat
        return Response.status(Response.Status.CREATED).entity(prestec).build();
    }

}