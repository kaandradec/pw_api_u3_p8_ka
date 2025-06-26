package uce.edu.web.api.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

import java.util.List;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService iProfesorService;

    @GET
    @Path("/{id}")
    public Profesor consultarPorId(@PathParam("id") Integer id) {
        return this.iProfesorService.buscarPorId(id);
    }

    @GET
    @Path("")
    public List<Profesor> consultarTodos() {
        return this.iProfesorService.buscarTodos();
    }

    // El recurso se lo envia en el body, @RequestBody opcional
    @POST
    @Path("")
    public void guardar(@RequestBody Profesor profesor) {
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@RequestBody Profesor profesor, @PathParam("id") Integer id) {

    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@RequestBody Profesor profesor, @PathParam("id") Integer id) {

    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {

    }
}