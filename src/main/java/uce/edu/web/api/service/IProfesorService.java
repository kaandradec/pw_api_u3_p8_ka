package uce.edu.web.api.service;

import uce.edu.web.api.repository.modelo.Profesor;

import java.util.List;

public interface IProfesorService {

    public void guardar(Profesor profesor);

    public Profesor buscarPorId(Integer id);

    public List<Profesor> buscarTodos();

    public void actualizarPorId(Profesor profesor);

    public void actualizarParcialPorId(Profesor profesor);

    public void borrarPorId(Integer id);
}
