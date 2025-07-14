package uce.edu.web.api.service;

import uce.edu.web.api.repository.modelo.Hijo;

import java.util.List;

public interface HijoService {
  public List<Hijo> buscarPorEstudianteId(Integer id);

  public List<Hijo> buscarPorProfesorId(Integer id);
}
