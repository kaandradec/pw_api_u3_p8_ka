package uce.edu.web.api.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IHijoRepo;
import uce.edu.web.api.repository.modelo.Hijo;

import java.util.List;

@ApplicationScoped
public class HijoServiceImpl implements HijoService {

  @Inject
  private IHijoRepo hijoRepo;

  @Override
  public List<Hijo> buscarPorEstudianteId(Integer id) {
    return this.hijoRepo.buscarPorEstudianteId(id);
  }

  @Override
  public List<Hijo> buscarPorProfesorId(Integer id) {
    return this.hijoRepo.buscarPorProfesorId(id);
  }

}
