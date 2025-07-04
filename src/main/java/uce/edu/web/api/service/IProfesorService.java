package uce.edu.web.api.service;

import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.to.ProfesorTo;

import java.util.List;

import jakarta.ws.rs.core.UriInfo;

public interface IProfesorService {

    public void guardar(Profesor profesor);

    public ProfesorTo buscarPorId(Integer id, UriInfo uriInfo);

    // public Profesor buscarPorId(Integer id);

    public List<Profesor> buscarTodos(String genero);

    public void actualizarPorId(Profesor profesor);

    public void actualizarParcialPorId(Profesor profesor);

    public void borrarPorId(Integer id);
}
