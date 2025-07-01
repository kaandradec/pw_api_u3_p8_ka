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
        this.iProfesorService.guardar(profesor);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@RequestBody Profesor profesor, @PathParam("id") Integer id) {
        profesor.setId(id);
        this.iProfesorService.actualizarPorId(profesor);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@RequestBody Profesor profesor, @PathParam("id") Integer id) {
        Profesor p = this.iProfesorService.buscarPorId(id);


        if (profesor.getNombre() != null)
            p.setNombre(profesor.getNombre());
        if (profesor.getApellido() != null)
            p.setApellido(profesor.getApellido());
        if (profesor.getFechaNacimiento() != null)
            p.setFechaNacimiento(profesor.getFechaNacimiento());
        if (profesor.getEmail() != null)
            p.setEmail(profesor.getEmail());
        if (profesor.getSalario() != null)
            p.setSalario(profesor.getSalario());

        this.iProfesorService.actualizarParcialPorId(p);

    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {
        this.iProfesorService.borrarPorId(id);
    }
}