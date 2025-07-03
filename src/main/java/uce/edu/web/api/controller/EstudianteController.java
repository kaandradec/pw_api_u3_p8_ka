package uce.edu.web.api.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

// SERVICIO
@Path("/estudiantes")
public class EstudianteController {

    @Inject
    private IEstudianteService iEstudianteService;

    // CAPACIDADES DEL SERVICIO
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response consultarPorId(@PathParam("id") Integer id) {
        // return this.iEstudianteService.buscarPorId(id);

        return Response.status(227).entity(this.iEstudianteService.buscarPorId(id)).build();
    }

    // ?genero=M&provincia=pichincha
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar Estudiantes", description = "Este endpoint permite consultar todos los estudiantes")
    public Response consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia) {
        System.out.println("Provincia query param:" + provincia);
        // return this.iEstudianteService.buscarTodos(genero);

        return Response.status(Response.Status.OK).entity(this.iEstudianteService.buscarTodos(genero)).build();
    }

    // El recurso se lo envia en el body, @RequestBody opcional
    @POST
    @Path("")
    // @Consumes(MediaType.APPLICATION_JSON) // MediaType de Jakarta
    @Consumes(MediaType.APPLICATION_XML) // MediaType de Jakarta
    @Operation(summary = "Guardar Estudiantes", description = "Este endpoint permite guardar un nuevo estudiante")
    public void guardar(@RequestBody Estudiante estudiante) {
        this.iEstudianteService.actualizarParcialPorId(estudiante);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.iEstudianteService.actualizarPorId(estudiante);
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public void actualizarParcial(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);

        Estudiante est = this.iEstudianteService.buscarPorId(id);
        if (estudiante.getApellido() != null)
            est.setApellido(estudiante.getApellido());
        if (estudiante.getNombre() != null)
            est.setNombre(estudiante.getNombre());
        if (estudiante.getFechaNacimiento() != null)
            est.setFechaNacimiento(estudiante.getFechaNacimiento());

        this.iEstudianteService.actualizarParcialPorId(est);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response borrarPorId(@PathParam("id") Integer id) {

        try {
            this.iEstudianteService.borrarPorId(id);
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Estudiante no encontrado").build();
        }

        return Response.status(Response.Status.OK)
                .entity("Estudiante con id " + id + " eliminado correctamente")
                .build();
    }
}
