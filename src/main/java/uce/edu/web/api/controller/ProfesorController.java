package uce.edu.web.api.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService iProfesorService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    @Operation(summary = "Consultar Profesor por ID", description = "Este endpoint permite consultar un profesor por su ID")
    public Response consultarPorId(@PathParam("id") Integer id) {
        return Response.status(Response.Status.OK).entity(this.iProfesorService.buscarPorId(id)).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar Profesores", description = "Este endpoint permite consultar todos los profesores")
    public Response consultarTodos(@QueryParam("genero") String genero) {
        return Response.status(Response.Status.OK).entity(this.iProfesorService.buscarTodos(genero)).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar Profesor", description = "Este endpoint permite guardar un nuevo profesor")
    public Response guardar(@RequestBody Profesor profesor) {
        this.iProfesorService.guardar(profesor);
        return Response.status(Response.Status.OK).entity("Profesor guardado correctamente").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@RequestBody Profesor profesor, @PathParam("id") Integer id) {
        profesor.setId(id);
        this.iProfesorService.actualizarPorId(profesor);
        return Response.status(Response.Status.OK).entity("Profesor con id " + id + " actualizado correctamente")
                .build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarParcial(@RequestBody Profesor profesor, @PathParam("id") Integer id) {
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
        return Response.status(Response.Status.OK).entity("Profesor con id " + id + " actualizado parcialmente")
                .build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response borrarPorId(@PathParam("id") Integer id) {
        try {
            this.iProfesorService.borrarPorId(id);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Profesor no encontrado").build();
        }

        return Response.status(Response.Status.OK)
                .entity("Profesor con id " + id + " eliminado correctamente")
                .build();
    }
}