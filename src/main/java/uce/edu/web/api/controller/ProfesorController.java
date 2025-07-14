package uce.edu.web.api.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.HijoService;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.mapper.HijoMapper;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.HijoTo;
import uce.edu.web.api.service.to.ProfesorTo;

import java.util.List;
import java.util.stream.Collectors;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService iProfesorService;

    @Inject
    private HijoService hijoService;

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar Profesor por ID", description = "Este endpoint permite consultar un profesor por su ID")
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ProfesorTo profesorTo = ProfesorMapper.toTo(this.iProfesorService.buscarPorId(id));
        profesorTo.buildUri(uriInfo);

        return Response.status(227).entity(profesorTo).build();
    }

    // ?genero=M&provincia=pichincha
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar Profesores", description = "Este endpoint permite consultar todos los profesores")
    public Response consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia, @Context UriInfo uriInfo) {
        System.out.println("Provincia query param:" + provincia);

        List<Profesor> profesores = this.iProfesorService.buscarTodos(genero);
        List<ProfesorTo> profesoresTo = profesores.stream()
                .map(prof -> {
                    ProfesorTo profesorTo = ProfesorMapper.toTo(prof);
                    profesorTo.buildUri(uriInfo);
                    return profesorTo;
                })
                .collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(profesoresTo).build();
    }

    // El recurso se lo envia en el body, @RequestBody opcional
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar Profesor", description = "Este endpoint permite guardar un nuevo profesor")
    public Response guardar(@RequestBody ProfesorTo profesorTo) {
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.iProfesorService.guardar(profesor);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPorId(@RequestBody ProfesorTo profesorTo, @PathParam("id") Integer id) {
        profesorTo.setId(id);
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.iProfesorService.actualizarPorId(profesor);
        return Response.status(Response.Status.OK).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar Parcialmente Profesor", description = "Este endpoint permite actualizar parcialmente un profesor")
    public Response actualizarParcial(@RequestBody ProfesorTo profesorTo, @PathParam("id") Integer id) {
        // Buscar el profesor existente
        Profesor profesorExistente = this.iProfesorService.buscarPorId(id);

        if (profesorExistente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Profesor no encontrado")
                    .build();
        }

        if (profesorTo.getNombre() != null) {
            profesorExistente.setNombre(profesorTo.getNombre());
        }
        if (profesorTo.getApellido() != null) {
            profesorExistente.setApellido(profesorTo.getApellido());
        }
        if (profesorTo.getFechaNacimiento() != null) {
            profesorExistente.setFechaNacimiento(profesorTo.getFechaNacimiento());
        }
        if (profesorTo.getEmail() != null) {
            profesorExistente.setEmail(profesorTo.getEmail());
        }
        if (profesorTo.getSalario() != null) {
            profesorExistente.setSalario(profesorTo.getSalario());
        }
        if (profesorTo.getGenero() != null) {
            profesorExistente.setGenero(profesorTo.getGenero());
        }

        this.iProfesorService.actualizarParcialPorId(profesorExistente);

        return Response.status(Response.Status.OK)
                .entity("Profesor con id " + id + " actualizado parcialmente")
                .build();
    }

    @DELETE
    @Path("/{id}")
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

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerHijosPorId(@PathParam("id") Integer id) {
        // Verificar que el profesor existe
        Profesor profesor = this.iProfesorService.buscarPorId(id);
        if (profesor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Profesor no encontrado")
                    .build();
        }

        List<Hijo> hijos = this.hijoService.buscarPorProfesorId(id);
        List<HijoTo> hijosTo = hijos.stream()
                .map(HijoMapper::toTo)
                .collect(java.util.stream.Collectors.toList());

        return Response.status(Response.Status.OK).entity(hijosTo).build();
    }
}