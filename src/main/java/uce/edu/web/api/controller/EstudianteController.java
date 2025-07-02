package uce.edu.web.api.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import org.eclipse.microprofile.openapi.annotations.Operation;
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

    // ?genero=M&provincia=pichincha
    @GET
    @Path("")
    @Operation(summary = "Consultar Estudiantes", description = "Este endpoint permite consultar todos los estudiantes")
    public List<Estudiante> consultarTodos(@QueryParam("genero") String genero,
            @QueryParam("provincia") String provincia) {
                System.out.println("Provincia query param:" + provincia);
        return this.iEstudianteService.buscarTodos(genero);
    }

    // El recurso se lo envia en el body, @RequestBody opcional
    @POST
    @Path("")
    @Operation(summary = "Guardar Estudiantes", description = "Este endpoint permite guardar un nuevo estudiante")
    public void guardar(@RequestBody Estudiante estudiante) {
        this.iEstudianteService.actualizarParcialPorId(estudiante);
    }

    @PUT
    @Path("/{id}")
    public void actualizarPorId(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);
        this.iEstudianteService.actualizarPorId(estudiante);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcial(@RequestBody Estudiante estudiante, @PathParam("id") Integer id) {
        estudiante.setId(id);

        Estudiante est = this.consultarPorId(id);
        if (estudiante.getApellido() != null)
            est.setApellido(estudiante.getApellido());
        if (estudiante.getNombre() != null)
            est.setNombre(estudiante.getNombre());
        if (estudiante.getFechaNacimiento() != null)
            est.setFechaNacimiento(estudiante.getFechaNacimiento());

        this.iEstudianteService.actualizarParcialPorId(est);
    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {
        this.iEstudianteService.borrarPorId(id);
    }
}
