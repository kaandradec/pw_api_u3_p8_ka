package uce.edu.web.api.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

import java.util.List;

// SERVICIO
@Path("/estudiantes")
public class EstudianteController {

    @Inject
    private IEstudianteService iEstudianteService;

    // CAPACIDADES DEL SERVICIO
    @GET
    @Path("/{id}")
    public Estudiante consultarPorId(@PathParam("id") Integer id) {
        return this.iEstudianteService.buscarPorId(id);
    }

    @GET
    @Path("")
    public List<Estudiante> consultarTodos() {
        return this.iEstudianteService.buscarTodos();
    }

    // El recurso se lo envia en el body, @RequestBody opcional
    @POST
    @Path("")
    public void guardar(@RequestBody Estudiante estudiante) {
    }
    @PUT
    @Path("/{id}")
    public void actualizar(@RequestBody Estudiante estudiante, @PathParam("id") Integer id){

    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@RequestBody Estudiante estudiante, @PathParam("id") Integer id){

    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id){

    }
}
