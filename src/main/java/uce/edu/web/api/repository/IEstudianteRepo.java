package uce.edu.web.api.repository;

import uce.edu.web.api.repository.modelo.Estudiante;

import java.util.List;

public interface IEstudianteRepo {

    public Estudiante seleccionarPorId(Integer id);

    public List<Estudiante> seleccionarTodos();

}
