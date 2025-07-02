package uce.edu.web.api.repository;

import uce.edu.web.api.repository.modelo.Estudiante;

import java.util.List;

public interface IEstudianteRepo {

    public Estudiante seleccionarPorId(Integer id);

    public List<Estudiante> seleccionarTodos(String genero);

    public void actualizarPorId(Estudiante estudiante);

    public void actualizarParcialPorId(Estudiante estudiante);

    public void borrarPorId(Integer id);

    public void insertar(Estudiante estudiante);

}
