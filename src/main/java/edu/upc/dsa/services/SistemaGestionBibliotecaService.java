package edu.upc.dsa.services;

import edu.upc.dsa.classes.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.constraints.Max;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.upc.dsa.SistemaGestioBibliotecaImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "/biblioteca", description = "Endpoint de biblioteca Service")
@Path("/biblioteca")
public class SistemaGestionBibliotecaService {

    private SistemaGestioBibliotecaImpl sistema;

    public SistemaGestionBibliotecaService() {
        // Use the singleton instance
        this.sistema = SistemaGestioBibliotecaImpl.getInstance();

            //Munt

            Munts munts = this.sistema.getMunts();
            LlistaLectors lectors = this.sistema.getLlistaLectors();

            if (lectors.size() == 0) {

                Lector lector1 = new Lector(1);
                lector1.setAllAtributs("Manel","Colominas", "Ruiz","hjdsdsbjads", "11/02/2003","Barcelona","Castelldefels");
                lectors.afegirLector(lector1);

                Lector lector2 = new Lector(2);
                lector2.setAllAtributs("Marc","Colominas", "Borrull","kckzcmccmc", "16/04/1968","Barcelona","Castelldefels");
                lectors.afegirLector(lector2);

                Lector lector3 = new Lector(3);
                lector3.setAllAtributs("Laura", "González", "Pérez", "48937215K", "23/09/1995", "Madrid", "Getafe");
                lectors.afegirLector(lector3);

                Lector lector4 = new Lector(4);
                //lector4.setAllAtributs("Joan", "Martí", "Serrano", "38152674M", "05/12/1980", "Girona", "Olot");
                lectors.afegirLector(lector4);

                Lector lector5 = new Lector(5);
                //lector5.setAllAtributs("Clara", "Ribas", "Solé", "52918463L", "29/07/2001", "Tarragona", "Reus");
                lectors.afegirLector(lector5);

                Llibre llibre1 = new Llibre("9788497592208", "El nom de la rosa", "Edicions 62", "1980", "1a", "Umberto Eco", "Clàssic");
                Llibre llibre2 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");
                Llibre llibre3 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");
                Llibre llibre4 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");

                munts.emmagatzemarLlibre(llibre1);
                munts.emmagatzemarLlibre(llibre2);
                munts.emmagatzemarLlibre(llibre3);
                munts.emmagatzemarLlibre(llibre4);

                Llibre llibre5 = new Llibre("9788437604947", "Don Quijote de la Mancha", "Cátedra", "1605", "5a", "Miguel de Cervantes", "Clàssic");
                Llibre llibre6 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");
                Llibre llibre7 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");
                Llibre llibre8 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");

                munts.emmagatzemarLlibre(llibre5);
                munts.emmagatzemarLlibre(llibre6);
                munts.emmagatzemarLlibre(llibre7);
                munts.emmagatzemarLlibre(llibre8);

                Llibre llibre9 = new Llibre("9788499890944", "Sàpiens: Una breu història de la humanitat", "Debate", "2011", "2a", "Yuval Noah Harari", "Clàssic");
                Llibre llibre10 = new Llibre("9788497930802", "Crim i càstig", "Edicions 62", "1866", "3a", "Fiódor Dostoievski", "Novel·la");
                Llibre llibre11 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");
                Llibre llibre12 = new Llibre("9788437604947", "Don Quijote de la Mancha", "Cátedra", "1605", "5a", "Miguel de Cervantes", "Clàssic");

                munts.emmagatzemarLlibre(llibre9);
                munts.emmagatzemarLlibre(llibre10);
                munts.emmagatzemarLlibre(llibre11);
                munts.emmagatzemarLlibre(llibre12);

                Llibre llibre13 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");
                Llibre llibre14 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");
                Llibre llibre15 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");
                Llibre llibre16 = new Llibre("9788413141739", "El petit príncep", "Salamandra", "1943", "7a", "Antoine de Saint-Exupéry", "Infantil");

                munts.emmagatzemarLlibre(llibre13);
                munts.emmagatzemarLlibre(llibre14);
                munts.emmagatzemarLlibre(llibre15);
                munts.emmagatzemarLlibre(llibre16);

                Llibre llibre17 = new Llibre("9788498387544", "Harry Potter i la pedra filosofal", "Empúries", "1997", "10a", "J.K. Rowling", "Fantasia");
                Llibre llibre18 = new Llibre("9788478888566", "El senyor dels anells", "Minotauro", "1954", "8a", "J.R.R. Tolkien", "Fantasia");
                Llibre llibre19 = new Llibre("9788466340533", "Cien años de soledad", "Debolsillo", "1967", "3a", "Gabriel García Márquez", "Fantasia");
                Llibre llibre20 = new Llibre("9788426415664", "1984", "Edhasa", "1949", "2a", "George Orwell", "Clàssic");

                munts.emmagatzemarLlibre(llibre17);
                munts.emmagatzemarLlibre(llibre18);
                munts.emmagatzemarLlibre(llibre19);
                munts.emmagatzemarLlibre(llibre20);

                sistema.catalogarLlibre();
                sistema.catalogarLlibre();
                sistema.catalogarLlibre();
                sistema.catalogarLlibre();

                sistema.catalogarLlibre();
                sistema.catalogarLlibre();
                sistema.catalogarLlibre();
                sistema.catalogarLlibre();

                sistema.catalogarLlibre();
                sistema.catalogarLlibre();
                sistema.catalogarLlibre();
                sistema.catalogarLlibre();
            }

    }

    // CATALOGAR UN LLIBRE
    @GET
    @ApiOperation(value = "Catalogar un llibre", notes = "Mostra el llibre catalogat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llibre catalogat correctament", response = Llibre.class),
            @ApiResponse(code = 404, message = "No hi ha llibres per a catalogar")
    })
    @Path("llibre/catalogar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response catalogarLlibre() {
        Llibre llibre = sistema.catalogarLlibre();

        if (llibre == null) {
            // Retornem un missatge d'error
            return Response.status(Response.Status.NOT_FOUND).entity("No hi ha llibres per a catalogar").type(MediaType.TEXT_PLAIN).build();
        }

        return Response.ok(llibre).build();
    }

    // TORNAR LLISTA DE LLIBRES CATALOGATS PER TEMATICA;
    @GET
    @ApiOperation(value = "Consultar llista de llibres catalogats per temàtica",
            notes = "Mostra els llibres catalogats per cada temàtica")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llista trobada", response = Llibre.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "No hi ha llibres catalogats")
    })
    @Path("llibres/catalogats/tematica")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostrarLlibresCatalogatsPerTematica(@QueryParam("tematica") String tematica) {
        CatalegTematic catalegTematic = this.sistema.getCatalegTematic();

        LlistaLlibres llistaLlibres = catalegTematic.getLlibresByTema(tematica);
        return Response.ok(llistaLlibres).build();
    }

    // TORNAR TOTA LA LLISTA DE LLIBRES CATALOGATS
    @GET
    @ApiOperation(
            value = "Consultar tota la llista de llibres catalogats",
            notes = "Mostra tots els llibres catalogats de totes les temàtiques"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llista trobada", response = Llibre.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "No hi ha llibres catalogats")
    })
    @Path("llibres/catalogats")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostrarTotsLlibresCatalogats() {
        CatalegTematic catalegTematic = this.sistema.getCatalegTematic();

        List<Llibre> totsElsLlibres = new ArrayList<>();

        for (LlistaLlibres llistaLlibres : catalegTematic.getCatalegMap().values()) {
            totsElsLlibres.addAll(llistaLlibres.getLlibres());
        }

        GenericEntity<List<Llibre>> entity = new GenericEntity<List<Llibre>>(totsElsLlibres) {};
        return Response.ok(entity).build();
    }

    // CONSULTAR LLISTA DE PRESTECS D'UN LECTOR;
    @GET
    @ApiOperation(value = "Consultar llista de prestecs d'un lector",
            notes = "Mostra la llista de prestecs d'un lector")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llista trobada", response = Prestec.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "No hi ha prestecs")
    })
    @Path("llibres/lector/prestecs/{lectorID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostrarLlistaDePrestecsDunLector(@QueryParam("lectorID") int lectorID) {
        LlistaLectors llistaLectors = this.sistema.getLlistaLectors();
        Lector lector = llistaLectors.getLectorByID(lectorID);

        List<Prestec> prestecs = lector.getPrestecs();
        GenericEntity<List<Prestec>> entity = new GenericEntity<List<Prestec>>(prestecs) {};
        return Response.ok(entity).build();
    }

    // TORNAR LLISTA DE LECTORS;
    @GET
    @ApiOperation(value = "Consultar llista de lectors", notes = "Mostra tots els lectors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llista trobada", response = Lector.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "No hi ha lectors")
    })
    @Path("lectors")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostrarLlistaDeLectors() {
        LlistaLectors llistaLectors = this.sistema.getLlistaLectors();

        List<Lector> lectors = llistaLectors.getLectors();

        GenericEntity<List<Lector>> entity = new GenericEntity<List<Lector>>(lectors) {};
        return Response.ok(entity).build();
    }

    // AFEGIR O ACTUALITZAR UN NOU LECTOR
    @POST
    @Path("lector/{idLector}/{nom}/{cognom1}/{cognom2}/{dni}/{datanaixement}/{llocnaixement}/{adreca}")
    @ApiOperation(value = "Afegir o Actualitzar un Lector", notes = "Afegir o Actualitzar un Lector")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lector Afegit o Actualitzat", response = Lector.class),
            @ApiResponse(code = 404, message = "Error a l'afegir o actualitzar el lector")
    })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response afegirOactualitzarLector(@PathParam("idLector") int idLector,@PathParam("nom") String nom, @PathParam("cognom1") String cognom1, @QueryParam("cognom2") String cognom2, @QueryParam("dni") String dni, @QueryParam("datanaixement") String datanaixement, @QueryParam("llocnaixement") String llocnaixement, @QueryParam("adreca") String adreca) {
        LlistaLectors llistaLectors = this.sistema.getLlistaLectors();
        Lector lectorexisteix = llistaLectors.getLectorByID(idLector);

        if (lectorexisteix == null) {
            Lector lector = new Lector(idLector);
            lector.setAllAtributs(nom,cognom1,cognom2,dni,datanaixement,llocnaixement,adreca);
            this.sistema.addLector(lector);
            return Response.ok(lector).build();
        }
        else{
                this.sistema.actualitzarDadesLector(idLector,nom,cognom1,cognom2, dni,datanaixement,llocnaixement,adreca);
                Lector lector = llistaLectors.getLectorByID(idLector);
                return Response.ok(lector).build();
        }
    }

    // EMMGATZEMAR UN NOU LLIBRE
    @POST
    @Path("llibre/emmagatzemar/{ISBN}/{titol}/{editorial}/{anypublicacio}/{numeroedicio}/{autor}/{tematica}")
    @ApiOperation(value = "Emmagtzemar un nou llibre", notes = "Emmagatzemar un nou llibre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response emmagatzemarLlibre(@PathParam("ISBN") String ISBN, @PathParam("titol") String titol,@PathParam("editorial") String editorial, @QueryParam("anypublicacio") String anypublicacio, @QueryParam("numeroedicio") String numeroedicio, @QueryParam("autor") String autor, @QueryParam("tematica") String tematica) {
        Llibre llibre = new Llibre(ISBN, titol, editorial, anypublicacio, numeroedicio, autor, tematica);
        this.sistema.addLlibre(llibre);
        return Response.status(Response.Status.CREATED).entity(llibre).build();
    }

    // CREAR UN PRESTEC
    @POST
    @Path("/llibre/prestar/{idLector}/{ISBN}")
    @ApiOperation(value = "Prestar un llibre", notes = "Crea un préstec per un lector si hi ha exemplars disponibles")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response prestarLlibre(@PathParam("idLector") int idLector, @PathParam("ISBN") String ISBN) {
        Llibre llibre = this.sistema.getLlibreCatalogatByISBN(ISBN); // Agafa un llibre catalogat, el qual el lector el vol demanar com a prestec
        if (llibre != null) {
            Lector lector = this.sistema.getLector(idLector);
            int numexemplars = llibre.getNumexemplars();
            if (lector != null) {
                if (numexemplars > 0) {
                    LocalDate dataPrestec = LocalDate.now(); // agafa la data actual
                    LocalDate dataDevolucio = dataPrestec.plusMonths(1); // la data actual + 1 mes

                    String dataPrestecString = dataPrestec.toString(); // Ho converteix a string
                    String dataDevolucioString = dataDevolucio.toString(); // Ho Converteix a String

                    Prestec prestec = this.sistema.ferPrestec(idLector, llibre, dataPrestecString, dataDevolucioString); // Fa el prestec

                    return Response.status(Response.Status.CREATED).entity(prestec).build();
                }
                else {
                    return Response.status(Response.Status.NOT_FOUND).entity("No hi ha exemplars suficients").type(MediaType.TEXT_PLAIN).build();
                }
            }
            else {
                return Response.status(Response.Status.NOT_FOUND).entity("El lector no existeix").type(MediaType.TEXT_PLAIN).build();
            }
        }
        else {
            return Response.status(Response.Status.NOT_FOUND).entity("El llibre no existeix").type(MediaType.TEXT_PLAIN).build();
        }
    }

}