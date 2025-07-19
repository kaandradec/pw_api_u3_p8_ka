package uce.edu.web.api.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.ClaimValue;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.HijoService;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.mapper.HijoMapper;
import uce.edu.web.api.service.to.EstudianteTo;
import uce.edu.web.api.service.to.HijoTo;

import java.util.List;
import java.util.stream.Collectors;

// SERVICIO
@Path("/estudiantes")
public class EstudianteController {

    @Inject
    JsonWebToken jwt;

    @Inject
    @Claim("sub")
    ClaimValue<String> subject;

    @Inject
    private IEstudianteService iEstudianteService;

    @Inject
    private HijoService hijoService;

    // CAPACIDADES DEL SERVICIO
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    // @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        // return this.iEstudianteService.buscarPorId(id);

        EstudianteTo estudianteTo = EstudianteMapper.toTo(this.iEstudianteService.buscarPorId(id));
        estudianteTo.buidUri(uriInfo);

        return Response.status(227).entity(estudianteTo).build();
    }

    // ?genero=M&provincia=pichincha
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consultar Estudiantes", description = "Este endpoint permite consultar todos los estudiantes")
    public Response consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia, @Context UriInfo uriInfo) {
        System.out.println("Provincia query param:" + provincia);

        List<Estudiante> estudiantes = this.iEstudianteService.buscarTodos(genero);
        List<EstudianteTo> estudiantesTo = estudiantes.stream()
                .map(est -> {
                    EstudianteTo estudianteTo = EstudianteMapper.toTo(est);
                    estudianteTo.buidUri(uriInfo);
                    return estudianteTo;
                })
                .collect(Collectors.toList());

        return Response.status(Response.Status.OK).entity(estudiantesTo).build();
    }

    // El recurso se lo envia en el body, @RequestBody opcional
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar Estudiantes", description = "Este endpoint permite guardar un nuevo estudiante")
    public Response guardar(@RequestBody EstudianteTo estudianteTo) {
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteTo);
        this.iEstudianteService.guardar(estudiante);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPorId(@RequestBody EstudianteTo estudianteTo, @PathParam("id") Integer id) {
        estudianteTo.setId(id);
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteTo);
        this.iEstudianteService.actualizarPorId(estudiante);
        return Response.status(Response.Status.OK).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Actualizar Parcialmente Estudiante", description = "Este endpoint permite actualizar parcialmente un estudiante")
    public Response actualizarParcial(@RequestBody EstudianteTo estudianteTo, @PathParam("id") Integer id) {
        // Buscar el estudiante existente
        Estudiante estudianteExistente = this.iEstudianteService.buscarPorId(id);

        if (estudianteExistente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Estudiante no encontrado")
                    .build();
        }

        if (estudianteTo.getNombre() != null) {
            estudianteExistente.setNombre(estudianteTo.getNombre());
        }
        if (estudianteTo.getApellido() != null) {
            estudianteExistente.setApellido(estudianteTo.getApellido());
        }
        if (estudianteTo.getFechaNacimiento() != null) {
            estudianteExistente.setFechaNacimiento(estudianteTo.getFechaNacimiento());
        }
        if (estudianteTo.getGenero() != null) {
            estudianteExistente.setGenero(estudianteTo.getGenero());
        }

        this.iEstudianteService.actualizarParcialPorId(estudianteExistente);

        return Response.status(Response.Status.OK)
                .entity("Estudiante con id " + id + " actualizado parcialmente")
                .build();
    }

    @DELETE
    // @Produces(MediaType.APPLICATION_JSON)
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

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerHijosPorId(@PathParam("id") Integer id) {
        // Verificar que el estudiante existe
        Estudiante estudiante = this.iEstudianteService.buscarPorId(id);
        if (estudiante == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Estudiante no encontrado")
                    .build();
        }

        List<Hijo> hijos = this.hijoService.buscarPorEstudianteId(id);
        List<HijoTo> hijosTo = hijos.stream()
                .map(HijoMapper::toTo)
                .collect(java.util.stream.Collectors.toList());

        return Response.status(Response.Status.OK).entity(hijosTo).build();
    }
}
