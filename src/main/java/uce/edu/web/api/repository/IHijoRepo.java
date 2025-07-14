package uce.edu.web.api.repository;

import uce.edu.web.api.repository.modelo.Hijo;

import java.util.List;

public interface IHijoRepo {
  public List<Hijo> buscarPorEstudianteId(Integer id);

  public List<Hijo> buscarPorProfesorId(Integer id);
}
