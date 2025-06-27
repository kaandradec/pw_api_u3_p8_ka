package uce.edu.web.api.service;

import uce.edu.web.api.repository.modelo.Estudiante;

import java.util.List;

public interface IEstudianteService {
  public Estudiante buscarPorId(Integer id);

  public List<Estudiante> buscarTodos();

  public void actualizarPorId(Estudiante estudiante);

  public void actualizarParcialPorId(Estudiante estudiante);

  public void borrarPorId(Integer id);

  public void guardar(Estudiante estudiante);
}
